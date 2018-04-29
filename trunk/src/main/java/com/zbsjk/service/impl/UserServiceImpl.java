package com.zbsjk.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zbsjk.model.dao.UserMapper;
import com.zbsjk.model.entity.User;
import com.zbsjk.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> queryUserList() {
		List<User> result = userMapper.queryUserList();
		return result;
	}

	
}
