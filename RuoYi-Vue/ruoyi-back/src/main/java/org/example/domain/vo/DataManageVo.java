package org.example.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.example.domain.DataManage;

import java.util.List;


/**
 * 媒体资源管理对象 data_manage
 *
 * @author YourName // TODO: 替换为您的名字
 * @date 2025-06-30 // TODO: 替换为当前日期
 */
public class DataManageVo extends BaseEntity { // 继承BaseEntity以获取createTime, updateTime等通用字段
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 逻辑删除标志 (0代表存在 1代表删除) */
    @Excel(name = "删除标志", readConverterExp = "0=存在,1=删除")
    private String delFlag;

    /** 状态 (0=启用,1=禁用) */
    @Excel(name = "状态", readConverterExp = "0=启用,1=禁用")
    private String status;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 数据标识 (如文件MD5或SHA256，用于去重和校验) */
    @Excel(name = "数据标识")
    private String sign;

    /** 数据来源 */
    @Excel(name = "数据来源")
    private String source;

    /** 数据类型 (IMAGE, VIDEO等) */
    @Excel(name = "数据类型", readConverterExp = "IMAGE=图片,VIDEO=视频,VISIBLE_LIGHT=可见光,INFRARED=红外,MULTISPECTRAL=多光谱,HYPERSPECTRAL=高光谱")
    private String dataType; // 数据库中是char(20)，但实际可能需要枚举或更长的字符串

    /** 场景类型 (海面/陆地/空中/水下) */
    @Excel(name = "场景类型", dictType = "sys_scene_type") // 假设在RuoYi字典中配置了sys_scene_type
    private String sceneType;

    /** 目标类型 (飞机/舰船等) */
    @Excel(name = "目标类型", dictType = "sys_target_type") // 假设在RuoYi字典中配置了sys_target_type
    private String targetType;

    /** 成像条件 (光照/温度等) */
    @Excel(name = "成像条件", dictType = "sys_imaging_condition") // 假设在RuoYi字典中配置了sys_imaging_condition
    private String imagingType;

    /** 算法ID */
    @Excel(name = "算法ID")
    private Long algorithmId;

    /** 文件地址 (OSS或其他存储路径) */
    @Excel(name = "文件地址")
    private String filePath;

    /** 算法备注 */
    private String algorithmRemark;

    /**
     * **【核心修改】** 扩展元数据列表，现在明确为 DataManageExtendVo 类型
     */
    private List<DataManageExtendVo> extendMetadata; // <-- 【这里是关键修改！】

    // --- Getter and Setter methods ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getSceneType() {
        return sceneType;
    }

    public void setSceneType(String sceneType) {
        this.sceneType = sceneType;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getImagingType() {
        return imagingType;
    }

    public void setImagingType(String imagingType) {
        this.imagingType = imagingType;
    }

    public Long getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(Long algorithmId) {
        this.algorithmId = algorithmId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    // 【核心修改】Getter 和 Setter 方法也必须使用 DataManageExtendVo
    public List<DataManageExtendVo> getExtendMetadata() { // <-- 【这里是关键修改！】
        return extendMetadata;
    }

    public void setExtendMetadata(List<DataManageExtendVo> extendMetadata) { // <-- 【这里是关键修改！】
        this.extendMetadata = extendMetadata;
    }

    public String getAlgorithmRemark() {
        return algorithmRemark;
    }

    public void setAlgorithmRemark(String algorithmRemark) {
        this.algorithmRemark = algorithmRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
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
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("algorithmRemark" , getAlgorithmRemark())
                .append("extendMetadata", getExtendMetadata()) // 确保 toString() 也能正确处理嵌套列表
                .toString();
    }
}