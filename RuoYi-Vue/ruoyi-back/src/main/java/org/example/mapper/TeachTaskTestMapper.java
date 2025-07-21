package org.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.TeachTaskTest;
import org.example.domain.TeachTaskTestDataManage;

/**
 * 教学任务实验Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Mapper
public interface TeachTaskTestMapper
{

    public List<Long> selectTestIdByTaskId(Long id);
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
     * 删除教学任务实验
     * 
     * @param id 教学任务实验主键
     * @return 结果
     */
    public int deleteTeachTaskTestById(Long id);

    /**
     * 批量删除教学任务实验
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachTaskTestByIds(Long[] ids);

    /**
     * 批量删除教学任务实验与数据管理关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachTaskTestDataManageByTaskTestIds(Long[] ids);
    
    /**
     * 批量新增教学任务实验与数据管理关联
     * 
     * @param teachTaskTestDataManageList 教学任务实验与数据管理关联列表
     * @return 结果
     */
    public int batchTeachTaskTestDataManage(List<TeachTaskTestDataManage> teachTaskTestDataManageList);
    

    /**
     * 通过教学任务实验主键删除教学任务实验与数据管理关联信息
     * 
     * @param id 教学任务实验ID
     * @return 结果
     */
    public int deleteTeachTaskTestDataManageByTaskTestId(Long id);


    List<TeachTaskTest> selectTeachTaskTestListByTaskId(Long id);

}
