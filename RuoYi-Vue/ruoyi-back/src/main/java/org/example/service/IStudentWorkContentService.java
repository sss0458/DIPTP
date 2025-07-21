package org.example.service;

import java.util.List;
import org.example.domain.StudentWorkContent;
import org.example.domain.StudentWorkContentStep;

/**
 * 学生作业内容Service接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public interface IStudentWorkContentService
{

    public void insertStudentWorkContentStep(StudentWorkContent content);

    public List<StudentWorkContentStep> selectStepsByContentId(Long id);
    /**
     * 查询学生作业内容
     * 
     * @param id 学生作业内容主键
     * @return 学生作业内容
     */
    public StudentWorkContent selectStudentWorkContentById(Long id);

    /**
     * 查询学生作业内容列表
     * 
     * @param studentWorkContent 学生作业内容
     * @return 学生作业内容集合
     */
    public List<StudentWorkContent> selectStudentWorkContentList(StudentWorkContent studentWorkContent);

    /**
     * 新增学生作业内容
     * 
     * @param studentWorkContent 学生作业内容
     * @return 结果
     */
    public int insertStudentWorkContent(StudentWorkContent studentWorkContent);

    /**
     * 修改学生作业内容
     * 
     * @param studentWorkContent 学生作业内容
     * @return 结果
     */
    public int updateStudentWorkContent(StudentWorkContent studentWorkContent);

    /**
     * 批量删除学生作业内容
     * 
     * @param ids 需要删除的学生作业内容主键集合
     * @return 结果
     */
    public int deleteStudentWorkContentByIds(Long[] ids);

    /**
     * 删除学生作业内容信息
     * 
     * @param id 学生作业内容主键
     * @return 结果
     */
    public int deleteStudentWorkContentById(Long id);

    public List<StudentWorkContent> selectStudentWorkContentListByWordId(Long id);

    public List<StudentWorkContentStep> selectStudentWorkContentStepListByContentId(Long id);
}
