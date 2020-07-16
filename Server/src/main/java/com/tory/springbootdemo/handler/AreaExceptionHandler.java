package com.tory.springbootdemo.handler;

import com.tory.springbootdemo.exception.AreaException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AreaExceptionHandler {
    @ExceptionHandler(AreaException.class)
    @ResponseBody
    private Map<String, Object> handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("success", false);
        modelMap.put("errMsg", e.getMessage());
        response.setCharacterEncoding("utf-8");     //防止中文乱码
        return modelMap;
    }
}
