package com.example.user.controller;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.CommonUtil;
import com.example.common.Constants;
import com.example.common.DataTableResults;
import com.example.common.Response;
import com.example.exception.SysException;
import com.example.user.entity.RoleBO;
import com.example.user.entity.UserBO;
import com.example.user.entity.UserBean;
import com.example.user.entity.UserForm;
import com.example.user.entity.UserRoleBO;
import com.example.user.service.JwtService;
import com.example.user.service.UserRoleService;
import com.example.user.service.UserService;

@RestController
@RequestMapping("/rest")
public class UserMainController {
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRoleService userRoleService;
    
    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<UserBO> listUser(){
        return userService.findAll();
    }
    @GetMapping(path = "/search")
    public @ResponseBody DataTableResults<UserBean> listStudents(HttpServletRequest req,UserForm user){
        return userService.getStudentList(user, req);
    }
    
    @GetMapping(path = "/search-user")
    public @ResponseBody DataTableResults<UserBean> getDatatables(HttpServletRequest req,UserForm user){
        return userService.getDatatable(user, req);
    }
    
    
    @GetMapping(path = "/get-roles")
    public List<RoleBO> getAllRole(){
        return userService.findAllRole();
    }
    
    @GetMapping(path = "/{id}")
    public @ResponseBody Response findById(HttpServletRequest req, @PathVariable Long id) throws Exception {
        UserBO bo = userService.findById(id);
        if (bo == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
        }
        List<Long> lstRoleId = userRoleService.findRoleByUserId(id);
        UserForm userForm = new UserForm();
        CommonUtil.copyProperties(userForm, bo);
        userForm.setLstRoleId(lstRoleId);
        return Response.success().withData(userForm);
    }
    
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Response create(HttpServletRequest req, @RequestBody UserForm form) 
            throws Exception, SysException {
        Long userId = CommonUtil.NVL(form.getUserId());
        UserBO bo;
        if(userId > 0L) {
            bo = userService.findById(userId);
            if(bo == null) {
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
//            if (!permissionChecker.hasPermission("action.update", adResouceKey, req)) {
//                return Response.invalidPermission();
//            }
        } else {
            bo = new UserBO();
//            if (!permissionChecker.hasPermission("action.insert", adResouceKey, req)) {
//                return Response.invalidPermission();
//            }
            bo.setCreatedDate(new Date());
            bo.setCreatedBy(jwtService.getUsernameFromRequest(req));
        }
        bo.setUserName(form.getUserName());
        bo.setPassword(form.getPassword());
        bo.setFullName(form.getFullName());
        bo.setDateOfBirth(form.getDateOfBirth());
        bo.setGender(form.getGender());
        bo.setEmail(form.getEmail());
        bo.setMobileNumber(form.getMobileNumber());
        bo.setPositionId(form.getPositionId());
        bo.setUserCode(form.getUserCode());
        bo.setRoleId(1L); // chỗ này phải sửa
        // phaan quyen
        /// xoa quyen cu
        userService.saveOrUpdate(bo);
        userRoleService.deleteAllByUserId(userId);
        /// Them quyen moi
        List<Long> lstRole = form.getLstRoleId();
        for (Long roleId : lstRole) {
        	UserRoleBO userRoleBO = new UserRoleBO();
        	userRoleBO.setUserId(bo.getUserId());
        	userRoleBO.setRoleId(roleId);
        	userRoleService.saveOrUpdate(userRoleBO);
		}
        //
        
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(bo);
    }

    
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Response delete(HttpServletRequest req,@PathVariable Long id) throws SysException, InstantiationException, IllegalAccessException {
        if(id > 0L) {
            UserBO bo = userService.findById(id);
            if (bo != null) {
//                if (!permissionChecker.hasPermission("action.delete", adResouceKey, req)) {
//                    return Response.invalidPermission();
//                 }
                userService.delete(bo);
                return Response.success(Constants.RESPONSE_CODE.DELETE_SUCCESS);
            } else {
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
        } else {
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(HttpServletRequest request, @RequestBody UserForm user) {
        String result = "";
        HttpStatus httpStatus = null;
        try {
            if (userService.checkLogin(user)) {
                result = jwtService.generateTokenLogin(user.getUserName());
                httpStatus = HttpStatus.OK;
                UserBean userBean = userService.loadUserByUsername(user.getUserName());
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userBean, null,
                        AuthorityUtils.createAuthorityList(userBean.getRole()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                result = "Wrong userId and password";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception ex) {
            result = "Server Error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<String>(result, httpStatus);
    }

    @RequestMapping(value = "/currentUserInfo", method = RequestMethod.GET)
    public UserBean getCurrentUserInfo() {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//        return userService.getUserByUsername(userDetails.getUsername());
        
        String userName = (String) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userService.getUserByUsername(userName);
    }
    
    @RequestMapping(value = "/userInfo/{userId}", method = RequestMethod.GET)
    public UserBean getUserInfo(@PathVariable Long userId) {
        return userService.getUserInfoById(userId);
    }

    
}
