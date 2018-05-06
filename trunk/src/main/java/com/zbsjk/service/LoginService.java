package com.zbsjk.service;

import java.util.List;

import com.zbsjk.model.vo.PutpwdVo;

public interface LoginService {

	Object login(PutpwdVo putpwdVo);

	Object getRoleList(List<Integer> list);

}
