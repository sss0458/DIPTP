package org.example.domain.vo;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

public class TeachTaskVo {
    private Long id;

    @Excel(name = "任务名称")
    private String title;

    @Excel(name = "任务描述")
    private String describe;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    @Excel(name = "任务状态")
    private String status;

    @Excel(name = "老师id")
    private Long teacherId;

    // 子表对象：教学任务实验列表
    private List<TeachTaskTestVo> teachTaskTestList;

    // 子表对象：关联的用户/班级列表
    private List<TeachTaskUserVo> teachTaskUserList;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescribe() { return describe; }
    public void setDescribe(String describe) { this.describe = describe; }
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getTeacherId() { return teacherId; }
    public void setTeacherId(Long teacherId) { this.teacherId = teacherId; }
    public List<TeachTaskTestVo> getTeachTaskTestList() { return teachTaskTestList; }
    public void setTeachTaskTestList(List<TeachTaskTestVo> teachTaskTestList) {
        this.teachTaskTestList = teachTaskTestList;
    }
    public List<TeachTaskUserVo> getTeachTaskUserList() { return teachTaskUserList; }
    public void setTeachTaskUserList(List<TeachTaskUserVo> teachTaskUserList) {
        this.teachTaskUserList = teachTaskUserList;
    }
}