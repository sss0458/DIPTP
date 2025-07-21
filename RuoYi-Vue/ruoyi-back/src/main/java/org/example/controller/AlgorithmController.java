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
import org.example.domain.Algorithm;
import org.example.service.IAlgorithmService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 算法Controller
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@RestController
@RequestMapping("/algorithm")
public class AlgorithmController extends BaseController
{
    @Autowired
    private IAlgorithmService algorithmService;

    /**
     * 查询算法列表
     */
    @PreAuthorize("@ss.hasPermi('example:algorithm:list')")
    @GetMapping("/list")
    public TableDataInfo list(Algorithm algorithm)
    {
        startPage();
        List<Algorithm> list = algorithmService.selectAlgorithmList(algorithm);
        return getDataTable(list);
    }

    /**
     * 导出算法列表
     */
    @PreAuthorize("@ss.hasPermi('example:algorithm:export')")
    @Log(title = "算法", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Algorithm algorithm)
    {
        List<Algorithm> list = algorithmService.selectAlgorithmList(algorithm);
        ExcelUtil<Algorithm> util = new ExcelUtil<Algorithm>(Algorithm.class);
        util.exportExcel(response, list, "算法数据");
    }

    /**
     * 获取算法详细信息
     */
    @PreAuthorize("@ss.hasPermi('example:algorithm:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(algorithmService.selectAlgorithmById(id));
    }

    /**
     * 新增算法
     */
    @PreAuthorize("@ss.hasPermi('example:algorithm:add')")
    @Log(title = "算法", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Algorithm algorithm)
    {
        return toAjax(algorithmService.insertAlgorithm(algorithm));
    }

    /**
     * 修改算法
     */
    @PreAuthorize("@ss.hasPermi('example:algorithm:edit')")
    @Log(title = "算法", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Algorithm algorithm)
    {
        return toAjax(algorithmService.updateAlgorithm(algorithm));
    }

    /**
     * 删除算法
     */
    @PreAuthorize("@ss.hasPermi('example:algorithm:remove')")
    @Log(title = "算法", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(algorithmService.deleteAlgorithmByIds(ids));
    }
}
