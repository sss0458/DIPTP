package org.example.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 教学任务对象 teach_task
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public class TeachTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String title;

    /** 任务描述 */
    @Excel(name = "任务描述")
    private String describe;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 任务状态   0未开始 1进行中 2已结束*/
    @Excel(name = "任务状态")
    private String status;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 老师id */
    @Excel(name = "老师id")
    private Long teacherId;

    /** 教学任务实验信息 */
    private List<TeachTaskTest> teachTaskTestList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setDescribe(String describe) 
    {
        this.describe = describe;
    }

    public String getDescribe() 
    {
        return describe;
    }

    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }

    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
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

    public List<TeachTaskTest> getTeachTaskTestList()
    {
        return teachTaskTestList;
    }

    public void setTeachTaskTestList(List<TeachTaskTest> teachTaskTestList)
    {
        this.teachTaskTestList = teachTaskTestList;
    }

    //修改
    public boolean isExpired() {
        return this.endDate != null && new Date().after(this.endDate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("describe", getDescribe())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("teacherId", getTeacherId())
            .append("teachTaskTestList", getTeachTaskTestList())
            .toString();
    }
}
