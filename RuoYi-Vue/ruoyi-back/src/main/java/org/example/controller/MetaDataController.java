package org.example.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
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
import org.example.domain.MetaData;
import org.example.service.IMetaDataService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 元数据管理Controller
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@RestController
@RequestMapping("/meta_data")
public class MetaDataController extends BaseController
{
    @Autowired
    private IMetaDataService metaDataService;

    /**
     * 查询元数据管理列表
     */
    @PreAuthorize("@ss.hasPermi('example:meta_data:list')")
    @GetMapping("/list")
    public TableDataInfo list(MetaData metaData)
    {
        startPage();
        List<MetaData> list = metaDataService.selectMetaDataList(metaData);
        return getDataTable(list);
    }

    /**
     * 导出元数据管理列表
     */
    @PreAuthorize("@ss.hasPermi('example:meta_data:export')")
    @Log(title = "元数据管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MetaData metaData)
    {
        List<MetaData> list = metaDataService.selectMetaDataList(metaData);
        ExcelUtil<MetaData> util = new ExcelUtil<MetaData>(MetaData.class);
        util.exportExcel(response, list, "元数据管理数据");
    }

    /**
     * 获取元数据管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('example:meta_data:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(metaDataService.selectMetaDataById(id));
    }

    /**
     * 新增元数据管理
     */
    @PreAuthorize("@ss.hasPermi('example:meta_data:add')")
    @Log(title = "元数据管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MetaData metaData)
    {
        return toAjax(metaDataService.insertMetaData(metaData));
    }

    /**
     * 修改元数据管理
     */
    @PreAuthorize("@ss.hasPermi('example:meta_data:edit')")
    @Log(title = "元数据管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MetaData metaData)
    {
        return toAjax(metaDataService.updateMetaData(metaData));
    }

    /**
     * 删除元数据管理
     */
    @PreAuthorize("@ss.hasPermi('example:meta_data:remove')")
    @Log(title = "元数据管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(metaDataService.deleteMetaDataByIds(ids));
    }

    /**
     * 批量删除元数据管理（逻辑删除）
     * DELETE /system/metaData/batchRemove/{ids} (例如: /system/metaData/batchRemove/1,2,3)
     */
    @PreAuthorize("@ss.hasPermi('example:meta_data:remove')") // 权限控制
    @Log(title = "元数据管理", businessType = BusinessType.DELETE) // 操作日志
    @DeleteMapping("/batchRemove/{ids}")
    public AjaxResult removeBatch(@PathVariable Long[] ids) // 从路径中获取逗号分隔的 ID 数组
    {
        String updateBy = SecurityUtils.getUsername(); // **获取当前登录用户名作为操作人**
        // 将 Long[] 转换为 List<Long>，以匹配 Service 接口和 Mapper 接口的参数类型
        List<Long> idList = Arrays.asList(ids);
        return toAjax(metaDataService.updateMetaDataDelFlagByIds(idList)); // 调用 Service 进行批量逻辑删除
    }

    /**
     * 根据名称查询元数据定义 (供其他模块，如媒体资源管理，获取元数据定义使用)
     * GET /system/metaData/byName/{name}
     */
    @PreAuthorize("@ss.hasPermi('example:meta_data:query')") // 通常查询权限即可
    @GetMapping("/byName/{name}")
    public AjaxResult getMetaDataByName(@PathVariable String name) {
        MetaData metaData = metaDataService.selectMetaDataByName(name);
        return success(metaData);
    }

    /**
     * 根据名称查询元数据ID (如果需要单独获取ID)
     * GET /system/metaData/idByName/{name}
     */
    @PreAuthorize("@ss.hasPermi('example:meta_data:query')")
    @GetMapping("/idByName/{name}")
    public AjaxResult getMetaDataIdByName(@PathVariable String name) {
        Long id = metaDataService.selectMetaDataIdByName(name);
        return success(id);
    }
}
