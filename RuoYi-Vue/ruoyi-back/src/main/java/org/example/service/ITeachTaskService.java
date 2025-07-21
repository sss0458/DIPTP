package org.example.service;

import java.util.List;
import org.example.domain.TeachTask;
import org.springframework.stereotype.Service;

/**
 * 教学任务Service接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public interface ITeachTaskService 
{
    /**
     * 查询教学任务
     * 
     * @param id 教学任务主键
     * @return 教学任务
     */
    public TeachTask selectTeachTaskById(Long id);

    /**
     * 查询教学任务列表
     * 
     * @param teachTask 教学任务
     * @return 教学任务集合
     */
    public List<TeachTask> selectTeachTaskList(TeachTask teachTask);

    /**
     * 新增教学任务
     * 
     * @param teachTask 教学任务
     * @return 结果
     */
    public int insertTeachTask(TeachTask teachTask);

    /**
     * 修改教学任务
     * 
     * @param teachTask 教学任务
     * @return 结果
     */
    public int updateTeachTask(TeachTask teachTask);

    /**
     * 批量删除教学任务
     * 
     * @param ids 需要删除的教学任务主键集合
     * @return 结果
     */
    public int deleteTeachTaskByIds(Long[] ids);

    /**
     * 删除教学任务信息
     * 
     * @param id 教学任务主键
     * @return 结果
     */
    public int deleteTeachTaskById(Long id);

    /**
     * 根据ID列表查询教学任务
     * @param ids 任务ID列表
     * @return 教学任务列表
     */
    public List<TeachTask> selectTeachTaskListByIds(List<Long> ids);
}
