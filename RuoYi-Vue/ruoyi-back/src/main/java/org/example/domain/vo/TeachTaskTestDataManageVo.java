package org.example.domain.vo;

import com.ruoyi.common.annotation.Excel;

public class TeachTaskTestDataManageVo {
    private Long id;

    @Excel(name = "任务")
    private String task;

    @Excel(name = "实验id")
    private Long taskTestId;

    @Excel(name = "数据管理id")
    private Long dataManageId;

    @Excel(name = "状态")
    private String status;

    @Excel(name = "老师id")
    private Long teacherId;


    @Excel(name = "数据类型")
    private String dataType;

    @Excel(name = "数据路径")
    private String dataPath;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getTaskTestId() { return taskTestId; }
    public void setTaskTestId(Long taskTestId) { this.taskTestId = taskTestId; }
    public Long getDataManageId() { return dataManageId; }
    public void setDataManageId(Long dataManageId) { this.dataManageId = dataManageId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getTeacherId() { return teacherId; }
    public void setTeacherId(Long teacherId) { this.teacherId = teacherId; }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }
}