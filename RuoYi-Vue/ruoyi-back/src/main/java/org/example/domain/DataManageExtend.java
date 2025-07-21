package org.example.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 数据管理拓展对象 data_manage_extend
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public class DataManageExtend extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 数据管理id */
    @Excel(name = "数据管理id")
    private Long dataManageId;

    /** 元数据id */
    @Excel(name = "元数据id")
    private Long metaDataId;

    /** 拓展内容 */
    @Excel(name = "拓展内容")
    private String content;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 状态:启用/停用 */
    @Excel(name = "状态:启用/停用")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDataManageId(Long dataManageId) 
    {
        this.dataManageId = dataManageId;
    }

    public Long getDataManageId() 
    {
        return dataManageId;
    }
    public void setMetaDataId(Long metaDataId) 
    {
        this.metaDataId = metaDataId;
    }

    public Long getMetaDataId() 
    {
        return metaDataId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dataManageId", getDataManageId())
            .append("metaDataId", getMetaDataId())
            .append("content", getContent())
            .append("delFlag", getDelFlag())
            .append("status", getStatus())
            .append("remark", getRemark())
            .toString();
    }
}
