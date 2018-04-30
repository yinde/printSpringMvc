package com.zbsjk.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zbsjk.ext.SecurityException;
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
		if(putpwdVo.getLoginType().equals("yingji")){
			if(!user.getRoleId().equals(1)&&!user.getRoleId().equals(2)&&!user.getRoleId().equals(4)){
				throw new SecurityException("user", "无登录权限");
			}
		}
		request.getSession().setAttribute("user", user);
		return user;
	}
	
	@RequestMapping(value ="/role", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Object getRoleList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer pageNo,
			@RequestParam Integer pageSize){
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		
		List<Integer> list = new ArrayList<>();
		switch (user.getRoleId()) {
		case 1:  //超级管理员
			list.add(2);
			list.add(3);
			list.add(4);
			list.add(5);
			list.add(6);
			list.add(7);
			break;
		case 2:	//市管理员
			list.add(3);
			list.add(4);
			list.add(5);
			list.add(6);
			list.add(7);
			break;
		case 3:	//区管理员
			list.add(5);
			list.add(7);
			break;
		default:
			break;
		}
		return loginService.getRoleList(pageNo,pageSize,list);
	}
}
