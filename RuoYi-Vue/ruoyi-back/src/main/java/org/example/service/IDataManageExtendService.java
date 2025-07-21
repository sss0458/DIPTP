package org.example.service;

import java.util.List;
import org.example.domain.DataManageExtend;
import org.example.domain.vo.DataManageExtendVo;
import org.springframework.stereotype.Service;

/**
 * 数据管理拓展Service接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public interface IDataManageExtendService 
{
    /**
     * 查询数据管理拓展
     * 
     * @param id 数据管理拓展主键
     * @return 数据管理拓展
     */
    public DataManageExtendVo selectDataManageExtendById(Long id);


    /**
     * 查询数据管理拓展列表
     * 
     * @param dataManageExtend 数据管理拓展
     * @return 数据管理拓展集合
     */
    public List<DataManageExtendVo> selectDataManageExtendVoList(DataManageExtend dataManageExtend);

    /**
     * 新增数据管理拓展
     * 
     * @param dataManageExtend 数据管理拓展
     * @return 结果
     */
    public int insertDataManageExtend(DataManageExtend dataManageExtend);

    /**
     * 修改数据管理拓展
     * 
     * @param dataManageExtend 数据管理拓展
     * @return 结果
     */
    public int updateDataManageExtend(DataManageExtend dataManageExtend);

    /**
     * 批量删除数据管理拓展
     * 
     * @param ids 需要删除的数据管理拓展主键集合
     * @return 结果
     */
    public int deleteDataManageExtendByIds(Long[] ids);

    /**
     * 删除数据管理拓展信息
     * 
     * @param id 数据管理拓展主键
     * @return 结果
     */
    public int deleteDataManageExtendById(Long id);

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
     * @return 结果
     */
    public int deleteDataManageExtendByDataManageIds(Long[] dataManageIds);

}
