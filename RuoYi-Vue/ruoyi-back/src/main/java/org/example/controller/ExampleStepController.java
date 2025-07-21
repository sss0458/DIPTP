package org.example.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import org.example.domain.ExampleStep;
import org.example.service.IExampleStepService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 案例库步骤Controller
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@RestController
@RequestMapping("/example_step")
public class ExampleStepController extends BaseController
{
    @Autowired
    private IExampleStepService exampleStepService;

    /**
     * 查询案例库步骤列表
     */
    @PreAuthorize("@ss.hasPermi('example:example_step:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExampleStep exampleStep)
    {
        startPage();
        List<ExampleStep> list = exampleStepService.selectExampleStepList(exampleStep);
        return getDataTable(list);
    }

    /**
     * 导出案例库步骤列表
     */
    @PreAuthorize("@ss.hasPermi('example:example_step:export')")
    @Log(title = "案例库步骤", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExampleStep exampleStep)
    {
        List<ExampleStep> list = exampleStepService.selectExampleStepList(exampleStep);
        ExcelUtil<ExampleStep> util = new ExcelUtil<ExampleStep>(ExampleStep.class);
        util.exportExcel(response, list, "案例库步骤数据");
    }

    /**
     * 获取案例库步骤详细信息
     */
    @PreAuthorize("@ss.hasPermi('example:example_step:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(exampleStepService.selectExampleStepById(id));
    }

    /**
     * 新增案例库步骤
     */
    @PreAuthorize("@ss.hasPermi('example:example_step:add')")
    @Log(title = "案例库步骤", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExampleStep exampleStep)
    {
        return toAjax(exampleStepService.insertExampleStep(exampleStep));
    }

    /**
     * 修改案例库步骤
     */
    @PreAuthorize("@ss.hasPermi('example:example_step:edit')")
    @Log(title = "案例库步骤", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExampleStep exampleStep)
    {
        return toAjax(exampleStepService.updateExampleStep(exampleStep));
    }

    /**
     * 删除案例库步骤
     */
    @PreAuthorize("@ss.hasPermi('example:example_step:remove')")
    @Log(title = "案例库步骤", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(exampleStepService.deleteExampleStepByIds(ids));
    }
}
