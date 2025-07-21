package org.example.service;

import java.util.List;
import org.example.domain.TeachTaskTest;
import org.springframework.stereotype.Service;

/**
 * 教学任务实验Service接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public interface ITeachTaskTestService 
{
    /**
     * 查询教学任务实验
     * 
     * @param id 教学任务实验主键
     * @return 教学任务实验
     */
    public TeachTaskTest selectTeachTaskTestById(Long id);

    /**
     * 查询教学任务实验列表
     * 
     * @param teachTaskTest 教学任务实验
     * @return 教学任务实验集合
     */
    public List<TeachTaskTest> selectTeachTaskTestList(TeachTaskTest teachTaskTest);

    /**
     * 新增教学任务实验
     * 
     * @param teachTaskTest 教学任务实验
     * @return 结果
     */
    public int insertTeachTaskTest(TeachTaskTest teachTaskTest);

    /**
     * 修改教学任务实验
     * 
     * @param teachTaskTest 教学任务实验
     * @return 结果
     */
    public int updateTeachTaskTest(TeachTaskTest teachTaskTest);

    /**
     * 批量删除教学任务实验
     * 
     * @param ids 需要删除的教学任务实验主键集合
     * @return 结果
     */
    public int deleteTeachTaskTestByIds(Long[] ids);

    /**
     * 删除教学任务实验信息
     * 
     * @param id 教学任务实验主键
     * @return 结果
     */
    public int deleteTeachTaskTestById(Long id);
}
