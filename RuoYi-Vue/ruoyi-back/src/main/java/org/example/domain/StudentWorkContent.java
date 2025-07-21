package org.example.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生作业内容对象 student_work_content
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public class StudentWorkContent extends BaseEntity
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

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    /** 代码 */
    @Excel(name = "代码")
    private String code;

    /** 文件地址/新图片 */
    @Excel(name = "文件地址/新图片")
    private String workPath;

    /** 实验结果与分析 */
    @Excel(name = "实验结果与分析")
    private String result;

    /** 实验主体 */
    @Excel(name = "实验主体")
    private String mainBody;

    /** 思考与总结 */
    @Excel(name = "思考与总结")
    private String summary;

    /** 保存时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "保存时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastDate;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 老师id */
    @Excel(name = "老师id")
    private Long teacherId;

    /** 学生作业内容步骤信息 */
    private List<StudentWorkContentStep> studentWorkContentStepList;

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

    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }

    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }

    public void setWorkPath(String workPath) 
    {
        this.workPath = workPath;
    }

    public String getWorkPath() 
    {
        return workPath;
    }

    public void setResult(String result) 
    {
        this.result = result;
    }

    public String getResult() 
    {
        return result;
    }

    public void setMainBody(String mainBody) 
    {
        this.mainBody = mainBody;
    }

    public String getMainBody() 
    {
        return mainBody;
    }

    public void setSummary(String summary) 
    {
        this.summary = summary;
    }

    public String getSummary() 
    {
        return summary;
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

    public List<StudentWorkContentStep> getStudentWorkContentStepList()
    {
        return studentWorkContentStepList;
    }

    public void setStudentWorkContentStepList(List<StudentWorkContentStep> studentWorkContentStepList)
    {
        this.studentWorkContentStepList = studentWorkContentStepList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("taskTestId", getTaskTestId())
            .append("workId", getWorkId())
            .append("version", getVersion())
            .append("code", getCode())
            .append("workPath", getWorkPath())
            .append("result", getResult())
            .append("mainBody", getMainBody())
            .append("summary", getSummary())
            .append("lastDate", getLastDate())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("teacherId", getTeacherId())
            .append("studentWorkContentStepList", getStudentWorkContentStepList())
            .toString();
    }
}
