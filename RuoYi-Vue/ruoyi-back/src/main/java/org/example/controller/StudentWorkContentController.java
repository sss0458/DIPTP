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
import org.example.domain.StudentWorkContent;
import org.example.service.IStudentWorkContentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生作业内容Controller
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@RestController
@RequestMapping("/content")
public class StudentWorkContentController extends BaseController
{
    @Autowired
    private IStudentWorkContentService studentWorkContentService;

    /**
     * 查询学生作业内容列表
     */
    @PreAuthorize("@ss.hasPermi('example:content:list')")
    @GetMapping("/list")
    public TableDataInfo list(StudentWorkContent studentWorkContent)
    {
        startPage();
        List<StudentWorkContent> list = studentWorkContentService.selectStudentWorkContentList(studentWorkContent);
        return getDataTable(list);
    }

    /**
     * 导出学生作业内容列表
     */
    @PreAuthorize("@ss.hasPermi('example:content:export')")
    @Log(title = "学生作业内容", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StudentWorkContent studentWorkContent)
    {
        List<StudentWorkContent> list = studentWorkContentService.selectStudentWorkContentList(studentWorkContent);
        ExcelUtil<StudentWorkContent> util = new ExcelUtil<StudentWorkContent>(StudentWorkContent.class);
        util.exportExcel(response, list, "学生作业内容数据");
    }

    /**
     * 获取学生作业内容详细信息
     */
    @PreAuthorize("@ss.hasPermi('example:content:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(studentWorkContentService.selectStudentWorkContentById(id));
    }

    /**
     * 新增学生作业内容
     */
    @PreAuthorize("@ss.hasPermi('example:content:add')")
    @Log(title = "学生作业内容", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StudentWorkContent studentWorkContent)
    {
        return toAjax(studentWorkContentService.insertStudentWorkContent(studentWorkContent));
    }

    /**
     * 修改学生作业内容
     */
    @PreAuthorize("@ss.hasPermi('example:content:edit')")
    @Log(title = "学生作业内容", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StudentWorkContent studentWorkContent)
    {
        return toAjax(studentWorkContentService.updateStudentWorkContent(studentWorkContent));
    }

    /**
     * 删除学生作业内容
     */
    @PreAuthorize("@ss.hasPermi('example:content:remove')")
    @Log(title = "学生作业内容", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(studentWorkContentService.deleteStudentWorkContentByIds(ids));
    }
}
