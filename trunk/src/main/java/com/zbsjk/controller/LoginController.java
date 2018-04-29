package com.zbsjk.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zbsjk.model.entity.UserInfo;
import com.zbsjk.model.vo.PutpwdVo;
import com.zbsjk.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value ="/login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object login(HttpServletRequest request, HttpServletResponse response,
			@RequestBody PutpwdVo putpwdVo){
		UserInfo user = (UserInfo)loginService.login(putpwdVo);
		request.getSession().setAttribute("user", user);
		return user;
	}
	
	@RequestMapping(value ="/role", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Object getRoleList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer pageNo,
			@RequestParam Integer pageSize){
		return loginService.getRoleList(pageNo,pageSize);
	}
}
