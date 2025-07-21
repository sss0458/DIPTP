package org.example.service;

import org.example.domain.DataManageExtend;
import org.example.domain.vo.DataManageExtendVo;

import java.util.List;

/**
 * 媒体资源扩展元数据Service接口
 *
 * @author YourName // TODO: 替换为您的名字
 * @date 2025-07-14 // TODO: 替换为当前日期 (Updated to today's date)
 */
public interface DataManageExtendService
{
    /**
     * 根据主媒体资源ID查询扩展元数据列表
     *
     * @param dataManageId 关联的主表（data_manage）ID
     * @return 扩展元数据集合 (Now returns DataManageExtendVo, which includes metaDataName)
     */
    public List<DataManageExtendVo> selectDataManageExtendByDataManageId(Long dataManageId);

    /**
     * 批量新增媒体资源扩展元数据
     *
     * @param extendList 扩展元数据列表
     * @return 结果
     */
    public int batchInsertDataManageExtend(List<DataManageExtend> extendList);

    /**
     * 批量更新媒体资源扩展元数据
     * (通常在Service层先逻辑删除旧的，再批量插入新的，这里的批量更新是针对已存在的记录进行更新)
     *
     * @param extendList 扩展元数据列表
     * @return 结果
     */
    public int batchUpdateDataManageExtend(List<DataManageExtend> extendList);

    /**
     * 根据主媒体资源ID批量逻辑删除扩展元数据
     *
     * @param dataManageIds 需要删除的主数据ID数组
     * @param updateBy 执行更新操作的用户
     * @return 结果
     */
    public int deleteDataManageExtendByDataManageIds(Long[] dataManageIds , String updateBy);

    /**
     * 根据扩展元数据ID逻辑删除单条扩展元数据
     *
     * @param id 扩展元数据主键ID
     * @param updateBy 执行更新操作的用户
     * @return 结果
     */
    public int deleteDataManageExtendById(Long id , String updateBy);

    /**
     * 新增单条媒体资源扩展元数据
     *
     * @param dataManageExtend 扩展元数据
     * @return 结果
     */
    public int insertDataManageExtend(DataManageExtend dataManageExtend);

    /**
     * 修改单条媒体资源扩展元数据
     *
     * @param dataManageExtend 扩展元数据
     * @return 结果
     */
    public int updateDataManageExtend(DataManageExtend dataManageExtend);

    /**
     * 根据ID查询单条扩展元数据详情
     *
     * @param id 扩展元数据主键ID
     * @return 扩展元数据对象 (Now returns DataManageExtendVo, which includes metaDataName)
     */
    public DataManageExtendVo selectDataManageExtendById(Long id);
}