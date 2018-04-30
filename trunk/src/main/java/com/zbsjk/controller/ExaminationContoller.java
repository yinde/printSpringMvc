package com.zbsjk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zbsjk.model.entity.ExaminationInfo;
import com.zbsjk.service.ExaminationService;

@RestController
public class ExaminationContoller {

	@Autowired
	private ExaminationService examinationService;
	
	@RequestMapping(value ="/examination", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addExamination(HttpServletRequest request, HttpServletResponse response,
			@RequestBody ExaminationInfo examinationInfo){
		return examinationService.addExamination(examinationInfo);
	}
	
	@RequestMapping(value ="/examination/{examinationId}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Object getExamination(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer examinationId){
		return examinationService.getExamination(examinationId);
	}
	
	@RequestMapping(value ="/examination/{examinationId}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
	public Object updateExamination(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer examinationId,
			@RequestBody ExaminationInfo examinationInfo){
		examinationInfo.setExaminationId(examinationId);
		return examinationService.updateExamination(examinationInfo);
	}
}
