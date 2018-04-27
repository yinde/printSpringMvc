package com.jing.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jing.model.entity.User;
import com.jing.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value ="/test/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Object test(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer id){
		PageHelper.startPage(1, 20,"sex desc,id asc");
		List<User> userlist = userService.queryUserList();
		PageInfo<User> pageInfo = new PageInfo<>(userlist);  
		request.getSession().setAttribute("user", userlist);
		return pageInfo;
	}
	
	@RequestMapping(value ="/testlist", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Object testSession(HttpServletRequest request, HttpServletResponse response){
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>)request.getSession().getAttribute("user");
		System.out.println(list.size());
		return null;
	}
}