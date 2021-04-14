package com.example.config;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;


public class TemplateResouces {
    /**
     * getImportFile
     * @param string
     * @param req
     * @return
     * @throws IOException 
     */
    public static String getImportFilePath(String path, HttpServletRequest req) throws IOException {
        return new ClassPathResource("public/template/import/" + path).getFile().getPath();
    }
    /**
     * getImportFile
     * @param string
     * @param req
     * @return
     * @throws IOException 
     */
    public static InputStream getImportFile(String path, HttpServletRequest req) throws IOException {
        return new ClassPathResource("public/template/import/" + path).getInputStream();
    }
    /**
     * getReportFile
     * @param string
     * @param req
     * @return
     * @throws IOException 
     */
    public static InputStream getReportFile(String path, HttpServletRequest req) throws IOException {
        return new ClassPathResource("public/template/report/" + path).getInputStream();
    }
}