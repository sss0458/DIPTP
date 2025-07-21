package org.example.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.example.domain.TeachTaskTestDataManage;
import org.example.mapper.TeachTaskTestMapper;
import org.example.domain.TeachTaskTest;
import org.example.service.ITeachTaskTestService;

/**
 * 教学任务实验Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public class TeachTaskTestServiceImpl implements ITeachTaskTestService 
{
    @Autowired
    private TeachTaskTestMapper teachTaskTestMapper;

    /**
     * 查询教学任务实验
     * 
     * @param id 教学任务实验主键
     * @return 教学任务实验
     */
    @Override
    public TeachTaskTest selectTeachTaskTestById(Long id)
    {
        return teachTaskTestMapper.selectTeachTaskTestById(id);
    }

    /**
     * 查询教学任务实验列表
     * 
     * @param teachTaskTest 教学任务实验
     * @return 教学任务实验
     */
    @Override
    public List<TeachTaskTest> selectTeachTaskTestList(TeachTaskTest teachTaskTest)
    {
        return teachTaskTestMapper.selectTeachTaskTestList(teachTaskTest);
    }

    /**
     * 新增教学任务实验
     * 
     * @param teachTaskTest 教学任务实验
     * @return 结果
     */
    @Transactional
    @Override
    public int insertTeachTaskTest(TeachTaskTest teachTaskTest)
    {
        int rows = teachTaskTestMapper.insertTeachTaskTest(teachTaskTest);
        insertTeachTaskTestDataManage(teachTaskTest);
        return rows;
    }

    /**
     * 修改教学任务实验
     * 
     * @param teachTaskTest 教学任务实验
     * @return 结果
     */
    @Transactional
    @Override
    public int updateTeachTaskTest(TeachTaskTest teachTaskTest)
    {
        teachTaskTestMapper.deleteTeachTaskTestDataManageByTaskTestId(teachTaskTest.getId());
        insertTeachTaskTestDataManage(teachTaskTest);
        return teachTaskTestMapper.updateTeachTaskTest(teachTaskTest);
    }

    /**
     * 批量删除教学任务实验
     * 
     * @param ids 需要删除的教学任务实验主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTeachTaskTestByIds(Long[] ids)
    {
        teachTaskTestMapper.deleteTeachTaskTestDataManageByTaskTestIds(ids);
        return teachTaskTestMapper.deleteTeachTaskTestByIds(ids);
    }

    /**
     * 删除教学任务实验信息
     * 
     * @param id 教学任务实验主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTeachTaskTestById(Long id)
    {
        teachTaskTestMapper.deleteTeachTaskTestDataManageByTaskTestId(id);
        return teachTaskTestMapper.deleteTeachTaskTestById(id);
    }

    /**
     * 新增教学任务实验与数据管理关联信息
     * 
     * @param teachTaskTest 教学任务实验对象
     */
    public void insertTeachTaskTestDataManage(TeachTaskTest teachTaskTest)
    {
        List<TeachTaskTestDataManage> teachTaskTestDataManageList = teachTaskTest.getTeachTaskTestDataManageList();
        Long id = teachTaskTest.getId();
        if (StringUtils.isNotNull(teachTaskTestDataManageList))
        {
            List<TeachTaskTestDataManage> list = new ArrayList<TeachTaskTestDataManage>();
            for (TeachTaskTestDataManage teachTaskTestDataManage : teachTaskTestDataManageList)
            {
                teachTaskTestDataManage.setTaskTestId(id);
                list.add(teachTaskTestDataManage);
            }
            if (list.size() > 0)
            {
                teachTaskTestMapper.batchTeachTaskTestDataManage(list);
            }
        }
    }
}
