package org.example.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 案例库对象 example
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public class Example extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 任务标题 */
    @Excel(name = "任务标题")
    private String taskTitle;

    /** 任务描述 */
    @Excel(name = "任务描述")
    private String taskDescribe;

    /** 实验标题 */
    @Excel(name = "实验标题")
    private String testTitle;

    /** 实验类型 */
    @Excel(name = "实验类型")
    private String dataType;

    /** 实验描述/目的及要求 */
    @Excel(name = "实验描述/目的及要求")
    private String testDescribe;

    /** 实验原理 */
    @Excel(name = "实验原理")
    private String theory;

    /** 评分规则id */
    @Excel(name = "评分规则id")
    private Long ruleId;

    /** 算法id */
    @Excel(name = "算法id")
    private Long algorithmId;

    /** 数据管理id/图片 */
    @Excel(name = "数据管理id/图片")
    private Long dataManageId;

    /** 文件地址/新图片 */
    @Excel(name = "文件地址/新图片")
    private String casePath;

    /** 代码 */
    @Excel(name = "代码")
    private String code;

    /** 实验结果与分析 */
    @Excel(name = "实验结果与分析")
    private String result;

    /** 实验主体 */
    @Excel(name = "实验主体")
    private String mainBody;

    /** 思考与总结 */
    @Excel(name = "思考与总结")
    private String summary;

    /** 步骤内容 */
    @Excel(name = "步骤内容")
    private String step;

    /** 等级 */
    @Excel(name = "等级")
    private String grade;

    /** 作业id */
    @Excel(name = "作业id")
    private Long workId;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 案例库步骤信息 */
    private List<ExampleStep> exampleStepList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setTaskTitle(String taskTitle) 
    {
        this.taskTitle = taskTitle;
    }

    public String getTaskTitle() 
    {
        return taskTitle;
    }

    public void setTaskDescribe(String taskDescribe) 
    {
        this.taskDescribe = taskDescribe;
    }

    public String getTaskDescribe() 
    {
        return taskDescribe;
    }

    public void setTestTitle(String testTitle) 
    {
        this.testTitle = testTitle;
    }

    public String getTestTitle() 
    {
        return testTitle;
    }

    public void setDataType(String dataType) 
    {
        this.dataType = dataType;
    }

    public String getDataType() 
    {
        return dataType;
    }

    public void setTestDescribe(String testDescribe) 
    {
        this.testDescribe = testDescribe;
    }

    public String getTestDescribe() 
    {
        return testDescribe;
    }

    public void setTheory(String theory) 
    {
        this.theory = theory;
    }

    public String getTheory() 
    {
        return theory;
    }

    public void setRuleId(Long ruleId) 
    {
        this.ruleId = ruleId;
    }

    public Long getRuleId() 
    {
        return ruleId;
    }

    public void setAlgorithmId(Long algorithmId) 
    {
        this.algorithmId = algorithmId;
    }

    public Long getAlgorithmId() 
    {
        return algorithmId;
    }

    public void setDataManageId(Long dataManageId) 
    {
        this.dataManageId = dataManageId;
    }

    public Long getDataManageId() 
    {
        return dataManageId;
    }

    public void setCasePath(String casePath) 
    {
        this.casePath = casePath;
    }

    public String getCasePath() 
    {
        return casePath;
    }

    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
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

    public void setStep(String step) 
    {
        this.step = step;
    }

    public String getStep() 
    {
        return step;
    }

    public void setGrade(String grade) 
    {
        this.grade = grade;
    }

    public String getGrade() 
    {
        return grade;
    }

    public void setWorkId(Long workId) 
    {
        this.workId = workId;
    }

    public Long getWorkId() 
    {
        return workId;
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

    public List<ExampleStep> getExampleStepList()
    {
        return exampleStepList;
    }

    public void setExampleStepList(List<ExampleStep> exampleStepList)
    {
        this.exampleStepList = exampleStepList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskTitle", getTaskTitle())
            .append("taskDescribe", getTaskDescribe())
            .append("testTitle", getTestTitle())
            .append("dataType", getDataType())
            .append("testDescribe", getTestDescribe())
            .append("theory", getTheory())
            .append("ruleId", getRuleId())
            .append("algorithmId", getAlgorithmId())
            .append("dataManageId", getDataManageId())
            .append("casePath", getCasePath())
            .append("code", getCode())
            .append("result", getResult())
            .append("mainBody", getMainBody())
            .append("summary", getSummary())
            .append("step", getStep())
            .append("grade", getGrade())
            .append("workId", getWorkId())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("exampleStepList", getExampleStepList())
            .toString();
    }
}
