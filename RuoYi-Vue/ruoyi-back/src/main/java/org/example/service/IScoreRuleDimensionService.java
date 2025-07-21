package org.example.service;

import java.util.List;
import org.example.domain.ScoreRuleDimension;
import org.example.domain.vo.ScoreRuleDimensionVo;

/**
 * 评分规则维度Service接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public interface IScoreRuleDimensionService 
{
    /**
     * 查询评分规则维度
     * 
     * @param id 评分规则维度主键
     * @return 评分规则维度
     */
    public ScoreRuleDimension selectScoreRuleDimensionById(Long id);

    /**
     * 查询评分规则维度列表
     * 
     * @param scoreRuleDimension 评分规则维度
     * @return 评分规则维度集合
     */
    public List<ScoreRuleDimension> selectScoreRuleDimensionList(ScoreRuleDimension scoreRuleDimension);

    /**
     * 新增评分规则维度
     * 
     * @param scoreRuleDimension 评分规则维度
     * @return 结果
     */
    public int insertScoreRuleDimension(ScoreRuleDimension scoreRuleDimension);

    /**
     * 修改评分规则维度
     * 
     * @param scoreRuleDimension 评分规则维度
     * @return 结果
     */
    public int updateScoreRuleDimension(ScoreRuleDimension scoreRuleDimension);

    /**
     * 批量删除评分规则维度
     * 
     * @param ids 需要删除的评分规则维度主键集合
     * @return 结果
     */
    public int deleteScoreRuleDimensionByIds(Long[] ids);

    /**
     * 删除评分规则维度信息
     * 
     * @param id 评分规则维度主键
     * @return 结果
     */
    public int deleteScoreRuleDimensionById(Long id);

    /**
     * 根据主键获取视图
     *
     * @param id 案例库主键
     * @return 结果
     */
    public ScoreRuleDimensionVo selectScoreRuleDimensionVoById(Long id);

    /**
     * 获取视图
     *
     * @return 结果
     */
    public List<ScoreRuleDimensionVo> selectScoreRuleDimensionVoList();
}
