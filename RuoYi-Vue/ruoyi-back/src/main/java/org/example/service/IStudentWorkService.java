package org.example.service;

import java.io.IOException;
import java.util.List;
import org.example.domain.StudentWork;
import org.example.domain.TeachTaskUser;
import org.example.domain.vo.StudentWorkVo;
import org.example.domain.vo.StudentWorkStatisticVo;

/**
 * 学生作业Service接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public interface IStudentWorkService 
{
    /**
     * 查询学生作业
     * 
     * @param id 学生作业主键
     * @return 学生作业
     */
    public StudentWork selectStudentWorkById(Long id);

    /**
     * 查询学生作业列表
     * 
     * @param studentWork 学生作业
     * @return 学生作业集合
     */
    public List<StudentWork> selectStudentWorkList(StudentWork studentWork);

    /**
     * 新增学生作业
     * 
     * @param studentWork 学生作业
     * @return 结果
     */
    public int insertStudentWork(StudentWork studentWork);

    /**
     * 修改学生作业
     * 
     * @param studentWork 学生作业
     * @return 结果
     */
    public int updateStudentWork(StudentWork studentWork);

    /**
     * 批量删除学生作业
     * 
     * @param ids 需要删除的学生作业主键集合
     * @return 结果
     */
    public int deleteStudentWorkByIds(Long[] ids);

    /**
     * 删除学生作业信息
     * 
     * @param id 学生作业主键
     * @return 结果
     */
    public int deleteStudentWorkById(Long id);


    public StudentWorkVo selectStudentWorkVoById(Long id);

    public List<StudentWorkVo> selectStudentWorkVoList();

    StudentWorkStatisticVo getStatisticSummary(Long taskId, Long testId);

    StudentWork getStudentWorkByTaskAndTestId(Long taskId,Long testId);

    public int GenerateWork(TeachTaskUser teachTaskUser) throws IOException;

}
