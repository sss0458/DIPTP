package org.example.domain.vo;

import com.ruoyi.common.annotation.Excel;

public class TeachTaskUserVo {
    private Long id;

    @Excel(name = "任务")
    private String task;

    @Excel(name = "班级")
    private String dept;

    @Excel(name = "状态")
    private String status;

    @Excel(name = "老师id")
    private Long teacherId;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTaskId() { return task; }
    public void setTaskId(String taskId) { this.task = taskId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getTeacherId() { return teacherId; }
    public void setTeacherId(Long teacherId) { this.teacherId = teacherId; }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}