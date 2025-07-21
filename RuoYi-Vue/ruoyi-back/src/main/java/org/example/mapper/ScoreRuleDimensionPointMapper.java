package org.example.mapper;

import java.util.List;
import org.example.domain.ScoreRuleDimensionPoint;
import org.example.domain.vo.ScoreRuleDimensionPointVo;
import org.example.domain.vo.ScoreRuleDimensionVo;

/**
 * 评分规则维度要点Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
public interface ScoreRuleDimensionPointMapper 
{
    /**
     * 查询评分规则维度要点
     * 
     * @param id 评分规则维度要点主键
     * @return 评分规则维度要点
     */
    public ScoreRuleDimensionPoint selectScoreRuleDimensionPointById(Long id);

    /**
     * 查询评分规则维度要点列表
     * 
     * @param scoreRuleDimensionPoint 评分规则维度要点
     * @return 评分规则维度要点集合
     */
    public List<ScoreRuleDimensionPoint> selectScoreRuleDimensionPointList(ScoreRuleDimensionPoint scoreRuleDimensionPoint);

    /**
     * 新增评分规则维度要点
     * 
     * @param scoreRuleDimensionPoint 评分规则维度要点
     * @return 结果
     */
    public int insertScoreRuleDimensionPoint(ScoreRuleDimensionPoint scoreRuleDimensionPoint);

    /**
     * 修改评分规则维度要点
     * 
     * @param scoreRuleDimensionPoint 评分规则维度要点
     * @return 结果
     */
    public int updateScoreRuleDimensionPoint(ScoreRuleDimensionPoint scoreRuleDimensionPoint);

    /**
     * 删除评分规则维度要点
     * 
     * @param id 评分规则维度要点主键
     * @return 结果
     */
    public int deleteScoreRuleDimensionPointById(Long id);

    /**
     * 批量删除评分规则维度要点
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteScoreRuleDimensionPointByIds(Long[] ids);

    /**
     * 通过主键获取视图
     *
     * @param id 案例库ID
     * @return 结果
     */
    public ScoreRuleDimensionPointVo selectScoreRuleDimensionPointVoById(Long id);


    /**
     * 获取视图
     *
     * @return 结果
     */
    public List<ScoreRuleDimensionPointVo> selectScoreRuleDimensionPointVoList();

    public List<ScoreRuleDimensionPoint> selectScoreRuleDimensionPointByDimensionId(Long dimensionId);

}
