package org.example.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.beans.Transient;
import java.util.List;

/**
 * 教学任务实验对象 teach_task_test
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public class TeachTaskTest extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 任务id */
    @Excel(name = "任务id")
    private Long taskId;

    /** 实验标题 */
    @Excel(name = "实验标题")
    private String title;

    /** 实验类型 */
    @Excel(name = "实验类型")
    private String dataType;

    /** 实验描述/目的及要求 */
    @Excel(name = "实验描述/目的及要求")
    private String describe;

    /** 实验原理 */
    @Excel(name = "实验原理")
    private String theory;

    /** 算法id */
    @Excel(name = "算法id")
    private Long algorithmId;

    /** 案例库id */
    @Excel(name = "案例库id")
    private Long caseId;

    /** 评分规则id */
    @Excel(name = "评分规则id")
    private Long ruleId;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    private List<Long> dataManageIds; // 关联的数据管理ID列表

    private ScoreRule scoreRule; // 评分规则对象

    public List<TeachTaskTestDataManage> getTeachTaskTestDataManageList() {
        return TeachTaskTestDataManageList;
    }

    public void setTeachTaskTestDataManageList(List<TeachTaskTestDataManage> teachTaskTestDataManageList) {
        TeachTaskTestDataManageList = teachTaskTestDataManageList;
    }

    public List<Long> getDataManageIds() {
        return dataManageIds;
    }

    public void setDataManageIds(List<Long> dataManageIds) {
        this.dataManageIds = dataManageIds;
    }

    public ScoreRule getScoreRule() {
        return scoreRule;
    }

    public void setScoreRule(ScoreRule scoreRule) {
        this.scoreRule = scoreRule;
    }

    /** 教学任务与数据关联*/
    private List<TeachTaskTestDataManage> TeachTaskTestDataManageList;

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
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setDataType(String dataType) 
    {
        this.dataType = dataType;
    }

    public String getDataType() 
    {
        return dataType;
    }
    public void setDescribe(String describe) 
    {
        this.describe = describe;
    }

    public String getDescribe() 
    {
        return describe;
    }
    public void setTheory(String theory) 
    {
        this.theory = theory;
    }

    public String getTheory() 
    {
        return theory;
    }
    public void setAlgorithmId(Long algorithmId) 
    {
        this.algorithmId = algorithmId;
    }

    public Long getAlgorithmId() 
    {
        return algorithmId;
    }
    public void setCaseId(Long caseId) 
    {
        this.caseId = caseId;
    }

    public Long getCaseId() 
    {
        return caseId;
    }
    public void setRuleId(Long ruleId) 
    {
        this.ruleId = ruleId;
    }

    public Long getRuleId() 
    {
        return ruleId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("title", getTitle())
            .append("dataType", getDataType())
            .append("describe", getDescribe())
            .append("theory", getTheory())
            .append("algorithmId", getAlgorithmId())
            .append("caseId", getCaseId())
            .append("ruleId", getRuleId())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }

}
