package org.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.domain.DataManage;
import org.example.domain.DataManageExtend;
import org.example.domain.vo.DataManageExtendVo;
import org.example.domain.vo.DataManageVo;

/**
 * 数据管理Mapper接口
 *
 * @author ruoyi
 * @date 2025-07-07
 */
@Mapper
public interface DataManageMapper
{
    /**
     * 查询数据管理
     *
     * @param id 数据管理主键
     * @return 数据管理
     */
    public DataManageVo selectDataManageVoById(Long id);

    /**
     * 查询数据管理列表
     *
     * @param dataManage 数据管理
     * @return 数据管理集合
     */
    public List<DataManageVo> selectDataManageVoList(DataManage dataManage);

    /**
     * 新增数据管理
     *
     * @param dataManage 数据管理
     * @return 结果
     */
    public int insertDataManage(DataManage dataManage);

    public int insertDataManage(DataManageVo dataManage);


    /**
     * 修改数据管理
     *
     * @param dataManage 数据管理
     * @return 结果
     */
    public int updateDataManage(DataManage dataManage);

    /**
     * 删除数据管理
     *
     * @param id 数据管理主键
     * @return 结果
     */
    public int deleteDataManageById(Long id);

    /**
     * 批量删除数据管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDataManageByIds(Long[] ids);

    /**
     * 批量删除数据管理拓展
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDataManageExtendByDataManageIds(Long[] ids);

    /**
     * 批量逻辑删除媒体资源管理
     * 将 del_flag 设置为 '2'
     * （此方法也用于单个逻辑删除，将单个ID放入数组即可）
     *
     * @param ids 需要逻辑删除的数据主键ID数组\
     * @return 结果
     */
    public int updateDataManageDelFlagByIds(@Param("ids") Long[] ids);

    /**
     * 批量新增数据管理拓展
     *
     * @param dataManageExtendList 数据管理拓展列表
     * @return 结果
     */
    public int batchDataManageExtend(List<DataManageExtend> dataManageExtendList);


    /**
     * 通过数据管理主键删除数据管理拓展信息
     *
     * @param id 数据管理ID
     * @return 结果
     */
    public int deleteDataManageExtendByDataManageId(Long id);

    /**
     * 通过数据管理主键删除数据管理拓展信息
     *
     * @param dataType 实验类型/数据类新
     * @return 结果
     */
    public Long selectRandomImageId(String dataType);

    /**
     * 根据ID查询媒体资源管理基本信息（返回原始实体，不加载扩展元数据）
     * 适用于只需要主表少量字段的场景，如视频截图时获取原视频基础信息。
     *
     * @param id 媒体资源管理主键ID
     * @return 媒体资源管理基本信息
     */
    public DataManage selectBasicDataManageById(Long id);

    /**
     * 根据数据类型查询媒体资源列表
     *
     * @param dataType 数据类型 (例如: "VIDEO", "IMAGE", "AUDIO")
     * @return 媒体资源管理列表
     */
    public List<DataManage> selectDataManageListByDataType(@Param("dataType") String dataType);

    /**
     * 根据文件路径查询媒体资源（通常文件路径应是唯一的）
     *
     * @param filePath 文件在服务器上的存储路径
     * @return 媒体资源管理对象，如果存在多个则返回第一个
     */
    public DataManage selectDataManageByFilePath(@Param("filePath") String filePath);

    /**
     * 根据标识符（sign）模糊查询媒体资源列表
     *
     * @param sign 媒体资源的唯一标识符或部分标识符
     * @return 媒体资源管理列表
     */
    public List<DataManage> selectDataManageListBySignLike(@Param("sign") String sign);

    /**
     * 查询指定场景类型下的媒体资源数量
     *
     * @param sceneType 场景类型编码
     * @return 媒体资源数量
     */
    public int selectDataManageCountBySceneType(@Param("sceneType") String sceneType);

    /**
     * 根据状态查询媒体资源列表
     *
     * @param status 状态 (例如: "0"-正常, "1"-禁用)
     * @return 媒体资源管理列表
     */
    public List<DataManage> selectDataManageListByStatus(@Param("status") String status);

    /**
     * 查询所有未被逻辑删除的媒体资源ID列表
     *
     * @return ID列表
     */
    public List<Long> selectAllActiveDataManageIds();

    /**
     * 查询媒体资源数量 (如果需要，例如在一些统计场景)
     *
     * @param dataManage 媒体资源管理对象
     * @return 结果
     */
    public int selectDataManageCount(DataManage dataManage);


}
