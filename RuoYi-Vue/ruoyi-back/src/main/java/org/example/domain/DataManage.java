package org.example.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 数据管理对象 data_manage
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public class DataManage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 状态:启用/停用 */
    @Excel(name = "状态:启用/停用")
    private String status;

    /** 数据标识 */
    @Excel(name = "数据标识")
    private String sign;

    /** 数据来源 */
    @Excel(name = "数据来源")
    private String source;

    /** 数据类型 */
    @Excel(name = "数据类型")
    private String dataType;

    /** 场景类型 */
    @Excel(name = "场景类型")
    private String sceneType;

    /** 目标类型 */
    @Excel(name = "目标类型")
    private String targetType;

    /** 成像条件 */
    @Excel(name = "成像条件")
    private String imagingType;

    /** 算法 */
    @Excel(name = "算法")
    private Long algorithmId;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String filePath;

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

    public void setSign(String sign) 
    {
        this.sign = sign;
    }

    public String getSign() 
    {
        return sign;
    }

    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }

    public void setDataType(String dataType) 
    {
        this.dataType = dataType;
    }

    public String getDataType() 
    {
        return dataType;
    }

    public void setSceneType(String sceneType) 
    {
        this.sceneType = sceneType;
    }

    public String getSceneType() 
    {
        return sceneType;
    }

    public void setTargetType(String targetType) 
    {
        this.targetType = targetType;
    }

    public String getTargetType() 
    {
        return targetType;
    }

    public void setImagingType(String imagingType) 
    {
        this.imagingType = imagingType;
    }

    public String getImagingType() 
    {
        return imagingType;
    }

    public void setAlgorithmId(Long algorithmId) 
    {
        this.algorithmId = algorithmId;
    }

    public Long getAlgorithmId() 
    {
        return algorithmId;
    }

    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
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
            .append("delFlag", getDelFlag())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("sign", getSign())
            .append("source", getSource())
            .append("dataType", getDataType())
            .append("sceneType", getSceneType())
            .append("targetType", getTargetType())
            .append("imagingType", getImagingType())
            .append("algorithmId", getAlgorithmId())
            .append("filePath", getFilePath())
            .append("dataManageExtendList", getDataManageExtendList())
            .toString();
    }
}
