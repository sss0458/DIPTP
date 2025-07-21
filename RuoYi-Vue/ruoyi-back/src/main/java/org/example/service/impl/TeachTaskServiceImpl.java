package org.example.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.example.domain.ScoreRule;
import org.example.domain.TeachTaskTestDataManage;
import org.example.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.example.domain.TeachTaskTest;
import org.example.domain.TeachTask;
import org.example.service.ITeachTaskService;

/**
 * 教学任务Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public class TeachTaskServiceImpl implements ITeachTaskService 
{
    @Autowired
    private TeachTaskMapper teachTaskMapper;

    @Autowired
    private DataManageMapper dataManageMapper;

    @Autowired
    private TeachTaskTestDataManageMapper teachTaskTestDataManageMapper;

    @Autowired
    private ScoreRuleMapper scoreRuleMapper;

    @Autowired
    private TeachTaskTestDataManageMapper taskTestDataMapper;

    @Autowired
    private TeachTaskTestMapper teachTaskTestMapper;

    //修改
    @Transactional
    public void distributeExperiment(Long taskId, TeachTaskTest experiment) {
        // 1. 保存评分规则
        ScoreRule rule = scoreRuleMapper.selectScoreRuleById(experiment.getRuleId());
        if (rule != null) {
            scoreRuleMapper.insertScoreRule(rule);
            experiment.setRuleId(rule.getId());
        }

        // 3. 随机分配图片库
        List<Long> dataIds = experiment.getDataManageIds();
        if (dataIds != null && !dataIds.isEmpty()) {
            // 打乱顺序实现随机分配
            Collections.shuffle(dataIds);

            // 保存关联关系
            List<TeachTaskTestDataManage> relations = dataIds.stream()
                    .map(id -> {
                        TeachTaskTestDataManage rel = new TeachTaskTestDataManage();
                        rel.setTaskId(taskId);
                        rel.setTaskTestId(experiment.getId());
                        rel.setDataManageId(id);
                        return rel;
                    })
                    .collect(Collectors.toList());

            for(TeachTaskTestDataManage teachTaskTestDataManage : relations){
                taskTestDataMapper.insertTeachTaskTestDataManage(teachTaskTestDataManage);
            }
        }

    }


    /**
     * 查询教学任务
     * 
     * @param id 教学任务主键
     * @return 教学任务
     */
    @Override
    public TeachTask selectTeachTaskById(Long id)
    {
        return teachTaskMapper.selectTeachTaskById(id);
    }

    /**
     * 查询教学任务列表
     * 
     * @param teachTask 教学任务
     * @return 教学任务
     */
    @Override
    public List<TeachTask> selectTeachTaskList(TeachTask teachTask)
    {
        return teachTaskMapper.selectTeachTaskList(teachTask);
    }

    /**
     * 新增教学任务
     * 
     * @param teachTask 教学任务
     * @return 结果
     */
    //修改
    @Transactional
    @Override
    public int insertTeachTask(TeachTask teachTask)
    {
        if (teachTask.getEndDate().before(new Date())) {
            throw new RuntimeException("截止时间不能早于当前时间");
        }
        int rows = teachTaskMapper.insertTeachTask(teachTask);
        List<TeachTaskTest> teachTaskTestList = teachTaskTestMapper.selectTeachTaskTestListByTaskId(teachTask.getId());
        insertTeachTaskTest(teachTask, teachTaskTestList);
        return rows;
    }

    /**
     * 修改教学任务
     * 
     * @param teachTask 教学任务
     * @return 结果
     */
    //修改
    @Transactional
    @Override
    public int updateTeachTask(TeachTask teachTask)
    {
        TeachTask existing = teachTaskMapper.selectTeachTaskById(teachTask.getId());
        if (existing.isExpired()) {
            throw new RuntimeException("已过期的任务不可修改");
        }
        if (teachTask.getEndDate().before(new Date())) {
            throw new RuntimeException("截止时间不能早于当前时间");
        }
        List<TeachTaskTest> teachTaskTestList = teachTaskTestMapper.selectTeachTaskTestListByTaskId(teachTask.getId());
        teachTaskMapper.deleteTeachTaskTestByTaskId(teachTask.getId());
        insertTeachTaskTest(teachTask, teachTaskTestList);
        return teachTaskMapper.updateTeachTask(teachTask);
    }

    /**
     * 批量删除教学任务
     * 
     * @param ids 需要删除的教学任务主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTeachTaskByIds(Long[] ids)
    {
        teachTaskMapper.deleteTeachTaskTestByTaskIds(ids);
        return teachTaskMapper.deleteTeachTaskByIds(ids);
    }

    /**
     * 删除教学任务信息
     * 
     * @param id 教学任务主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTeachTaskById(Long id)
    {
        teachTaskMapper.deleteTeachTaskTestByTaskId(id);
        return teachTaskMapper.deleteTeachTaskById(id);
    }

    /**
     * 新增教学任务实验信息
     * 
     * @param teachTask 教学任务对象
     */
    //修改
    public void insertTeachTaskTest(TeachTask teachTask, List<TeachTaskTest> list1)
    {
        List<TeachTaskTest> teachTaskTestList = list1;
        Long id = teachTask.getId();
        Long teacherId = teachTask.getTeacherId();
        if (StringUtils.isNotNull(teachTaskTestList))
        {
            List<TeachTaskTest> list = new ArrayList<TeachTaskTest>();
            for (TeachTaskTest teachTaskTest : teachTaskTestList)
            {
                String s = teachTaskTest.getDataType();
                Long testId = teachTaskTest.getId();
                Long imageId = dataManageMapper.selectRandomImageId(s);
                TeachTaskTestDataManage teachTaskTestDataManage = new TeachTaskTestDataManage();
                teachTaskTestDataManage.setTaskId(id);
                teachTaskTestDataManage.setTaskTestId(testId);
                teachTaskTestDataManage.setDataManageId(imageId);
                teachTaskTestDataManage.setTeacherId(teacherId);
                teachTaskTest.setTaskId(id);
                teachTaskTestDataManageMapper.insertTeachTaskTestDataManage(teachTaskTestDataManage);
                list.add(teachTaskTest);
            }
            if (list.size() > 0)
            {
                teachTaskMapper.batchTeachTaskTest(list);
            }
            for (TeachTaskTest experiment : teachTaskTestList) {
                distributeExperiment(teachTask.getId(), experiment);
            }
        }
    }

    @Override
    public List<TeachTask> selectTeachTaskListByIds(List<Long> ids) {
        return teachTaskMapper.selectTeachTaskListByIds(ids);
    }

}
