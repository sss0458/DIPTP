package org.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.system.service.ISysDeptService;
import org.example.domain.vo.TeachTaskUserVo;
import org.example.service.IStudentWorkService;
import org.example.service.ITeachTaskService;
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
import org.example.domain.TeachTaskUser;
import org.example.service.ITeachTaskUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 教学任务与用户关联Controller
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@RestController
@RequestMapping("/teach_task_user")
public class TeachTaskUserController extends BaseController
{
    @Autowired
    private ITeachTaskUserService teachTaskUserService;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ITeachTaskService teachTaskService;

    @Autowired
    private IStudentWorkService studentWorkService;

    /**
     * 查询教学任务与用户关联列表
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task_user:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeachTaskUser teachTaskUser)
    {
        startPage();
        List<TeachTaskUser> list = teachTaskUserService.selectTeachTaskUserList(teachTaskUser);
        List<TeachTaskUserVo> userVoList = new ArrayList<>();

        for(TeachTaskUser item : list){
            TeachTaskUserVo vo = new TeachTaskUserVo();
            // 基础字段
            vo.setId(item.getId());
            vo.setTaskId(teachTaskService.selectTeachTaskById(item.getTaskId()).getTitle());
            vo.setStatus(item.getStatus());
            vo.setTeacherId(item.getTeacherId());

            // 关联部门信息
            SysDept dept = deptService.selectDeptById(item.getDeptId());
            if (dept != null) {
                vo.setDept(dept.getDeptName());
            }

            userVoList.add(vo);
        }
        return getDataTable(userVoList);
    }

    /**
     * 导出教学任务与用户关联列表
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task_user:export')")
    @Log(title = "教学任务与用户关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TeachTaskUser teachTaskUser)
    {
        List<TeachTaskUser> list = teachTaskUserService.selectTeachTaskUserList(teachTaskUser);
        ExcelUtil<TeachTaskUser> util = new ExcelUtil<TeachTaskUser>(TeachTaskUser.class);
        util.exportExcel(response, list, "教学任务与用户关联数据");
    }

    /**
     * 获取教学任务与用户关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task_user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        TeachTaskUser item = teachTaskUserService.selectTeachTaskUserById(id);
        if (item == null) {
            return AjaxResult.error("关联记录不存在");
        }

        TeachTaskUserVo vo = new TeachTaskUserVo();
        // 基础字段
        vo.setId(item.getId());
        vo.setTaskId(teachTaskService.selectTeachTaskById(item.getTaskId()).getTitle());
        vo.setStatus(item.getStatus());
        vo.setTeacherId(item.getTeacherId());

        // 关联部门信息
        SysDept dept = deptService.selectDeptById(item.getDeptId());
        if (dept != null) {
            vo.setDept(dept.getDeptName());
        }

        return AjaxResult.success(vo);
    }

    /**
     * 新增教学任务与用户关联
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task_user:add')")
    @Log(title = "教学任务与用户关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeachTaskUser teachTaskUser) throws IOException {
        studentWorkService.GenerateWork(teachTaskUser);
        return toAjax(teachTaskUserService.insertTeachTaskUser(teachTaskUser));
    }

    /**
     * 修改教学任务与用户关联
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task_user:edit')")
    @Log(title = "教学任务与用户关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeachTaskUser teachTaskUser)
    {
        return toAjax(teachTaskUserService.updateTeachTaskUser(teachTaskUser));
    }

    /**
     * 删除教学任务与用户关联
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task_user:remove')")
    @Log(title = "教学任务与用户关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teachTaskUserService.deleteTeachTaskUserByIds(ids));
    }
}
