package org.example.service;

import org.example.domain.MetaData;

import java.util.List;

/**
 * 元数据管理Service接口
 *
 * @author YourName // TODO: 替换为您的名字
 * @date 2025-07-04 // TODO: 替换为当前日期
 */
public interface MetaDataService
{
    /**
     * 查询元数据管理列表
     *
     * @param metaData 元数据管理
     * @return 元数据管理集合
     */
    public List<MetaData> selectMetaDataList(MetaData metaData);

    /**
     * 查询元数据管理
     *
     * @param id 元数据管理主键
     * @return 元数据管理
     */
    public MetaData selectMetaDataById(Long id);

    /**
     * 新增元数据管理
     *
     * @param metaData 元数据管理
     * @return 结果
     */
    public int insertMetaData(MetaData metaData);

    /**
     * 修改元数据管理
     *
     * @param metaData 元数据管理
     * @return 结果
     */
    public int updateMetaData(MetaData metaData);

    /**
     * 物理删除元数据管理信息
     *
     * @param id 元数据管理主键
     * @return 结果
     */
    public int deleteMetaDataById(Long id);

    /**
     * 批量物理删除元数据管理
     *
     * @param ids 需要删除的元数据管理主键集合
     * @return 结果
     */
    public int deleteMetaDataByIds(Long[] ids);

    /**
     * 根据元数据名称查询元数据定义（完整对象）
     *
     * @param name 元数据名称
     * @return 元数据定义对象
     */
    public MetaData selectMetaDataByName(String name);

    /**
     * 根据元数据名称查询元数据ID
     *
     * @param name 元数据名称
     * @return 元数据ID
     */
    public Long selectMetaDataIdByName(String name);

    /**
     * 逻辑删除单个元数据管理
     *
     * @param id 元数据管理主键
     * @return 结果
     */
    public int updateMetaDataDelFlagById(Long id);

    /**
     * 批量逻辑删除元数据管理
     *
     * @param ids 需要逻辑删除的数据主键集合
     * @return 结果
     */
    public int updateMetaDataDelFlagByIds(List<Long> ids); // 推荐使用 List<Long>
    // 如果您坚持使用 Long[]，请改为：public int updateMetaDataDelFlagByIds(Long[] ids, String updateBy);
}