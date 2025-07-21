package org.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.example.domain.vo.ExampleVo;
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
import org.example.domain.ScoreRuleDimension;
import org.example.service.IScoreRuleDimensionService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 评分规则维度Controller
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@RestController
@RequestMapping("/dimension")
public class ScoreRuleDimensionController extends BaseController
{
    @Autowired
    private IScoreRuleDimensionService scoreRuleDimensionService;

    /**
     * 查询评分规则维度列表
     */
    @PreAuthorize("@ss.hasPermi('example:dimension:list')")
    @GetMapping("/list")
    public AjaxResult list(ScoreRuleDimension scoreRuleDimension)
    {
        List<ScoreRuleDimension> list = scoreRuleDimensionService.selectScoreRuleDimensionList(scoreRuleDimension);
        return success(list);
    }


    /**
     * 根据评分维度ID获取所有案例数据
     * @param dimensionId 维度ID
     * @return 包含对应数据的视图对象
     */
    @PreAuthorize("@ss.hasPermi('example:dimension:query')")
    @GetMapping("/fulldimension/{dimensionId}")
    public AjaxResult getFullScoreRuleDimensionDataByDimensionId(@PathVariable Long dimensionId) {

        ScoreRuleDimensionVo scoreRuleDimensionVo =  scoreRuleDimensionService.selectScoreRuleDimensionVoById(dimensionId);
        if (scoreRuleDimensionVo == null) {
            return error("维度不存在");
        }
        return success(scoreRuleDimensionVo);
    }

    /**
     * 获取所有案例数据
     *
     * @return 包含所有案例数据的视图对象列表
     */
    @PreAuthorize("@ss.hasPermi('example:dimension:list')")
    @GetMapping("/full/all")
    public AjaxResult getAllFullScoreRuleDimensionData() {
        List<ScoreRuleDimensionVo>  scoreRuleDimensionVos = scoreRuleDimensionService.selectScoreRuleDimensionVoList();
        return success(scoreRuleDimensionVos);
    }


    /**
     * 导出评分规则维度列表
     */
    @PreAuthorize("@ss.hasPermi('example:dimension:export')")
    @Log(title = "评分规则维度", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ScoreRuleDimension scoreRuleDimension)
    {
        List<ScoreRuleDimension> list = scoreRuleDimensionService.selectScoreRuleDimensionList(scoreRuleDimension);
        ExcelUtil<ScoreRuleDimension> util = new ExcelUtil<ScoreRuleDimension>(ScoreRuleDimension.class);
        util.exportExcel(response, list, "评分规则维度数据");
    }

    /**
     * 获取评分规则维度详细信息
     */
    @PreAuthorize("@ss.hasPermi('example:dimension:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(scoreRuleDimensionService.selectScoreRuleDimensionById(id));
    }

    /**
     * 新增评分规则维度
     */
    @PreAuthorize("@ss.hasPermi('example:dimension:add')")
    @Log(title = "评分规则维度", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ScoreRuleDimension scoreRuleDimension)
    {
        return toAjax(scoreRuleDimensionService.insertScoreRuleDimension(scoreRuleDimension));
    }

    /**
     * 修改评分规则维度
     */
    @PreAuthorize("@ss.hasPermi('example:dimension:edit')")
    @Log(title = "评分规则维度", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ScoreRuleDimension scoreRuleDimension)
    {
        return toAjax(scoreRuleDimensionService.updateScoreRuleDimension(scoreRuleDimension));
    }

    /**
     * 删除评分规则维度
     */
    @PreAuthorize("@ss.hasPermi('example:dimension:remove')")
    @Log(title = "评分规则维度", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(scoreRuleDimensionService.deleteScoreRuleDimensionByIds(ids));
    }
}
