package org.example.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生作业评分对象 student_work_score
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public class StudentWorkScore extends BaseEntity
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

    /** 评分规则id */
    @Excel(name = "评分规则id")
    private Long ruleId;

    /** 评分规则维度id */
    @Excel(name = "评分规则维度id")
    private Long dimensionId;

    /** 得分 */
    @Excel(name = "得分")
    private BigDecimal score;

    /** 批语 */
    @Excel(name = "批语")
    private String comment;

    /** 作业状态 */
    @Excel(name = "作业状态")
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

    public void setRuleId(Long ruleId) 
    {
        this.ruleId = ruleId;
    }

    public Long getRuleId() 
    {
        return ruleId;
    }

    public void setDimensionId(Long dimensionId) 
    {
        this.dimensionId = dimensionId;
    }

    public Long getDimensionId() 
    {
        return dimensionId;
    }

    public void setScore(BigDecimal score)
    {
        this.score = score;
    }

    public BigDecimal getScore() 
    {
        return score;
    }

    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
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
            .append("ruleId", getRuleId())
            .append("dimensionId", getDimensionId())
            .append("score", getScore())
            .append("comment", getComment())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("teacherId", getTeacherId())
            .toString();
    }
}
