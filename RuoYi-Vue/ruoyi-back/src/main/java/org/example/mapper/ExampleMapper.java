package org.example.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.entity.SysUser;
import org.example.domain.Example;
import org.example.domain.ExampleStep;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.example.domain.vo.ExampleVo;
import org.example.domain.vo.StudentWorkVo;

/**
 * 案例库Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-30
 */
public interface ExampleMapper 
{
    /**
     * 查询案例库
     * 
     * @param id 案例库主键
     * @return 案例库
     */
    public Example selectExampleById(Long id);

    /**
     * 查询案例库列表
     * 
     * @param example 案例库
     * @return 案例库集合
     */
    public List<Example> selectExampleList(Example example);

    /**
     * 新增案例库
     * 
     * @param example 案例库
     * @return 结果
     */
    public int insertExample(Example example);

    /**
     * 修改案例库
     * 
     * @param example 案例库
     * @return 结果
     */
    public int updateExample(Example example);

    /**
     * 删除案例库
     * 
     * @param id 案例库主键
     * @return 结果
     */
    public int deleteExampleById(Long id);

    /**
     * 批量删除案例库
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExampleByIds(Long[] ids);

    /**
     * 批量删除案例库步骤
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExampleStepByExampleIds(Long[] ids);
    
    /**
     * 批量新增案例库步骤
     * 
     * @param exampleStepList 案例库步骤列表
     * @return 结果
     */
    public int batchExampleStep(List<ExampleStep> exampleStepList);
    

    /**
     * 通过案例库主键删除案例库步骤信息
     * 
     * @param id 案例库ID
     * @return 结果
     */
    public int deleteExampleStepByExampleId(Long id);


    /**
     * 通过案例库主键获取视图
     *
     * @param id 案例库ID
     * @return 结果
     */
    public ExampleVo selectExampleVoById(Long id);


    /**
     * 获取视图
     *
     * @return 结果
     */
    public List<ExampleVo> selectExampleVoList();




    /**
     * 通过实验名和任务名查询用户
     *
     * @param task_title 任务名
     * @param test_title 实验名
     * @return 用户对象信息
     */
    public Example selectByTaskTitleAndTestTitle(String task_title,String test_title);

    // ============== 统计功能新增方法 ==============

    /**
     * 统计案例总数
     */
    Long countTotal();

    /**
     * 按状态统计案例数量
     * @param status 状态值
     */
    Long countByStatus(String status);

    /**
     * 按字段分组统计
     * @param column 分组字段名
     * @return 分组统计结果，key: 字段值, value: 数量
     */
    List<Map<String, Object>> countGroupBy(@Param("column") String column);

    /**
     * 查询热门案例（按使用次数排序）
     * @param limit 返回数量
     * @return 包含案例信息和引用次数的列表
     */
    //@MapKey("id")
    List<Map<String, Object>> selectTopExamples(@Param("limit") int limit);

 /*   *//**
     * 按日期范围统计新增案例
     * @param beginTime 开始时间
     * @param endTime 结束时间
     *//*
    //@MapKey("date")
    List<Map<String, Object>> countByDateRange(
            @Param("beginTime") Date beginTime,
            @Param("endTime") Date endTime);*/


}
