package org.example.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 教学任务实验与数据管理关联对象 teach_task_test_data_manage
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public class TeachTaskTestDataManage extends BaseEntity
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

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 老师id */
    @Excel(name = "老师id")
    private Long teacherId;

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
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("taskTestId", getTaskTestId())
            .append("dataManageId", getDataManageId())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("teacherId", getTeacherId())
            .toString();
    }
}
