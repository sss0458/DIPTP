package org.example.mapper;

import java.util.List;
import org.example.domain.StudentWorkScore;
import org.example.domain.vo.ScoreRuleDimensionVo;
import org.example.domain.vo.StudentWorkScoreVo;

/**
 * 学生作业评分Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public interface StudentWorkScoreMapper 
{
    /**
     * 查询学生作业评分
     * 
     * @param id 学生作业评分主键
     * @return 学生作业评分
     */
    public StudentWorkScore selectStudentWorkScoreById(Long id);

    /**
     * 查询学生作业评分列表
     * 
     * @param studentWorkScore 学生作业评分
     * @return 学生作业评分集合
     */
    public List<StudentWorkScore> selectStudentWorkScoreList(StudentWorkScore studentWorkScore);

    /**
     * 新增学生作业评分
     * 
     * @param studentWorkScore 学生作业评分
     * @return 结果
     */
    public int insertStudentWorkScore(StudentWorkScore studentWorkScore);

    /**
     * 修改学生作业评分
     * 
     * @param studentWorkScore 学生作业评分
     * @return 结果
     */
    public int updateStudentWorkScore(StudentWorkScore studentWorkScore);

    /**
     * 删除学生作业评分
     * 
     * @param id 学生作业评分主键
     * @return 结果
     */
    public int deleteStudentWorkScoreById(Long id);

    /**
     * 批量删除学生作业评分
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStudentWorkScoreByIds(Long[] ids);

    /**
     * 通过主键获取视图
     *
     * @param id 案例库ID
     * @return 结果
     */
    public StudentWorkScoreVo selectStudentWorkScoreVoById(Long id);


    /**
     * 获取视图
     *
     * @return 结果
     */
    public List<StudentWorkScoreVo> selectStudentWorkScoreVoList();


    public List<StudentWorkScoreVo> selectStudentWorkScoreByTeacherId(Long id);
}

