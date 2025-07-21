package org.example.service.impl;

import java.util.List;

import org.example.domain.vo.ExampleVo;
import org.example.domain.vo.ScoreRuleDimensionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.mapper.ScoreRuleDimensionMapper;
import org.example.domain.ScoreRuleDimension;
import org.example.service.IScoreRuleDimensionService;

/**
 * 评分规则维度Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public class ScoreRuleDimensionServiceImpl implements IScoreRuleDimensionService 
{
    @Autowired
    private ScoreRuleDimensionMapper scoreRuleDimensionMapper;

    /**
     * 查询评分规则维度
     * 
     * @param id 评分规则维度主键
     * @return 评分规则维度
     */
    @Override
    public ScoreRuleDimension selectScoreRuleDimensionById(Long id)
    {
        return scoreRuleDimensionMapper.selectScoreRuleDimensionById(id);
    }

    /**
     * 查询评分规则维度列表
     * 
     * @param scoreRuleDimension 评分规则维度
     * @return 评分规则维度
     */
    @Override
    public List<ScoreRuleDimension> selectScoreRuleDimensionList(ScoreRuleDimension scoreRuleDimension)
    {
        return scoreRuleDimensionMapper.selectScoreRuleDimensionList(scoreRuleDimension);
    }

    /**
     * 新增评分规则维度
     * 
     * @param scoreRuleDimension 评分规则维度
     * @return 结果
     */
    @Override
    public int insertScoreRuleDimension(ScoreRuleDimension scoreRuleDimension)
    {
        return scoreRuleDimensionMapper.insertScoreRuleDimension(scoreRuleDimension);
    }

    /**
     * 修改评分规则维度
     * 
     * @param scoreRuleDimension 评分规则维度
     * @return 结果
     */
    @Override
    public int updateScoreRuleDimension(ScoreRuleDimension scoreRuleDimension)
    {
        return scoreRuleDimensionMapper.updateScoreRuleDimension(scoreRuleDimension);
    }

    /**
     * 批量删除评分规则维度
     * 
     * @param ids 需要删除的评分规则维度主键
     * @return 结果
     */
    @Override
    public int deleteScoreRuleDimensionByIds(Long[] ids)
    {
        return scoreRuleDimensionMapper.deleteScoreRuleDimensionByIds(ids);
    }

    /**
     * 删除评分规则维度信息
     * 
     * @param id 评分规则维度主键
     * @return 结果
     */
    @Override
    public int deleteScoreRuleDimensionById(Long id)
    {
        return scoreRuleDimensionMapper.deleteScoreRuleDimensionById(id);
    }

    /**
     * 根据主键获取视图
     */
    public ScoreRuleDimensionVo selectScoreRuleDimensionVoById(Long id){
        return scoreRuleDimensionMapper.selectScoreRuleDimensionVoById(id);
    }

    /**
     * 获取视图
     */
    public List<ScoreRuleDimensionVo> selectScoreRuleDimensionVoList() {
        return scoreRuleDimensionMapper.selectScoreRuleDimensionVoList();
    }
}
