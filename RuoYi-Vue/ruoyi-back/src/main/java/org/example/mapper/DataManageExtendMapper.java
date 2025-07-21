package org.example.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.domain.DataManageExtend;
import org.example.domain.vo.DataManageExtendVo;

/**
 * 数据管理拓展Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Mapper
public interface DataManageExtendMapper 
{
    /**
     * 查询数据管理拓展
     * 
     * @param id 数据管理拓展主键
     * @return 数据管理拓展
     */
    public DataManageExtend selectDataManageExtendById(Long id);

    /**
     * 查询数据管理拓展列表
     * 
     * @param dataManageExtend 数据管理拓展
     * @return 数据管理拓展集合
     */
    public List<DataManageExtend> selectDataManageExtendList(DataManageExtend dataManageExtend);

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
     * 根据数据管理ID查询其所有扩展元数据（返回原始实体）
     *
     * @param dataManageId 数据管理主键ID
     * @return 数据管理拓展集合
     */
    public List<DataManageExtend> selectDataManageExtendByDataManageId(Long dataManageId);

    /**
     * 查询数据管理拓展列表（返回 VO，包含关联元数据名称）
     * 用于前端需要展示元数据名称等关联信息的情况。
     * @param dataManageExtend 包含查询条件的数据管理拓展对象
     * @return DataManageExtendVo 集合
     */
    public List<DataManageExtendVo> selectDataManageExtendVoList(DataManageExtend dataManageExtend);

    /**
     * 根据ID查询数据管理拓展（返回 VO，包含关联元数据名称）
     * @param id 数据管理拓展主键
     * @return DataManageExtendVo 对象
     */
    public DataManageExtendVo selectDataManageExtendVoById(Long id);


    /**
     * 根据数据管理ID查询其所有扩展元数据（返回 VO，包含关联元数据名称）
     * 此方法将用于 DataManageVo 中嵌套查询 extendMetadata 列表。
     * @param dataManageId 数据管理主键ID
     * @return DataManageExtendVo 集合
     */
    public List<DataManageExtendVo> selectDataManageExtendVoListByDataManageId(Long dataManageId);

    /**
     * 逻辑删除单个数据管理拓展
     * 将 del_flag 设置为 '2'
     *
     * @param id 需要逻辑删除的数据管理拓展主键ID
     * @return 结果
     */
    public int updateDataManageExtendDelFlagById(@Param("id") Long id);

    /**
     * 删除数据管理拓展
     * 
     * @param id 数据管理拓展主键
     * @return 结果
     */
    public int deleteDataManageExtendById(Long id);

    /**
     * 批量插入 DataManageExtend 记录
     * @param list 要插入的 DataManageExtend 对象的列表
     * @return 插入成功的记录数
     */
    int batchInsertDataManageExtend(List<DataManageExtend> list);

    int batchInsertDataManageExtendVo(List<DataManageExtendVo> list);

    /**
     * 根据数据管理ID逻辑删除其所有扩展元数据
     * 将 del_flag 设置为 '2'
     *
     * @param dataManageId 数据管理主键ID
     * @return 结果
     */
    public int updateDataManageExtendDelFlagByDataManageId(@Param("dataManageId") Long dataManageId);

    /**
     * 批量逻辑删除数据管理拓展
     * 将 del_flag 设置为 '2'
     *
     * @param ids 需要逻辑删除的数据主键ID数组
     * @return 结果
     */
    public int updateDataManageExtendDelFlagByIds(@Param("ids") Long[] ids);

    /**
     * 批量根据数据管理ID逻辑删除其所有扩展元数据
     * 将 del_flag 设置为 '2'
     *
     * @param dataManageIds 需要逻辑删除的数据管理主键ID数组
     * @return 结果
     */
    public int updateDataManageExtendDelFlagByDataManageIds(@Param("dataManageIds") Long[] dataManageIds);


    /**
     * 批量删除数据管理拓展
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDataManageExtendByIds(Long[] ids);
}
