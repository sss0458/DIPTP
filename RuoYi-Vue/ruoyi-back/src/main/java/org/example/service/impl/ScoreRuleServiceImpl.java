package org.example.service.impl;

import java.util.List;

import org.example.domain.ScoreRuleDimension;
import org.example.domain.ScoreRuleDimensionPoint;
import org.example.mapper.ScoreRuleDimensionMapper;
import org.example.mapper.ScoreRuleDimensionPointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.mapper.ScoreRuleMapper;
import org.example.domain.ScoreRule;
import org.example.service.IScoreRuleService;

/**
 * 评分规则Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public class ScoreRuleServiceImpl implements IScoreRuleService 
{
    @Autowired
    private ScoreRuleMapper scoreRuleMapper;

    @Autowired
    private ScoreRuleDimensionMapper scoreRuleDimensionMapper;

    @Autowired
    private ScoreRuleDimensionPointMapper scoreRuleDimensionPointMapper;

    //修改
    public int saveScoreRule(ScoreRule rule) {
        // 2. 保存维度
        for (ScoreRuleDimension dimension : scoreRuleDimensionMapper.selectScoreRuleDimensionByRuleId(rule.getId())) {
            dimension.setRuleId(rule.getId());
            scoreRuleDimensionMapper.insertScoreRuleDimension(dimension);

            // 3. 保存评分要点
            for (ScoreRuleDimensionPoint point : scoreRuleDimensionPointMapper.selectScoreRuleDimensionPointByDimensionId(dimension.getId())) {
                point.setRuleId(rule.getId());
                point.setDimensionId(dimension.getId());
                scoreRuleDimensionPointMapper.insertScoreRuleDimensionPoint(point);
            }
        }
        return scoreRuleMapper.insertScoreRule(rule);
    }
    /**
     * 查询评分规则
     * 
     * @param id 评分规则主键
     * @return 评分规则
     */
    @Override
    public ScoreRule selectScoreRuleById(Long id)
    {
        return scoreRuleMapper.selectScoreRuleById(id);
    }

    /**
     * 查询评分规则列表
     * 
     * @param scoreRule 评分规则
     * @return 评分规则
     */
    @Override
    public List<ScoreRule> selectScoreRuleList(ScoreRule scoreRule)
    {
        return scoreRuleMapper.selectScoreRuleList(scoreRule);
    }

    /**
     * 新增评分规则
     * 
     * @param scoreRule 评分规则
     * @return 结果
     */
    @Override
    public int insertScoreRule(ScoreRule scoreRule)
    {
        return saveScoreRule(scoreRule);
    }

    /**
     * 修改评分规则
     * 
     * @param scoreRule 评分规则
     * @return 结果
     */
    @Override
    public int updateScoreRule(ScoreRule scoreRule)
    {
        return scoreRuleMapper.updateScoreRule(scoreRule);
    }

    /**
     * 批量删除评分规则
     * 
     * @param ids 需要删除的评分规则主键
     * @return 结果
     */
    @Override
    public int deleteScoreRuleByIds(Long[] ids)
    {
        return scoreRuleMapper.deleteScoreRuleByIds(ids);
    }

    /**
     * 删除评分规则信息
     * 
     * @param id 评分规则主键
     * @return 结果
     */
    @Override
    public int deleteScoreRuleById(Long id)
    {
        return scoreRuleMapper.deleteScoreRuleById(id);
    }
}
