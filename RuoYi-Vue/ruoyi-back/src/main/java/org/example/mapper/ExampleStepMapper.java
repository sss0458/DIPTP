package org.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.ExampleStep;

/**
 * 案例库步骤Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Mapper
public interface ExampleStepMapper 
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
     * 删除案例库步骤
     * 
     * @param id 案例库步骤主键
     * @return 结果
     */
    public int deleteExampleStepById(Long id);

    /**
     * 批量删除案例库步骤
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExampleStepByIds(Long[] ids);
}
