package org.example.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生作业对象 student_work
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public class StudentWork extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 任务id */
    @Excel(name = "任务id")
    private Long taskId;

    /** 实验id */
    @Excel(name = "实验id")
    private Long taskTestId;

    /** 数据管理id */
    @Excel(name = "数据管理id")
    private Long dataManageId;

    /** 学生id */
    @Excel(name = "学生id")
    private Long studentId;

    /** 老师id */
    @Excel(name = "老师id")
    private Long teacherId;

    /** 提交次数 */
    @Excel(name = "提交次数")
    private Long quantity;

    /** 最近保存时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近保存时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastDate;

    /** 作业状态 */
    @Excel(name = "作业状态")
    private String status;

    /** 批改状态 */
    @Excel(name = "批改状态")
    private String criticizeStatus;

    /** 得分 */
    @Excel(name = "得分")
    private BigDecimal score;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }

    public void setTaskTestId(Long taskTestId) 
    {
        this.taskTestId = taskTestId;
    }

    public Long getTaskTestId() 
    {
        return taskTestId;
    }

    public void setDataManageId(Long dataManageId) 
    {
        this.dataManageId = dataManageId;
    }

    public Long getDataManageId() 
    {
        return dataManageId;
    }

    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }

    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }

    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }

    public void setLastDate(Date lastDate) 
    {
        this.lastDate = lastDate;
    }

    public Date getLastDate() 
    {
        return lastDate;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setCriticizeStatus(String criticizeStatus) 
    {
        this.criticizeStatus = criticizeStatus;
    }

    public String getCriticizeStatus() 
    {
        return criticizeStatus;
    }

    public void setScore(BigDecimal score) 
    {
        this.score = score;
    }

    public BigDecimal getScore() 
    {
        return score;
    }

    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("taskTestId", getTaskTestId())
            .append("dataManageId", getDataManageId())
            .append("studentId", getStudentId())
            .append("teacherId", getTeacherId())
            .append("quantity", getQuantity())
            .append("lastDate", getLastDate())
            .append("status", getStatus())
            .append("criticizeStatus", getCriticizeStatus())
            .append("score", getScore())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
