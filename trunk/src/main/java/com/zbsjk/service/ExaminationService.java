package com.zbsjk.service;

import com.zbsjk.model.entity.ExaminationInfo;

public interface ExaminationService {

	Object addExamination(ExaminationInfo examinationInfo);

	Object getExamination(Integer examinationId);

	Object updateExamination(ExaminationInfo examinationInfo);

}
