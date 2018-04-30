package com.zbsjk.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zbsjk.ext.SecurityException;
import com.zbsjk.model.entity.UserInfo;
import com.zbsjk.model.vo.PutpwdVo;
import com.zbsjk.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value ="/user", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addUser(HttpServletRequest request, HttpServletResponse response,
			@RequestBody UserInfo userInfo){
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		return userService.addUser(userInfo);
	}
	
	@RequestMapping(value ="/user/{userId}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
	public Object updateUser(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer userId,
			@RequestBody UserInfo userInfo){
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		userInfo.setUserId(userId);
		userInfo.setUserPwd(null);
		return userService.updateUser(userInfo);
	}
	
	@RequestMapping(value ="/user/{userId}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
	public Object deleteUser(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer userId){
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		return userService.deleteUser(userId);
	}
	
	@RequestMapping(value ="/user", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Object getUserList(HttpServletRequest request, HttpServletResponse response,
			UserInfo userInfo,
			@RequestParam Integer pageNo,
			@RequestParam Integer pageSize){
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		
		if(user.getRoleId().equals(2)){
			userInfo.setUserCity(user.getUserCity());
		}else if(user.getRoleId().equals(4)){
			userInfo.setUserCity(user.getUserCity());
			userInfo.setUserArea(user.getUserArea());
		}else if(user.getRoleId().equals(1)){
			
		}else{
			throw new SecurityException("user", "无权限");
		}
		return userService.getUserList(userInfo,pageNo,pageSize);
	}
	
	@RequestMapping(value ="/user/{userid}/resetpwd", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
	public Object resetpwd(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer userId){
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		return userService.resetpwd(userId);
	}
	
	@RequestMapping(value ="/user/{userid}/putpwd", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
	public Object putpwd(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer userId,
			@RequestBody PutpwdVo putpwdVo){
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		return userService.putpwd(putpwdVo,userId);
	}
	
}
