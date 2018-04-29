package com.zbsjk.service;

import com.zbsjk.model.vo.PutpwdVo;

public interface LoginService {

	Object login(PutpwdVo putpwdVo);

	Object getRoleList(Integer pageNo, Integer pageSize);

}
