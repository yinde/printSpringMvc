package com.zbsjk.ext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice  
@EnableWebMvc  
public class GlobalExceptionHandler{  
  
    @ExceptionHandler(ParamException.class)  
    @ResponseBody  
    public ErrorInfo paramException(HttpServletRequest req,HttpServletResponse rsp,Exception e){  
    	ParamException pe = (ParamException) e;
    	rsp.setStatus(400);
    	ErrorInfo errInfo = new ErrorInfo();  
    	errInfo.setField(pe.getFiled());
    	errInfo.setMsg(pe.getMessageException());
        return errInfo;  
    }  
  
    @ExceptionHandler(SecurityException.class)  
    @ResponseBody  
    public ErrorInfo securityException(HttpServletRequest req,HttpServletResponse rsp,Exception e){  
    	ParamException pe = (ParamException) e;
    	rsp.setStatus(403);
    	ErrorInfo errInfo = new ErrorInfo();  
    	errInfo.setField(pe.getFiled());
    	errInfo.setMsg(pe.getMessageException());
        return errInfo;  
    }  
  
}  
