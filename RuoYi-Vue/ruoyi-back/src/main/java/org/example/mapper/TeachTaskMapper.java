package org.example.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.TeachTask;
import org.example.domain.TeachTaskTest;

/**
 * 教学任务Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Mapper
public interface TeachTaskMapper 
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
     * 删除教学任务
     * 
     * @param id 教学任务主键
     * @return 结果
     */
    public int deleteTeachTaskById(Long id);

    /**
     * 批量删除教学任务
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachTaskByIds(Long[] ids);

    /**
     * 批量删除教学任务实验
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachTaskTestByTaskIds(Long[] ids);
    
    /**
     * 批量新增教学任务实验
     * 
     * @param teachTaskTestList 教学任务实验列表
     * @return 结果
     */
    public int batchTeachTaskTest(List<TeachTaskTest> teachTaskTestList);
    

    /**
     * 通过教学任务主键删除教学任务实验信息
     * 
     * @param id 教学任务ID
     * @return 结果
     */
    public int deleteTeachTaskTestByTaskId(Long id);

    /**
     * 根据ID列表查询教学任务
     * @param ids 任务ID列表
     * @return 教学任务列表
     */
    public List<TeachTask> selectTeachTaskListByIds(List<Long> ids);

    public int markExpiredTasksAsDeleted(Date now);



}
