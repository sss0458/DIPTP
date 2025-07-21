package org.example.service.impl;

import java.util.List;

import org.example.domain.vo.ScoreRuleDimensionPointVo;
import org.example.domain.vo.ScoreRuleDimensionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.mapper.ScoreRuleDimensionPointMapper;
import org.example.domain.ScoreRuleDimensionPoint;
import org.example.service.IScoreRuleDimensionPointService;

/**
 * 评分规则维度要点Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public class ScoreRuleDimensionPointServiceImpl implements IScoreRuleDimensionPointService 
{
    @Autowired
    private ScoreRuleDimensionPointMapper scoreRuleDimensionPointMapper;

    /**
     * 查询评分规则维度要点
     * 
     * @param id 评分规则维度要点主键
     * @return 评分规则维度要点
     */
    @Override
    public ScoreRuleDimensionPoint selectScoreRuleDimensionPointById(Long id)
    {
        return scoreRuleDimensionPointMapper.selectScoreRuleDimensionPointById(id);
    }

    /**
     * 查询评分规则维度要点列表
     * 
     * @param scoreRuleDimensionPoint 评分规则维度要点
     * @return 评分规则维度要点
     */
    @Override
    public List<ScoreRuleDimensionPoint> selectScoreRuleDimensionPointList(ScoreRuleDimensionPoint scoreRuleDimensionPoint)
    {
        return scoreRuleDimensionPointMapper.selectScoreRuleDimensionPointList(scoreRuleDimensionPoint);
    }

    /**
     * 新增评分规则维度要点
     * 
     * @param scoreRuleDimensionPoint 评分规则维度要点
     * @return 结果
     */
    @Override
    public int insertScoreRuleDimensionPoint(ScoreRuleDimensionPoint scoreRuleDimensionPoint)
    {
        return scoreRuleDimensionPointMapper.insertScoreRuleDimensionPoint(scoreRuleDimensionPoint);
    }

    /**
     * 修改评分规则维度要点
     * 
     * @param scoreRuleDimensionPoint 评分规则维度要点
     * @return 结果
     */
    @Override
    public int updateScoreRuleDimensionPoint(ScoreRuleDimensionPoint scoreRuleDimensionPoint)
    {
        return scoreRuleDimensionPointMapper.updateScoreRuleDimensionPoint(scoreRuleDimensionPoint);
    }

    /**
     * 批量删除评分规则维度要点
     * 
     * @param ids 需要删除的评分规则维度要点主键
     * @return 结果
     */
    @Override
    public int deleteScoreRuleDimensionPointByIds(Long[] ids)
    {
        return scoreRuleDimensionPointMapper.deleteScoreRuleDimensionPointByIds(ids);
    }

    /**
     * 删除评分规则维度要点信息
     * 
     * @param id 评分规则维度要点主键
     * @return 结果
     */
    @Override
    public int deleteScoreRuleDimensionPointById(Long id)
    {
        return scoreRuleDimensionPointMapper.deleteScoreRuleDimensionPointById(id);
    }

    /**
     * 根据主键获取视图
     */
    public ScoreRuleDimensionPointVo selectScoreRuleDimensionPointVoById(Long id){
        return scoreRuleDimensionPointMapper.selectScoreRuleDimensionPointVoById(id);
    }

    /**
     * 获取视图
     */
    public List<ScoreRuleDimensionPointVo> selectScoreRuleDimensionPointVoList() {
        return scoreRuleDimensionPointMapper.selectScoreRuleDimensionPointVoList();
    }
}
