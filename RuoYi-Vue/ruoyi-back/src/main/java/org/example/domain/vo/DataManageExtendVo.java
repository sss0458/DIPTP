package org.example.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 媒体资源扩展元数据视图对象 DataManageExtendVo
 * 用于承载媒体资源的扩展元数据信息，通常作为 DataManageVo 的嵌套列表。
 *
 * @author YourName // TODO: 替换为您的名字
 * @date 2025-07-14 // TODO: 替换为当前日期
 */
public class DataManageExtendVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 关联的主表ID (DataManage) */
    @Excel(name = "主数据ID")
    private Long dataManageId;

    /** 元数据键的编码，例如 "original_video_id", "screenshot_time_sec" 等 */
    @Excel(name = "元数据键编码")
    private String metaDataNameCode; // 用于表示元数据键，通常是 String 类型

    /** 元数据键的显示名称，例如 "原始视频ID", "截图时间(秒)" */
    @Excel(name = "元数据键名称")
    private String metaDataName;

    /** 扩展内容 (元数据值)，通常是 String 类型，即使是数字也会被存储为字符串 */
    @Excel(name = "扩展内容")
    private String content;

    /** 逻辑删除标志 (0代表存在 1代表删除) */
    private String delFlag;

    /** 状态 (0=启用,1=禁用) */
    private String status;

    /** 备注 */
    private String remark;

    private Long metaDataId;

    // --- Getter and Setter methods ---


    public Long getMetaDataId() {
        return metaDataId;
    }

    public void setMetaDataId(Long metaDataId) {
        this.metaDataId = metaDataId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDataManageId() {
        return dataManageId;
    }

    public void setDataManageId(Long dataManageId) {
        this.dataManageId = dataManageId;
    }

    public String getMetaDataNameCode() {
        return metaDataNameCode;
    }

    public void setMetaDataNameCode(String metaDataNameCode) {
        this.metaDataNameCode = metaDataNameCode;
    }

    public String getMetaDataName() {
        return metaDataName;
    }

    public void setMetaDataName(String metaDataName) {
        this.metaDataName = metaDataName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("dataManageId", getDataManageId())
                .append("metaDataNameCode", getMetaDataNameCode()) // 添加 metaDataNameCode
                .append("metaDataName", getMetaDataName())       // 添加 metaDataName
                .append("content", getContent())
                .append("delFlag", getDelFlag())
                .append("status", getStatus())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}