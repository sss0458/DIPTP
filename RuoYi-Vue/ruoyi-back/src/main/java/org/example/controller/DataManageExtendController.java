package org.example.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.example.domain.vo.DataManageExtendVo;
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
import org.example.domain.DataManageExtend;
import org.example.service.IDataManageExtendService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 数据管理拓展Controller
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@RestController
@RequestMapping("/data_manage_extend")
public class DataManageExtendController extends BaseController
{
    @Autowired
    private IDataManageExtendService dataManageExtendService;

    /**
     * 查询数据管理拓展列表
     */
    @PreAuthorize("@ss.hasPermi('example:data_manage_extend:list')")
    @GetMapping("/list")
    public TableDataInfo list(DataManageExtend dataManageExtend)
    {
        startPage();
        List<DataManageExtendVo> list = dataManageExtendService.selectDataManageExtendVoList(dataManageExtend);
        return getDataTable(list);
    }

    /**
     * 获取数据管理拓展详细信息
     */
    @PreAuthorize("@ss.hasPermi('example:data_manage_extend:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(dataManageExtendService.selectDataManageExtendById(id));
    }

    /**
     * 新增数据管理拓展
     */
    @PreAuthorize("@ss.hasPermi('example:data_manage_extend:add')")
    @Log(title = "数据管理拓展", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DataManageExtend dataManageExtend)
    {
        return toAjax(dataManageExtendService.insertDataManageExtend(dataManageExtend));
    }

    /**
     * 修改数据管理拓展
     */
    @PreAuthorize("@ss.hasPermi('example:data_manage_extend:edit')")
    @Log(title = "数据管理拓展", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DataManageExtend dataManageExtend)
    {
        return toAjax(dataManageExtendService.updateDataManageExtend(dataManageExtend));
    }

    /**
     * 删除数据管理拓展
     */
    @PreAuthorize("@ss.hasPermi('example:data_manage_extend:remove')")
    @Log(title = "数据管理拓展", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(dataManageExtendService.deleteDataManageExtendByIds(ids));
    }
}
