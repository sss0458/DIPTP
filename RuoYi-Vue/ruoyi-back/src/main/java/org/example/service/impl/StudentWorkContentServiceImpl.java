package org.example.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.example.domain.StudentWorkContentStep;
import org.example.mapper.StudentWorkContentMapper;
import org.example.domain.StudentWorkContent;
import org.example.service.IStudentWorkContentService;

/**
 * 学生作业内容Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public class StudentWorkContentServiceImpl implements IStudentWorkContentService 
{
    @Autowired
    private StudentWorkContentMapper studentWorkContentMapper;


    @Override
    public List<StudentWorkContent> selectStudentWorkContentListByWordId(Long id) {
        return studentWorkContentMapper.selectStudentWorkContentListByWordId(id);
    }

    public List<StudentWorkContentStep> selectStudentWorkContentStepListByContentId(Long id) {
        return studentWorkContentMapper.selectStudentWorkContentStepListByContentId(id);
    }

    @Override
    public List<StudentWorkContentStep> selectStepsByContentId(Long id){
        return studentWorkContentMapper.selectStudentWorkContentStepList(id);
    }
    /**
     * 查询学生作业内容
     * 
     * @param id 学生作业内容主键
     * @return 学生作业内容
     */
    @Override
    public StudentWorkContent selectStudentWorkContentById(Long id)
    {
        return studentWorkContentMapper.selectStudentWorkContentById(id);
    }

    /**
     * 查询学生作业内容列表
     * 
     * @param studentWorkContent 学生作业内容
     * @return 学生作业内容
     */
    @Override
    public List<StudentWorkContent> selectStudentWorkContentList(StudentWorkContent studentWorkContent)
    {
        return studentWorkContentMapper.selectStudentWorkContentList(studentWorkContent);
    }

    /**
     * 新增学生作业内容
     * 
     * @param studentWorkContent 学生作业内容
     * @return 结果
     */
    @Transactional
    @Override
    public int insertStudentWorkContent(StudentWorkContent studentWorkContent)
    {
        int rows = studentWorkContentMapper.insertStudentWorkContent(studentWorkContent);
        insertStudentWorkContentStep(studentWorkContent);
        return rows;
    }

    /**
     * 修改学生作业内容
     * 
     * @param studentWorkContent 学生作业内容
     * @return 结果
     */
    @Transactional
    @Override
    public int updateStudentWorkContent(StudentWorkContent studentWorkContent)
    {
        studentWorkContentMapper.deleteStudentWorkContentStepByWorkContentId(studentWorkContent.getId());
        insertStudentWorkContentStep(studentWorkContent);
        return studentWorkContentMapper.updateStudentWorkContent(studentWorkContent);
    }

    /**
     * 批量删除学生作业内容
     * 
     * @param ids 需要删除的学生作业内容主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStudentWorkContentByIds(Long[] ids)
    {
        studentWorkContentMapper.deleteStudentWorkContentStepByWorkContentIds(ids);
        return studentWorkContentMapper.deleteStudentWorkContentByIds(ids);
    }

    /**
     * 删除学生作业内容信息
     * 
     * @param id 学生作业内容主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStudentWorkContentById(Long id)
    {
        studentWorkContentMapper.deleteStudentWorkContentStepByWorkContentId(id);
        return studentWorkContentMapper.deleteStudentWorkContentById(id);
    }

    /**
     * 新增学生作业内容步骤信息
     * 
     * @param studentWorkContent 学生作业内容对象
     */
    public void insertStudentWorkContentStep(StudentWorkContent studentWorkContent)
    {
        List<StudentWorkContentStep> studentWorkContentStepList = studentWorkContent.getStudentWorkContentStepList();
        Long id = studentWorkContent.getId();
        if (StringUtils.isNotNull(studentWorkContentStepList))
        {
            List<StudentWorkContentStep> list = new ArrayList<StudentWorkContentStep>();
            for (StudentWorkContentStep studentWorkContentStep : studentWorkContentStepList)
            {
                studentWorkContentStep.setWorkContentId(id);
                list.add(studentWorkContentStep);
            }
            if (list.size() > 0)
            {
                studentWorkContentMapper.batchStudentWorkContentStep(list);
            }
        }
    }
}
