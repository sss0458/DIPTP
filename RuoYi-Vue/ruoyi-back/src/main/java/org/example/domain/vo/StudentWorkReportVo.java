package org.example.domain.vo;

import com.ruoyi.common.annotation.Excel;

import java.math.BigDecimal;

public class StudentWorkReportVo {
    private Long id;

    @Excel(name = "学生姓名")
    private String studentName;

    @Excel(name = "任务标题")
    private String taskTitle;

    @Excel(name = "实验内容步骤")
    private String step;

    @Excel(name = "实验结果")
    private String workPath;

    @Excel(name = "得分")
    private String score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getWorkPath() {
        return workPath;
    }

    public void setWorkPath(String workPath) {
        this.workPath = workPath;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
