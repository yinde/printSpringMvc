package com.zbsjk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zbsjk.ext.ParamException;
import com.zbsjk.model.dao.RoleInfoMapper;
import com.zbsjk.model.dao.UserInfoMapper;
import com.zbsjk.model.entity.RoleInfo;
import com.zbsjk.model.entity.UserInfo;
import com.zbsjk.model.vo.PutpwdVo;
import com.zbsjk.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private RoleInfoMapper roleInfoMapper;
	
	@Override
	public Object login(PutpwdVo putpwdVo) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserPhone(putpwdVo.getAccount());
		userInfo.setUserPwd(putpwdVo.getPwd());
		List<UserInfo> list = userInfoMapper.queryUserList(userInfo);
		if(list.size()==0){
			throw new ParamException("account", "账号密码不正确");
		}
		return list.get(0);
	}

	@Override
	public Object getRoleList(List<Integer> roleIdlist) {
		List<RoleInfo> list = roleInfoMapper.queryRoleList(roleIdlist);
		return list;
	}

}
