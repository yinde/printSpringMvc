package com.zbsjk.service;

import com.zbsjk.model.entity.UserInfo;
import com.zbsjk.model.vo.PutpwdVo;

public interface UserService {

	Object addUser(UserInfo userInfo);

	Object updateUser(UserInfo userInfo);

	Object deleteUser(Integer userId,Integer operationUser);

	Object getUserList(UserInfo userInfo, Integer pageNo, Integer pageSize);

	Object putpwd(PutpwdVo putpwdVo,Integer userId);

	Object resetpwd(Integer userId);

}
