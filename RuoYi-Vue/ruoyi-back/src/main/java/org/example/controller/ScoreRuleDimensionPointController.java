package org.example.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.example.domain.vo.ScoreRuleDimensionPointVo;
import org.example.domain.vo.ScoreRuleDimensionVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import org.example.domain.ScoreRuleDimensionPoint;
import org.example.service.IScoreRuleDimensionPointService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 评分规则维度要点Controller
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@RestController
@RequestMapping("/point")
public class ScoreRuleDimensionPointController extends BaseController
{
    @Autowired
    private IScoreRuleDimensionPointService scoreRuleDimensionPointService;

    /**
     * 查询评分规则维度要点列表
     */
    @PreAuthorize("@ss.hasPermi('example:point:list')")
    @GetMapping("/list")
    public AjaxResult list(ScoreRuleDimensionPoint scoreRuleDimensionPoint)
    {
        List<ScoreRuleDimensionPoint> list = scoreRuleDimensionPointService.selectScoreRuleDimensionPointList(scoreRuleDimensionPoint);
        return success(list);
    }

    /**
     * 根据评分点ID获取所有评分点数据
     * @param pointId ID
     * @return 包含对应数据的视图对象
     */
    @PreAuthorize("@ss.hasPermi('example:point:query')")
    @GetMapping("/fullpoint/{pointId}")
    public AjaxResult getFullScoreRuleDimensionPointDataByPointId(@PathVariable Long pointId) {

        ScoreRuleDimensionPointVo scoreRuleDimensionPointVo =  scoreRuleDimensionPointService.selectScoreRuleDimensionPointVoById(pointId);
        if (scoreRuleDimensionPointVo == null) {
            return error("评分点不存在");
        }
        return success(scoreRuleDimensionPointVo);
    }

    /**
     * 获取所有案例数据
     *
     * @return 包含所有评分点数据的视图对象列表
     */
    @PreAuthorize("@ss.hasPermi('example:point:list')")
    @GetMapping("/full/all")
    public AjaxResult getAllFullScoreRuleDimensionPointData() {
        List<ScoreRuleDimensionPointVo>  scoreRuleDimensionPointVos = scoreRuleDimensionPointService.selectScoreRuleDimensionPointVoList();
        return success(scoreRuleDimensionPointVos);
    }


    /**
     * 导出评分规则维度要点列表
     */
    @PreAuthorize("@ss.hasPermi('example:point:export')")
    @Log(title = "评分规则维度要点", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ScoreRuleDimensionPoint scoreRuleDimensionPoint)
    {
        List<ScoreRuleDimensionPoint> list = scoreRuleDimensionPointService.selectScoreRuleDimensionPointList(scoreRuleDimensionPoint);
        ExcelUtil<ScoreRuleDimensionPoint> util = new ExcelUtil<ScoreRuleDimensionPoint>(ScoreRuleDimensionPoint.class);
        util.exportExcel(response, list, "评分规则维度要点数据");
    }

    /**
     * 获取评分规则维度要点详细信息
     */
    @PreAuthorize("@ss.hasPermi('example:point:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(scoreRuleDimensionPointService.selectScoreRuleDimensionPointById(id));
    }

    /**
     * 新增评分规则维度要点
     */
    @PreAuthorize("@ss.hasPermi('example:point:add')")
    @Log(title = "评分规则维度要点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ScoreRuleDimensionPoint scoreRuleDimensionPoint)
    {
        return toAjax(scoreRuleDimensionPointService.insertScoreRuleDimensionPoint(scoreRuleDimensionPoint));
    }

    /**
     * 修改评分规则维度要点
     */
    @PreAuthorize("@ss.hasPermi('example:point:edit')")
    @Log(title = "评分规则维度要点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ScoreRuleDimensionPoint scoreRuleDimensionPoint)
    {
        return toAjax(scoreRuleDimensionPointService.updateScoreRuleDimensionPoint(scoreRuleDimensionPoint));
    }

    /**
     * 删除评分规则维度要点
     */
    @PreAuthorize("@ss.hasPermi('example:point:remove')")
    @Log(title = "评分规则维度要点", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(scoreRuleDimensionPointService.deleteScoreRuleDimensionPointByIds(ids));
    }
}
