package org.example.service;

import java.util.List;
import org.example.domain.TeachTaskTestDataManage;
import org.springframework.stereotype.Service;

/**
 * 教学任务实验与数据管理关联Service接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public interface ITeachTaskTestDataManageService 
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
     * 批量删除教学任务实验与数据管理关联
     * 
     * @param ids 需要删除的教学任务实验与数据管理关联主键集合
     * @return 结果
     */
    public int deleteTeachTaskTestDataManageByIds(Long[] ids);

    /**
     * 删除教学任务实验与数据管理关联信息
     * 
     * @param id 教学任务实验与数据管理关联主键
     * @return 结果
     */
    public int deleteTeachTaskTestDataManageById(Long id);
}
