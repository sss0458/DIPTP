package org.example.service.impl;

import java.util.*;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import org.example.domain.vo.ExampleVo;
import org.example.domain.vo.StudentWorkVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.example.domain.ExampleStep;
import org.example.mapper.ExampleMapper;
import org.example.domain.Example;
import org.example.service.IExampleService;

import javax.validation.Validator;

/**
 * 案例库Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-30
 */
@Service
public class ExampleServiceImpl implements IExampleService 
{
    @Autowired
    private ExampleMapper exampleMapper;

    @Autowired
    protected Validator validator;

    private static final Logger log = LoggerFactory.getLogger(ExampleServiceImpl.class);

    /**
     * 查询案例库
     * 
     * @param id 案例库主键
     * @return 案例库
     */
    @Override
    public Example selectExampleById(Long id)
    {
        return exampleMapper.selectExampleById(id);
    }

    /**
     * 查询案例库列表
     * 
     * @param example 案例库
     * @return 案例库
     */
    @Override
    public List<Example> selectExampleList(Example example)
    {
        return exampleMapper.selectExampleList(example);
    }

    /**
     * 新增案例库
     * 
     * @param example 案例库
     * @return 结果
     */
    @Transactional
    @Override
    public int insertExample(Example example)
    {
        int rows = exampleMapper.insertExample(example);
        insertExampleStep(example);
        return rows;
    }

    /**
     * 修改案例库
     * 
     * @param example 案例库
     * @return 结果
     */
    @Transactional
    @Override
    public int updateExample(Example example)
    {
        exampleMapper.deleteExampleStepByExampleId(example.getId());
        insertExampleStep(example);
        return exampleMapper.updateExample(example);
    }

    /**
     * 批量删除案例库
     * 
     * @param ids 需要删除的案例库主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteExampleByIds(Long[] ids)
    {
        exampleMapper.deleteExampleStepByExampleIds(ids);
        return exampleMapper.deleteExampleByIds(ids);
    }

    /**
     * 删除案例库信息
     * 
     * @param id 案例库主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteExampleById(Long id)
    {
        exampleMapper.deleteExampleStepByExampleId(id);
        return exampleMapper.deleteExampleById(id);
    }

    /**
     * 新增案例库步骤信息
     * 
     * @param example 案例库对象
     */
    public void insertExampleStep(Example example)
    {
        List<ExampleStep> exampleStepList = example.getExampleStepList();
        Long id = example.getId();
        if (StringUtils.isNotNull(exampleStepList))
        {
            List<ExampleStep> list = new ArrayList<ExampleStep>();
            for (ExampleStep exampleStep : exampleStepList)
            {
                exampleStep.setExampleId(id);
                list.add(exampleStep);
            }
            if (list.size() > 0)
            {
                exampleMapper.batchExampleStep(list);
            }
        }
    }


    /**
     * 导入用户数据
     *
     * @param exampleList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importExample(List<Example> exampleList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(exampleList) || exampleList.size() == 0)
        {
            throw new ServiceException("导入案例库数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Example example : exampleList)
        {
            try
            {
                // 验证是否存在这个用户
                Example e = exampleMapper.selectByTaskTitleAndTestTitle(example.getTaskTitle(),example.getTestTitle());
                if (StringUtils.isNull(e))
                {
                    example.setCreateBy(operName);
                    example.setDelFlag("0");
                    exampleMapper.insertExample(example);
                    successNum++;
                    successMsg.append("<br/>").append(successNum)
                            .append("、任务[").append(example.getTaskTitle())
                            .append("]-实验[").append(example.getTestTitle())
                            .append("] 对应的案例导入成功");
                }
                else if (isUpdateSupport)
                {
                    BeanValidators.validateWithException(validator, example);
                    example.setId(e.getId());
                    example.setUpdateBy(operName);
                    exampleMapper.updateExample(example);
                    successNum++;
                    successMsg.append("<br/>").append(successNum)
                            .append("、任务[").append(example.getTaskTitle())
                            .append("]-实验[").append(example.getTestTitle())
                            .append("] 对应的案例更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>").append(failureNum)
                            .append("、任务[").append(example.getTaskTitle())
                            .append("]-实验[").append(example.getTestTitle())
                            .append("] 对应的案例已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                failureMsg.append("<br/>").append(failureNum)
                        .append("、导入失败：").append(e.getMessage());
                log.error("案例导入失败: {}-{}",
                        example.getTaskTitle(), example.getTestTitle(), e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    /**
     * 获取案例库统计信息
     */
    @Override
    public Map<String, Object> getExampleStatistics() {
        Map<String, Object> stats = new HashMap<>();

        // 1. 基础统计
        stats.put("totalCount", exampleMapper.countTotal());
        stats.put("activeCount", exampleMapper.countByStatus("0")); // 假设0是活跃状态

        // 2. 分类分布统计
        stats.put("typeDistribution", convertToMap(exampleMapper.countGroupBy("data_type")));
        stats.put("gradeDistribution", convertToMap(exampleMapper.countGroupBy("grade")));

        // 3. 热门案例
        stats.put("topExamples", exampleMapper.selectTopExamples(5));

      /*  // 4. 时间趋势（最近7天）
        Calendar calendar = Calendar.getInstance();
        Date endTime = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        Date beginTime = calendar.getTime();
        stats.put("trendData", exampleMapper.countByDateRange(beginTime, endTime));*/

        return stats;
    }

    /**
     * 将分组统计结果转换为Map
     */
    private Map<String, Long> convertToMap(List<Map<String, Object>> list) {
        Map<String, Long> result = new HashMap<>();
        for (Map<String, Object> item : list) {
            String key = (String) item.get("key");
            Long value = (Long) item.get("value");
            if (key != null && value != null) {
                result.put(key, value);
            }
        }
        return result;
    }

    /**
     * 根据主键获取视图
     */
    public ExampleVo selectExampleVoById(Long id){
        return exampleMapper.selectExampleVoById(id);
    }

    /**
     * 获取视图
     */
    public List<ExampleVo> selectExampleVoList() {
        return exampleMapper.selectExampleVoList();
    }
}
