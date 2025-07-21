package org.example.mapper;

import java.util.List;
import org.example.domain.StudentWorkContent;
import org.example.domain.StudentWorkContentStep;

/**
 * 学生作业内容Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public interface StudentWorkContentMapper 
{



    public List<StudentWorkContentStep> selectStudentWorkContentStepList(Long id);
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
     * 删除学生作业内容
     * 
     * @param id 学生作业内容主键
     * @return 结果
     */
    public int deleteStudentWorkContentById(Long id);

    /**
     * 批量删除学生作业内容
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStudentWorkContentByIds(Long[] ids);

    /**
     * 批量删除学生作业内容步骤
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStudentWorkContentStepByWorkContentIds(Long[] ids);
    
    /**
     * 批量新增学生作业内容步骤
     * 
     * @param studentWorkContentStepList 学生作业内容步骤列表
     * @return 结果
     */
    public int batchStudentWorkContentStep(List<StudentWorkContentStep> studentWorkContentStepList);
    

    /**
     * 通过学生作业内容主键删除学生作业内容步骤信息
     * 
     * @param id 学生作业内容ID
     * @return 结果
     */
    public int deleteStudentWorkContentStepByWorkContentId(Long id);

    List<StudentWorkContent> selectStudentWorkContentListByWordId(Long id);

    List<StudentWorkContentStep> selectStudentWorkContentStepListByContentId(Long id);
}
