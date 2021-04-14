package com.example.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.DataTableResults;
import com.example.common.UttData;
import com.example.user.dao.RoleDAO;
import com.example.user.dao.UserDAO;
import com.example.user.dao.UserRoleDAO;
import com.example.user.entity.RoleBO;
import com.example.user.entity.UserBO;
import com.example.user.entity.UserBean;
import com.example.user.entity.UserForm;



@Service(value = "userService")
class UserDetailsServiceImpl implements  UserService {

//    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserDAO userDAO;
    
    
    @Autowired
    private RoleDAO roleDAO;
    
    @Autowired
    private UserRoleDAO uRoleDAO;
    
    @Autowired
    private UttData uttData;
    
    public DataTableResults<UserBean> getStudentList(UserForm userForm, HttpServletRequest req) {
        return userDAO.getStudentList(uttData, userForm, req);
    }
    
    public DataTableResults<UserBean> getDatatable(UserForm userForm, HttpServletRequest req) {
        return userDAO.getDatatable(uttData, userForm, req);
    }
    
    // Lấy danh sách sinh viên theo lớp
    public List<UserBO> getListStudentByPositionId(Long positionId) {
        return userDAO.findByPositionId(positionId);
    }
    
    
    // 
    public UserBO getUserByCode(String userCode) {
        return userDAO.findByUserCode(userCode);
    }
    
    public List<RoleBO> findAllRole() {
        return roleDAO.findAll();
    }
    
    // hàm kiểm tra login
    public boolean checkLogin(UserForm user) {
        List<UserBO> listUser = (List<UserBO>) userDAO.findAll();
        for (UserBO userExist : listUser) {
            if (StringUtils.equals(user.getUserName(), userExist.getUserName())
                    && StringUtils.equals(user.getPassword(), userExist.getPassword())) {
                return true;
            }
        }
        return false;
    }
    
    
    public UserBean loadUserByUsername(String username) {
        UserBean user = userDAO.getUserWithRole(uttData, username);
            if (user.getUserName().equals(username)) {
                return user;
            }
        return null;
    }
    
    public UserBean getUserByUsername(String username) {
        UserBean user = userDAO.getUserByName(uttData, username);
        user.setLstRoleCode(roleDAO.findAllRoleOfUserCode(user.getUserCode()));
            if (user.getUserName().equals(username)) {
                return user;
            }
        return null;
    }
    
    // Thông tin người dùng theo userId
    public UserBean getUserInfoById(Long userId) {
        UserBean user = userDAO.getUserInfoById(uttData, userId);
            if (user.getUserId().equals(userId)) {
                return user;
            }
        return null;
    }

    public List<UserBO> findAll() {
        List<UserBO> list = new ArrayList<>();
        userDAO.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
    
    @Transactional
    public void saveOrUpdate(UserBO entity) {
        uttData.saveOrUpdate(entity);
        uttData.flushSession();
    }

    @Override
    public UserBO findById(Long id) {
        return userDAO.findById(id).get();
    }

    public void delete(UserBO entity) {
        userDAO.delete(entity);
    }

    @Override
    public UserBO save(UserBO user) {
        return userDAO.save(user);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        try {
//            UserBean user = new UserBean();
//            UserBO sysUser = userDAO.findUserAccount(username, entityManager);
//
//            if (sysUser != null) {
//                user.setEmail(sysUser.getEmail());
//                user.setUserCode(sysUser.getUserCode());
//                user.setFullName(sysUser.getFullName());
//                user.setMobileNumber(sysUser.getMobileNumber());
//                user.setUserId(sysUser.getUserId());
//                user.setUserName(sysUser.getUserName());
//                user.setPassword(sysUser.getPassword());
//            } else {
//                LOGGER.error("Bad credentials");
//                throw new BadCredentialsException("Bad credentials");
//            }
//            new AccountStatusUserDetailsChecker().check(user);
//            return user;
//        } catch (Exception ex) {
//            LOGGER.error("Bad credentials", ex);
//            throw ex;
//        }
//    }

}
