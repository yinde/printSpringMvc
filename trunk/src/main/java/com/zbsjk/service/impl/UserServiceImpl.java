package com.zbsjk.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zbsjk.ext.ParamException;
import com.zbsjk.model.dao.UserInfoMapper;
import com.zbsjk.model.entity.UserInfo;
import com.zbsjk.model.vo.PutpwdVo;
import com.zbsjk.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public Object addUser(UserInfo userInfo) {
		UserInfo userInfoData = new UserInfo();
		userInfoData.setUserPhone(userInfo.getUserPhone());
		List<UserInfo> list = userInfoMapper.queryUserList(userInfo);
		if(list.size()>0){
			throw new ParamException("userid", "账号已存在");
		}
		
		userInfo.setUserProvince("湖南省");
		userInfo.setCreateTime(new Date());
		userInfoMapper.insertSelective(userInfo);
		return userInfo;
	}

	@Override
	public Object updateUser(UserInfo userInfo) {
		userInfoMapper.updateByPrimaryKeySelective(userInfo);
		return userInfo;
	}

	@Override
	public Object deleteUser(Integer userId) {
		return userInfoMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public Object getUserList(UserInfo userInfo, Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<UserInfo> userlist = userInfoMapper.queryUserList(userInfo);
		PageInfo<UserInfo> pageInfo = new PageInfo<>(userlist);  
		return pageInfo;
	}

	@Override
	public Object putpwd(PutpwdVo putpwdVo,Integer userId) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserPwd(putpwdVo.getOldpwd());
		userInfo.setUserPhone(putpwdVo.getAccount());
		List<UserInfo> list = userInfoMapper.queryUserList(userInfo);
		if(list.size()==0){
			throw new ParamException("account", "密码不正确");
		}
		
		UserInfo userInfoData = new UserInfo();
		userInfoData.setUserId(userId);
		userInfoData.setUserPwd(putpwdVo.getNewpwd());
		return userInfoMapper.updateByPrimaryKeySelective(userInfoData);
	}

	@Override
	public Object resetpwd(Integer userId) {
		UserInfo userInfoData = new UserInfo();
		userInfoData.setUserId(userId);
		userInfoData.setUserPwd("111111");
		return userInfoMapper.updateByPrimaryKeySelective(userInfoData);
	}

}
