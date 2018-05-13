package com.zbsjk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zbsjk.ext.ParamException;
import com.zbsjk.model.dao.EquipmentInfoMapper;
import com.zbsjk.model.dao.ExaminationInfoMapper;
import com.zbsjk.model.entity.EquipmentInfo;
import com.zbsjk.model.entity.ExaminationInfo;
import com.zbsjk.model.vo.EquipmentListVo;
import com.zbsjk.service.ExaminationService;

@Service
public class ExaminationServiceImpl implements ExaminationService {

	@Autowired
	private ExaminationInfoMapper examinationInfoMapper;
	
	@Autowired
	private EquipmentInfoMapper equipmentInfoMapper;
	
	@Override
	public Object addExamination(ExaminationInfo examinationInfo) {
		//验证是否有记录或者审核通过
		EquipmentListVo ei = new EquipmentListVo();
		ei.setUserNameNoLike(examinationInfo.getUserName());
		ei.setEngineNumber(examinationInfo.getEquipmentNumber());
		ei.setAuditStatus(1);
		List<EquipmentInfo> elist = equipmentInfoMapper.queryByProperties(ei);
		if(elist.size()==0){
			throw new ParamException("equipmentNumber", "审核未通过或无记录");
		}
		
		if(elist.get(0).getPayStatus().equals(0)){
			throw new ParamException("user", "请先缴费！");
		}
		
		List<ExaminationInfo> eifList = examinationInfoMapper.queryByProperties(examinationInfo);
		if(eifList.size()>0){
			return eifList.get(0);
		}
		  examinationInfoMapper.insertSelective(examinationInfo);
		return elist.size();
	}

	@Override
	public Object getExamination(Integer examinationId) {
		return examinationInfoMapper.selectByPrimaryKey(examinationId);
	}

	@Override
	public Object updateExamination(ExaminationInfo examinationInfo) {
		return examinationInfoMapper.updateByPrimaryKeySelective(examinationInfo);
	}

}
