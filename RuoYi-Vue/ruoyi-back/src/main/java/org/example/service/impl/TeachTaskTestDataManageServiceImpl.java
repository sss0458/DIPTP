package org.example.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.mapper.TeachTaskTestDataManageMapper;
import org.example.domain.TeachTaskTestDataManage;
import org.example.service.ITeachTaskTestDataManageService;

/**
 * 教学任务实验与数据管理关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public class TeachTaskTestDataManageServiceImpl implements ITeachTaskTestDataManageService 
{
    @Autowired
    private TeachTaskTestDataManageMapper teachTaskTestDataManageMapper;

    /**
     * 查询教学任务实验与数据管理关联
     * 
     * @param id 教学任务实验与数据管理关联主键
     * @return 教学任务实验与数据管理关联
     */
    @Override
    public TeachTaskTestDataManage selectTeachTaskTestDataManageById(Long id)
    {
        return teachTaskTestDataManageMapper.selectTeachTaskTestDataManageById(id);
    }

    /**
     * 查询教学任务实验与数据管理关联列表
     * 
     * @param teachTaskTestDataManage 教学任务实验与数据管理关联
     * @return 教学任务实验与数据管理关联
     */
    @Override
    public List<TeachTaskTestDataManage> selectTeachTaskTestDataManageList(TeachTaskTestDataManage teachTaskTestDataManage)
    {
        return teachTaskTestDataManageMapper.selectTeachTaskTestDataManageList(teachTaskTestDataManage);
    }

    /**
     * 新增教学任务实验与数据管理关联
     * 
     * @param teachTaskTestDataManage 教学任务实验与数据管理关联
     * @return 结果
     */
    @Override
    public int insertTeachTaskTestDataManage(TeachTaskTestDataManage teachTaskTestDataManage)
    {
        return teachTaskTestDataManageMapper.insertTeachTaskTestDataManage(teachTaskTestDataManage);
    }

    /**
     * 修改教学任务实验与数据管理关联
     * 
     * @param teachTaskTestDataManage 教学任务实验与数据管理关联
     * @return 结果
     */
    @Override
    public int updateTeachTaskTestDataManage(TeachTaskTestDataManage teachTaskTestDataManage)
    {
        return teachTaskTestDataManageMapper.updateTeachTaskTestDataManage(teachTaskTestDataManage);
    }

    /**
     * 批量删除教学任务实验与数据管理关联
     * 
     * @param ids 需要删除的教学任务实验与数据管理关联主键
     * @return 结果
     */
    @Override
    public int deleteTeachTaskTestDataManageByIds(Long[] ids)
    {
        return teachTaskTestDataManageMapper.deleteTeachTaskTestDataManageByIds(ids);
    }

    /**
     * 删除教学任务实验与数据管理关联信息
     * 
     * @param id 教学任务实验与数据管理关联主键
     * @return 结果
     */
    @Override
    public int deleteTeachTaskTestDataManageById(Long id)
    {
        return teachTaskTestDataManageMapper.deleteTeachTaskTestDataManageById(id);
    }
}
