package org.example.service;

import java.util.List;
import org.example.domain.ExampleStep;
import org.springframework.stereotype.Service;

/**
 * 案例库步骤Service接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public interface IExampleStepService 
{
    /**
     * 查询案例库步骤
     * 
     * @param id 案例库步骤主键
     * @return 案例库步骤
     */
    public ExampleStep selectExampleStepById(Long id);

    /**
     * 查询案例库步骤列表
     * 
     * @param exampleStep 案例库步骤
     * @return 案例库步骤集合
     */
    public List<ExampleStep> selectExampleStepList(ExampleStep exampleStep);

    /**
     * 新增案例库步骤
     * 
     * @param exampleStep 案例库步骤
     * @return 结果
     */
    public int insertExampleStep(ExampleStep exampleStep);

    /**
     * 修改案例库步骤
     * 
     * @param exampleStep 案例库步骤
     * @return 结果
     */
    public int updateExampleStep(ExampleStep exampleStep);

    /**
     * 批量删除案例库步骤
     * 
     * @param ids 需要删除的案例库步骤主键集合
     * @return 结果
     */
    public int deleteExampleStepByIds(Long[] ids);

    /**
     * 删除案例库步骤信息
     * 
     * @param id 案例库步骤主键
     * @return 结果
     */
    public int deleteExampleStepById(Long id);
}
