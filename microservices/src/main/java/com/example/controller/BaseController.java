package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import com.example.common.Response;
import com.example.exception.ValidateException;


@Controller
public abstract class BaseController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        // true passed to CustomDateEditor constructor means convert empty String to null
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    /**
     * handleValidateException
     * 
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ValidateException.class)
    public @ResponseBody Response handleValidateException(ValidateException ex, HttpServletRequest req,
            HandlerMethod handlerMethod) {
        return Response.warning(ex.getCode());
    }

}
