package org.example.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.example.domain.ScoreRule;
import org.example.service.IScoreRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 评分规则Controller
 * 
 * @author ruoyi
 * @date 2025-07-07
 */

@RestController
@RequestMapping("/rule")
public class ScoreRuleController extends BaseController
{
    @Autowired
    private IScoreRuleService scoreRuleService;

    /**
     * 查询评分规则列表
     */

    @PreAuthorize("@ss.hasPermi('example:rule:list')")
    @GetMapping("/list")
    public TableDataInfo list(ScoreRule scoreRule)
    {
        startPage();
        List<ScoreRule> list = scoreRuleService.selectScoreRuleList(scoreRule);
        return getDataTable(list);
    }

    /**
     * 导出评分规则列表
     */
    @PreAuthorize("@ss.hasPermi('example:rule:export')")
    @Log(title = "评分规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ScoreRule scoreRule)
    {
        List<ScoreRule> list = scoreRuleService.selectScoreRuleList(scoreRule);
        ExcelUtil<ScoreRule> util = new ExcelUtil<ScoreRule>(ScoreRule.class);
        util.exportExcel(response, list, "评分规则数据");
    }

    /**
     * 获取评分规则详细信息
     */
    @PreAuthorize("@ss.hasPermi('example:rule:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(scoreRuleService.selectScoreRuleById(id));
    }

    /**
     * 新增评分规则
     */
    @PreAuthorize("@ss.hasPermi('example:rule:add')")
    @Log(title = "评分规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ScoreRule scoreRule)
    {
        return toAjax(scoreRuleService.insertScoreRule(scoreRule));
    }

    /**
     * 修改评分规则
     */
    @PreAuthorize("@ss.hasPermi('example:rule:edit')")
    @Log(title = "评分规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ScoreRule scoreRule)
    {
        return toAjax(scoreRuleService.updateScoreRule(scoreRule));
    }

    /**
     * 删除评分规则
     */
    @PreAuthorize("@ss.hasPermi('example:rule:remove')")
    @Log(title = "评分规则", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(scoreRuleService.deleteScoreRuleByIds(ids));
    }
}
