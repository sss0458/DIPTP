package org.example.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生作业内容步骤对象 student_work_content_step
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public class StudentWorkContentStep extends BaseEntity
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

    /** 作业id */
    @Excel(name = "作业id")
    private Long workId;

    /** 作业内容id */
    @Excel(name = "作业内容id")
    private Long workContentId;

    /** 序号 */
    @Excel(name = "序号")
    private Long sort;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

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
    public void setWorkId(Long workId) 
    {
        this.workId = workId;
    }

    public Long getWorkId() 
    {
        return workId;
    }
    public void setWorkContentId(Long workContentId) 
    {
        this.workContentId = workContentId;
    }

    public Long getWorkContentId() 
    {
        return workContentId;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
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
            .append("workId", getWorkId())
            .append("workContentId", getWorkContentId())
            .append("sort", getSort())
            .append("content", getContent())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("teacherId", getTeacherId())
            .toString();
    }
}
