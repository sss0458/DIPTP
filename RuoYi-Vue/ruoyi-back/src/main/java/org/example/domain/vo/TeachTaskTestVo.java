package org.example.domain.vo;

import java.util.List;
import com.ruoyi.common.annotation.Excel;

public class TeachTaskTestVo {
    private Long id;

    @Excel(name = "任务")
    private String task;

    @Excel(name = "实验标题")
    private String title;

    @Excel(name = "实验类型")
    private String dataType;

    @Excel(name = "实验描述/目的及要求")
    private String describe;

    @Excel(name = "实验原理")
    private String theory;

    @Excel(name = "算法id")
    private Long algorithmId;

    @Excel(name = "案例库路径")
    private String casePath;

    @Excel(name = "评分规则")
    private String rule;

    @Excel(name = "状态")
    private String status;

    // 子表对象：关联的数据管理列表
    private List<TeachTaskTestDataManageVo> teachTaskTestDataManageList;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDataType() { return dataType; }
    public void setDataType(String dataType) { this.dataType = dataType; }
    public String getDescribe() { return describe; }
    public void setDescribe(String describe) { this.describe = describe; }
    public String getTheory() { return theory; }
    public void setTheory(String theory) { this.theory = theory; }
    public Long getAlgorithmId() { return algorithmId; }
    public void setAlgorithmId(Long algorithmId) { this.algorithmId = algorithmId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<TeachTaskTestDataManageVo> getTeachTaskTestDataManageList() {
        return teachTaskTestDataManageList;
    }
    public void setTeachTaskTestDataManageList(List<TeachTaskTestDataManageVo> teachTaskTestDataManageList) {
        this.teachTaskTestDataManageList = teachTaskTestDataManageList;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getCasePath() {
        return casePath;
    }

    public void setCasePath(String casePath) {
        this.casePath = casePath;
    }
}