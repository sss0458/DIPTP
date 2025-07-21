package org.example.service.impl;

import java.util.List;

import org.example.domain.vo.DataManageExtendVo;
import org.example.mapper.DataManageExtendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.domain.DataManageExtend;
import org.example.service.IDataManageExtendService;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据管理拓展Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public class DataManageExtendServiceImpl implements IDataManageExtendService 
{
    @Autowired
    private DataManageExtendMapper dataManageExtendMapper;
    private static final Logger log = LoggerFactory.getLogger(DataManageExtendServiceImpl.class);
    /**
     * 查询数据管理拓展
     * 
     * @param id 数据管理拓展主键
     * @return 数据管理拓展
     */
    @Override
    public DataManageExtendVo selectDataManageExtendById(Long id)
    {
        return dataManageExtendMapper.selectDataManageExtendVoById(id);
    }

    /**
     * 根据主媒体资源ID查询扩展元数据列表
     * ⭐ 修改：返回 List<DataManageExtendVo>
     * @param dataManageId 关联的主表（data_manage）ID
     * @return 扩展元数据集合 (现在返回 DataManageExtendVo，包含 metaDataName)
     */
    @Override
    public List<DataManageExtendVo> selectDataManageExtendByDataManageId(Long dataManageId) {
        // 调用 Mapper 中返回 DataManageExtendVo 的方法
        return dataManageExtendMapper.selectDataManageExtendVoListByDataManageId(dataManageId);
    }

    /**
     * 查询数据管理拓展列表
     * 
     * @param dataManageExtend 数据管理拓展
     * @return 数据管理拓展
     */
    @Override
    public List<DataManageExtendVo> selectDataManageExtendVoList(DataManageExtend dataManageExtend)
    {
        return dataManageExtendMapper.selectDataManageExtendVoList(dataManageExtend);
    }

    /**
     * 新增数据管理拓展
     * 
     * @param dataManageExtend 数据管理拓展
     * @return 结果
     */
    @Override
    public int insertDataManageExtend(DataManageExtend dataManageExtend)
    {
        return dataManageExtendMapper.insertDataManageExtend(dataManageExtend);
    }

    /**
     * 修改数据管理拓展
     * 
     * @param dataManageExtend 数据管理拓展
     * @return 结果
     */
    @Override
    public int updateDataManageExtend(DataManageExtend dataManageExtend)
    {
        return dataManageExtendMapper.updateDataManageExtend(dataManageExtend);
    }

    /**
     * 批量新增媒体资源扩展元数据
     * @param extendList 扩展元数据列表
     * @return 结果（影响的行数）
     */
    @Override
    @Transactional // 批量操作通常需要事务
    public int batchInsertDataManageExtend(List<DataManageExtend> extendList) {
        if (extendList == null || extendList.isEmpty()) {
            return 0; // 没有数据需要插入
        }
        for (DataManageExtend extend : extendList) {
            // 避免重复设置，只在为null时设置，或者强制每次都设置最新时间
                extend.setDelFlag("0"); // 默认未删除
                extend.setStatus("0"); // 确保默认状态为正常
            }

        return dataManageExtendMapper.batchInsertDataManageExtend(extendList);
    }

    /**
     * 批量更新媒体资源扩展元数据
     * (此方法在主Service中通常被“先逻辑删除再批量插入”策略取代，
     * 如果保留，需根据具体业务判断是逐条更新还是其他更复杂的逻辑)
     * @param extendList 扩展元数据列表
     * @return 结果
     */
    @Override
    @Transactional
    public int batchUpdateDataManageExtend(List<DataManageExtend> extendList) {
        // 这里的实现将执行传入列表中每条记录的更新。
        // 如果您的业务逻辑更倾向于"先删后增"，那么这个方法可能不会被直接调用。
        // 如果您确实需要细粒度的批量更新，确保前端传入的 extendList 中的每个 DataManageExtend 对象都有有效的 id。
        if (extendList == null || extendList.isEmpty()) {
            return 0;
        }
        int affectedRows = 0;
        for (DataManageExtend extend : extendList) {
            if (extend.getId() != null) {
                // 如果有ID，就执行更新
                affectedRows += dataManageExtendMapper.updateDataManageExtend(extend);
            } else {
                extend.setDelFlag("0");
                extend.setStatus("0"); // 确保默认状态为正常
                affectedRows += dataManageExtendMapper.insertDataManageExtend(extend);
            }
        }
        return affectedRows;
    }

    /**
     * 根据主媒体资源ID批量逻辑删除扩展元数据
     * @param dataManageIds 需要删除的主数据ID数组
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteDataManageExtendByDataManageIds(Long[] dataManageIds) {
        if (dataManageIds == null || dataManageIds.length == 0) {
            return 0;
        }
        // 2. 调用 Mapper 方法时传递所有三个参数
        return dataManageExtendMapper.updateDataManageExtendDelFlagByDataManageIds(dataManageIds);
    }

    /**
     * 根据扩展元数据ID逻辑删除单条扩展元数据
     * @param id 扩展元数据主键ID
     * @return 结果
     */
    @Override
    public int deleteDataManageExtendById(Long id) {
        if (id == null) {
            log.warn("Attempted to delete DataManageExtend with null ID.");
            return 0; // 或者抛出 IllegalArgumentException
        }
        return dataManageExtendMapper.updateDataManageExtendDelFlagById(id);
    }

    /**
     * 批量删除数据管理拓展
     * 
     * @param ids 需要删除的数据管理拓展主键
     * @return 结果
     */
    @Override
    public int deleteDataManageExtendByIds(Long[] ids)
    {
        return dataManageExtendMapper.deleteDataManageExtendByIds(ids);
    }

}
