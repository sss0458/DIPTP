package org.example.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.entity.SysUser;
import org.example.domain.Example;
import org.example.domain.vo.ExampleVo;
import org.example.domain.vo.StudentWorkVo;

/**
 * 案例库Service接口
 * 
 * @author ruoyi
 * @date 2025-06-30
 */
public interface IExampleService 
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
     * 批量删除案例库
     * 
     * @param ids 需要删除的案例库主键集合
     * @return 结果
     */
    public int deleteExampleByIds(Long[] ids);

    /**
     * 删除案例库信息
     * 
     * @param id 案例库主键
     * @return 结果
     */
    public int deleteExampleById(Long id);

    /**
     * 导入案例数据
     *
     * @param exampleList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importExample(List<Example> exampleList, Boolean isUpdateSupport, String operName);

    /**
     * 获取统计数据
     */
    public Map<String, Object> getExampleStatistics();

    /**
     * 根据案例库主键获取视图
     *
     * @param id 案例库主键
     * @return 结果
     */
    public ExampleVo selectExampleVoById(Long id);

    /**
     * 获取视图
     *
     * @return 结果
     */
    public List<ExampleVo> selectExampleVoList();



}
