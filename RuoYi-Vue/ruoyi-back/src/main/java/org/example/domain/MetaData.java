package org.example.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 元数据管理对象 meta_data
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public class MetaData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 数据名称 */
    @Excel(name = "数据名称")
    private String name;

    /** 内容长度 */
    @Excel(name = "内容长度")
    private Long contentLength;

    /** 是否必填 */
    @Excel(name = "是否必填")
    private String isRequired;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 状态:启用/停用 */
    @Excel(name = "状态:启用/停用")
    private String status;

    /** 数据管理拓展信息 */
    private List<DataManageExtend> dataManageExtendList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setContentLength(Long contentLength) 
    {
        this.contentLength = contentLength;
    }

    public Long getContentLength() 
    {
        return contentLength;
    }

    public void setIsRequired(String isRequired) 
    {
        this.isRequired = isRequired;
    }

    public String getIsRequired() 
    {
        return isRequired;
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

    public List<DataManageExtend> getDataManageExtendList()
    {
        return dataManageExtendList;
    }

    public void setDataManageExtendList(List<DataManageExtend> dataManageExtendList)
    {
        this.dataManageExtendList = dataManageExtendList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("contentLength", getContentLength())
            .append("isRequired", getIsRequired())
            .append("delFlag", getDelFlag())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("dataManageExtendList", getDataManageExtendList())
            .toString();
    }
}
