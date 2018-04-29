package com.zbsjk.model.entity;

public class ExaminationInfo {
    private Integer examinationId;

    private Integer examinationResults;

    private String equipmentNumber;

    private String userName;

    private String watchTime;

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public Integer getExaminationResults() {
        return examinationResults;
    }

    public void setExaminationResults(Integer examinationResults) {
        this.examinationResults = examinationResults;
    }

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber == null ? null : equipmentNumber.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getWatchTime() {
        return watchTime;
    }

    public void setWatchTime(String watchTime) {
        this.watchTime = watchTime == null ? null : watchTime.trim();
    }
}