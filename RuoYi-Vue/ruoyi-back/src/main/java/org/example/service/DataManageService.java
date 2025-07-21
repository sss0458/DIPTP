package org.example.service;

import java.util.List;

import org.example.domain.DataManage;
import org.example.domain.vo.DataManageVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 媒体资源管理Service接口
 *
 * @author YourName // TODO: 替换为您的名字
 * @date 2025-07-14 // TODO: 替换为当前日期 (已更新为今天日期)
 */
public interface DataManageService
{
    /**
     * 查询媒体资源管理列表
     * 支持多标签组合检索和关键词搜索，以及自定义扩展元数据检索
     *
     * @param dataManage 媒体资源管理对象，包含查询条件
     * @return 媒体资源管理集合 (现在返回 DataManageVo 类型，包含更详细的扩展元数据)
     */
    public List<DataManageVo> selectDataManageList(DataManage dataManage);

    /**
     * 根据ID查询媒体资源管理详情
     * 同时加载其关联的扩展元数据
     *
     * @param id 媒体资源管理主键ID
     * @return 媒体资源管理对象 (现在返回 DataManageVo 类型，包含扩展元数据列表及其元数据名称)
     */
    public DataManageVo selectDataManageById(Long id);

    /**
     * 新增媒体资源管理
     * 同时保存其关联的扩展元数据
     *
     * @param dataManage 媒体资源管理对象，包含主信息和扩展元数据列表
     * @return 结果
     */
    public int insertDataManage(DataManage dataManage);

    /**
     * 修改媒体资源管理
     * 同时更新其关联的扩展元数据
     *
     * @param dataManage 媒体资源管理对象，包含主信息和扩展元数据列表
     * @return 结果
     */
    public int updateDataManage(DataManage dataManage);

    /**
     * 批量逻辑删除媒体资源管理
     * 同时逻辑删除其关联的扩展元数据
     *
     * @param ids 需要删除的数据主键ID数组
     * @return 结果
     */
    public int deleteDataManageByIds(Long[] ids);

    /**
     * 逻辑删除单个媒体资源管理
     * 同时逻辑删除其关联的扩展元数据
     *
     * @param id 媒体资源管理主键ID
     * @return 结果
     */
    public int deleteDataManageById(Long id);


    /**
     * 上传视频截图并生成新的媒体资源记录
     * 该新记录将关联原视频的部分元数据和截图时间点
     *
     * @param file 截图文件
     * @param dataManageId 原视频的ID
     * @param screenshotTime 截图在原视频中的时间点（秒），可以为null
     * @return 新生成的媒体资源对象 (现在返回 DataManageVo 类型，包含完整的元数据信息)，或null如果上传失败
     */
    public DataManageVo uploadScreenshot(MultipartFile file, Long dataManageId, Float screenshotTime);

    /**
     * 导出媒体资源管理数据
     *
     * @param dataManage 查询条件
     * @return 导出的数据列表 (现在返回 DataManageVo 类型，包含更详细的扩展元数据)
     */
    public List<DataManageVo> selectDataManageExport(DataManage dataManage);

    /**
     * 查询媒体资源数量
     *
     * @param dataManage 查询条件
     * @return 媒体资源数量
     */
    public int selectDataManageCount(DataManage dataManage);
}