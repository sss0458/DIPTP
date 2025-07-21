package org.example.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 评分规则维度对象 score_rule_dimension
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public class ScoreRuleDimension extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 评分规则id */
    @Excel(name = "评分规则id")
    private Long ruleId;

    /** 纬度 */
    @Excel(name = "纬度")
    private String dimension;

    /** 权重 */
    @Excel(name = "权重")
    private Long scale;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

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

    public void setRuleId(Long ruleId) 
    {
        this.ruleId = ruleId;
    }

    public Long getRuleId() 
    {
        return ruleId;
    }

    public void setDimension(String dimension) 
    {
        this.dimension = dimension;
    }

    public String getDimension() 
    {
        return dimension;
    }

    public void setScale(Long scale) 
    {
        this.scale = scale;
    }

    public Long getScale() 
    {
        return scale;
    }

    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
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
            .append("ruleId", getRuleId())
            .append("dimension", getDimension())
            .append("scale", getScale())
            .append("sort", getSort())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
