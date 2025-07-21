package org.example.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.mapper.ExampleStepMapper;
import org.example.domain.ExampleStep;
import org.example.service.IExampleStepService;

/**
 * 案例库步骤Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public class ExampleStepServiceImpl implements IExampleStepService 
{
    @Autowired
    private ExampleStepMapper exampleStepMapper;

    /**
     * 查询案例库步骤
     * 
     * @param id 案例库步骤主键
     * @return 案例库步骤
     */
    @Override
    public ExampleStep selectExampleStepById(Long id)
    {
        return exampleStepMapper.selectExampleStepById(id);
    }

    /**
     * 查询案例库步骤列表
     * 
     * @param exampleStep 案例库步骤
     * @return 案例库步骤
     */
    @Override
    public List<ExampleStep> selectExampleStepList(ExampleStep exampleStep)
    {
        return exampleStepMapper.selectExampleStepList(exampleStep);
    }

    /**
     * 新增案例库步骤
     * 
     * @param exampleStep 案例库步骤
     * @return 结果
     */
    @Override
    public int insertExampleStep(ExampleStep exampleStep)
    {
        return exampleStepMapper.insertExampleStep(exampleStep);
    }

    /**
     * 修改案例库步骤
     * 
     * @param exampleStep 案例库步骤
     * @return 结果
     */
    @Override
    public int updateExampleStep(ExampleStep exampleStep)
    {
        return exampleStepMapper.updateExampleStep(exampleStep);
    }

    /**
     * 批量删除案例库步骤
     * 
     * @param ids 需要删除的案例库步骤主键
     * @return 结果
     */
    @Override
    public int deleteExampleStepByIds(Long[] ids)
    {
        return exampleStepMapper.deleteExampleStepByIds(ids);
    }

    /**
     * 删除案例库步骤信息
     * 
     * @param id 案例库步骤主键
     * @return 结果
     */
    @Override
    public int deleteExampleStepById(Long id)
    {
        return exampleStepMapper.deleteExampleStepById(id);
    }
}
