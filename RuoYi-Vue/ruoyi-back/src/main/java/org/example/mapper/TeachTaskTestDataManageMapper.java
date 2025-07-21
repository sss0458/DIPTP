package org.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.TeachTaskTestDataManage;

/**
 * 教学任务实验与数据管理关联Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Mapper
public interface TeachTaskTestDataManageMapper 
{
    /**
     * 查询教学任务实验与数据管理关联
     * 
     * @param id 教学任务实验与数据管理关联主键
     * @return 教学任务实验与数据管理关联
     */
    public TeachTaskTestDataManage selectTeachTaskTestDataManageById(Long id);

    /**
     * 查询教学任务实验与数据管理关联列表
     * 
     * @param teachTaskTestDataManage 教学任务实验与数据管理关联
     * @return 教学任务实验与数据管理关联集合
     */
    public List<TeachTaskTestDataManage> selectTeachTaskTestDataManageList(TeachTaskTestDataManage teachTaskTestDataManage);

    /**
     * 新增教学任务实验与数据管理关联
     * 
     * @param teachTaskTestDataManage 教学任务实验与数据管理关联
     * @return 结果
     */
    public int insertTeachTaskTestDataManage(TeachTaskTestDataManage teachTaskTestDataManage);

    /**
     * 修改教学任务实验与数据管理关联
     * 
     * @param teachTaskTestDataManage 教学任务实验与数据管理关联
     * @return 结果
     */
    public int updateTeachTaskTestDataManage(TeachTaskTestDataManage teachTaskTestDataManage);

    /**
     * 删除教学任务实验与数据管理关联
     * 
     * @param id 教学任务实验与数据管理关联主键
     * @return 结果
     */
    public int deleteTeachTaskTestDataManageById(Long id);

    /**
     * 批量删除教学任务实验与数据管理关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachTaskTestDataManageByIds(Long[] ids);
}
