package com.jing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jing.model.dao.UserMapper;
import com.jing.model.entity.User;
import com.jing.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User queryUserList() {
		
		return userMapper.selectByPrimaryKey(1);
	}

	
}
