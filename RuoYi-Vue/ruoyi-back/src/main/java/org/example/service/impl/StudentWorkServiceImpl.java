package org.example.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.system.mapper.SysUserMapper;
import org.example.domain.DataManage;
import org.example.domain.TeachTask;
import org.example.domain.TeachTaskUser;
import org.example.domain.vo.StudentWorkStatisticVo;
import org.example.domain.vo.StudentWorkVo;
import org.example.mapper.TeachTaskTestMapper;
import org.example.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.mapper.StudentWorkMapper;
import org.example.domain.StudentWork;
import org.example.service.IStudentWorkService;
import org.example.mapper.TeachTaskMapper;

/**
 * 学生作业Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public class StudentWorkServiceImpl implements IStudentWorkService 
{
    @Autowired
    private StudentWorkMapper studentWorkMapper;

    @Autowired
    private TeachTaskMapper teachTaskMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private TeachTaskTestMapper teachTaskTestMapper;

    private FileUtils  utils = new FileUtils();

    @Override
    public StudentWork getStudentWorkByTaskAndTestId(Long taskId, Long testId) {
        return studentWorkMapper.getStudentWorkByTaskAndTestId(taskId,testId);
    }

    @Override
    public int GenerateWork(TeachTaskUser teachTaskUser) throws IOException {
        int count = 0;
        List<Long> studentIds  = sysUserMapper.selectIdsByDeptId(teachTaskUser.getDeptId());
        List<Long> testIds = teachTaskTestMapper.selectTestIdByTaskId(teachTaskUser.getTaskId());
        StudentWork workDemo = new StudentWork();
        workDemo.setTaskId(teachTaskUser.getTaskId());
        workDemo.setQuantity(0L);
        workDemo.setStatus("0");
        workDemo.setCriticizeStatus("0");
        workDemo.setDelFlag("0");
        workDemo.setLastDate(new Date());
        workDemo.setTeacherId(teachTaskUser.getTeacherId());
        workDemo.setScore(BigDecimal.valueOf(0));
        // 添加目录文件datamange
        for (Long sid : studentIds){
            workDemo.setStudentId(sid);
            utils.uploadFileAndReDic(sid);
            for (Long tid : testIds){
                workDemo.setTaskTestId(tid);
                count++;
                studentWorkMapper.insertStudentWork(workDemo);
            }
        }
        return count;
    }

    /**
     * 查询学生作业
     * 
     * @param id 学生作业主键
     * @return 学生作业
     */
    @Override
    public StudentWork selectStudentWorkById(Long id)
    {
        return studentWorkMapper.selectStudentWorkById(id);
    }

    /**
     * 查询学生作业列表
     * 
     * @param studentWork 学生作业
     * @return 学生作业
     */
    @Override
    public List<StudentWork> selectStudentWorkList(StudentWork studentWork)
    {
        return studentWorkMapper.selectStudentWorkList(studentWork);
    }

    /**
     * 新增学生作业
     *
     * @param studentWork 学生作业
     * @return 结果
     */
    //修改
    @Override
    public int insertStudentWork(StudentWork studentWork)
    {
        TeachTask task = teachTaskMapper.selectTeachTaskById(studentWork.getTaskId());

        if (task.isExpired()) {
            throw new RuntimeException("已超过截止时间，无法提交作业");
        }
        return studentWorkMapper.insertStudentWork(studentWork);
    }
    /**
     * 修改学生作业
     * 
     * @param studentWork 学生作业
     * @return 结果
     */
    @Override
    public int updateStudentWork(StudentWork studentWork)
    {
        return studentWorkMapper.updateStudentWork(studentWork);
    }

    /**
     * 批量删除学生作业
     * 
     * @param ids 需要删除的学生作业主键
     * @return 结果
     */
    @Override
    public int deleteStudentWorkByIds(Long[] ids)
    {
        return studentWorkMapper.deleteStudentWorkByIds(ids);
    }

    /**
     * 删除学生作业信息
     * 
     * @param id 学生作业主键
     * @return 结果
     */
    @Override
    public int deleteStudentWorkById(Long id)
    {
        return studentWorkMapper.deleteStudentWorkById(id);
    }

    public StudentWorkVo selectStudentWorkVoById(Long id){
         return studentWorkMapper.selectStudentWorkVoById(id);
    }
    @Override
    public List<StudentWorkVo> selectStudentWorkVoList() {
        return studentWorkMapper.selectStudentWorkVoList();
    }

    @Override
    public StudentWorkStatisticVo getStatisticSummary(Long taskId, Long testId) {
        return studentWorkMapper.getStatisticSummary(taskId,testId);
    }
}
