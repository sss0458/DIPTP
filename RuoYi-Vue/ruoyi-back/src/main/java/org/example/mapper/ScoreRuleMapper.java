package org.example.mapper;

import java.util.List;
import org.example.domain.ScoreRule;

/**
 * 评分规则Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public interface ScoreRuleMapper 
{
    /**
     * 查询评分规则
     * 
     * @param id 评分规则主键
     * @return 评分规则
     */
    public ScoreRule selectScoreRuleById(Long id);

    /**
     * 查询评分规则列表
     * 
     * @param scoreRule 评分规则
     * @return 评分规则集合
     */
    public List<ScoreRule> selectScoreRuleList(ScoreRule scoreRule);

    /**
     * 新增评分规则
     * 
     * @param scoreRule 评分规则
     * @return 结果
     */
    public int insertScoreRule(ScoreRule scoreRule);

    /**
     * 修改评分规则
     * 
     * @param scoreRule 评分规则
     * @return 结果
     */
    public int updateScoreRule(ScoreRule scoreRule);

    /**
     * 删除评分规则
     * 
     * @param id 评分规则主键
     * @return 结果
     */
    public int deleteScoreRuleById(Long id);

    /**
     * 批量删除评分规则
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteScoreRuleByIds(Long[] ids);
}
