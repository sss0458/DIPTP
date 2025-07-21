package org.example.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.example.domain.StudentWork;
import org.example.domain.vo.ScoreRuleDimensionVo;
import org.example.domain.vo.StudentWorkScoreVo;
import org.example.mapper.StudentWorkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.mapper.StudentWorkScoreMapper;
import org.example.domain.StudentWorkScore;
import org.example.service.IStudentWorkScoreService;

/**
 * 学生作业评分Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public class StudentWorkScoreServiceImpl implements IStudentWorkScoreService 
{
    @Autowired
    private StudentWorkScoreMapper studentWorkScoreMapper;

    @Autowired
    private StudentWorkMapper studentWorkMapper;

    @Override
    public List<StudentWorkScoreVo> selectStudentWorkScoreVoListByTeacherId(Long id) {
        return studentWorkScoreMapper.selectStudentWorkScoreByTeacherId(id);
    }

    /**
     * 查询学生作业评分
     * 
     * @param id 学生作业评分主键
     * @return 学生作业评分
     */
    @Override
    public StudentWorkScore selectStudentWorkScoreById(Long id)
    {
        return studentWorkScoreMapper.selectStudentWorkScoreById(id);
    }

    /**
     * 查询学生作业评分列表
     * 
     * @param studentWorkScore 学生作业评分
     * @return 学生作业评分
     */
    @Override
    public List<StudentWorkScore> selectStudentWorkScoreList(StudentWorkScore studentWorkScore)
    {
        return studentWorkScoreMapper.selectStudentWorkScoreList(studentWorkScore);
    }

    /**
     * 新增学生作业评分
     * 
     * @param studentWorkScore 学生作业评分
     * @return 结果
     */
    @Override
    public int insertStudentWorkScore(StudentWorkScore studentWorkScore)
    {
        return studentWorkScoreMapper.insertStudentWorkScore(studentWorkScore);
    }

    /**
     * 修改学生作业评分
     *
     * @param studentWorkScore 学生作业评分
     * @return 结果
     */
    @Override
    public int updateStudentWorkScore(StudentWorkScore studentWorkScore)
    {
        StudentWork studentWork = studentWorkMapper.selectStudentWorkById(studentWorkScore.getWorkId());
        BigDecimal primaryScore = studentWorkScoreMapper.selectStudentWorkScoreById(studentWorkScore.getId()).getScore();
        BigDecimal newScore = studentWorkScore.getScore();
        BigDecimal add = newScore.subtract(primaryScore);
        studentWork.setScore(studentWork.getScore().add(add));
        studentWorkMapper.updateStudentWork(studentWork);
        return studentWorkScoreMapper.updateStudentWorkScore(studentWorkScore);
    }
    /**
     * 批量删除学生作业评分
     * 
     * @param ids 需要删除的学生作业评分主键
     * @return 结果
     */
    @Override
    public int deleteStudentWorkScoreByIds(Long[] ids)
    {
        return studentWorkScoreMapper.deleteStudentWorkScoreByIds(ids);
    }

    /**
     * 删除学生作业评分信息
     * 
     * @param id 学生作业评分主键
     * @return 结果
     */
    @Override
    public int deleteStudentWorkScoreById(Long id)
    {
        return studentWorkScoreMapper.deleteStudentWorkScoreById(id);
    }

    /**
     * 根据主键获取视图
     */
    public StudentWorkScoreVo selectStudentWorkScoreVoById(Long id){
        return studentWorkScoreMapper.selectStudentWorkScoreVoById(id);
    }

    /**
     * 获取视图
     */
    public List<StudentWorkScoreVo> selectStudentWorkScoreVoList() {
        return studentWorkScoreMapper.selectStudentWorkScoreVoList();
    }
}
