package com.example.employeeManager.employeeImages.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.DataTableResults;
import com.example.common.VfData;
import com.example.employeeManager.employeeImages.bean.EmployeeImagesBean;
import com.example.employeeManager.employeeImages.bo.EmployeeImagesBO;
import com.example.employeeManager.employeeImages.dao.EmployeeImagesDAO;
import com.example.employeeManager.employeeImages.form.EmployeeImagesForm;

@Service
public class EmployeeImagesService {

    @Autowired
    private EmployeeImagesDAO employeeImagesdao;
    @Autowired
    private VfData vfData;

    /**
     * findById
     * @param EmployeeImagesId
     * @return
     */
    public EmployeeImagesBO findById(Long EmployeeImagesId) {
        return employeeImagesdao.findById(EmployeeImagesId).orElse(null);
    }

    /**
     * getDatatables
     * @param employeeImagesForm
     * @return
     */
    public DataTableResults<EmployeeImagesBean> getDatatables(EmployeeImagesForm employeeImagesForm) {
        return employeeImagesdao.getDatatables(vfData, employeeImagesForm);
    }

    /**
     * saveOrUpdate
     * @param entity
     */
    @Transactional
    public void saveOrUpdate(EmployeeImagesBO entity) {
        employeeImagesdao.save(entity);
    }

    /**
     * delete
     * @param entity
     */
    public void delete(EmployeeImagesBO entity) {
        employeeImagesdao.delete(entity);
    }
    
    public void saveAll(List<EmployeeImagesBO> listEmployeeImages) {
        employeeImagesdao.saveAll(listEmployeeImages);
    }
    
    public EmployeeImagesBean getEmployeeImageByEmployeeId(Long employeeId) {
        return employeeImagesdao.getUrlImageByEmployeeId(vfData, employeeId);
    }
    
    public EmployeeImagesBO getEmployeeImageByEmployeeIdBO(Long employeeId) {
        return employeeImagesdao.getUrlImageByEmployeeIdBO(vfData, employeeId);
    }
    
    public void saveImageToDirectory(String employeeImageUrl, String employeeCode, EmployeeImagesBO employeeImagesBO) {
        // save string base64 as a file to folder start
        String base64String = employeeImageUrl;
        String[] strings = base64String.split(",");
        String extension;
        switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default://should write cases for more images types
                extension = "jpg";
                break;
        }
        //convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        String path = "../assets/img/users/" + employeeCode + "." + extension;
        employeeImagesBO.setImgName(employeeCode + "." + extension);
        File file = new File(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
