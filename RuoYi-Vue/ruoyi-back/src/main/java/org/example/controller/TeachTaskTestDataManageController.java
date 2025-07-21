package org.example.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.example.domain.DataManage;
import org.example.domain.vo.TeachTaskTestDataManageVo;
import org.example.service.IDataManageService;
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
import org.example.domain.TeachTaskTestDataManage;
import org.example.service.ITeachTaskTestDataManageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 教学任务实验与数据管理关联Controller
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@RestController
@RequestMapping("/teach_task_test_data_manage")
public class TeachTaskTestDataManageController extends BaseController
{
    @Autowired
    private ITeachTaskTestDataManageService teachTaskTestDataManageService;

    @Autowired
    private IDataManageService dataManageService;

    @Autowired
    private ITeachTaskService teachTaskService;

    /**
     * 查询教学任务实验与数据管理关联列表
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task_test_data_manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeachTaskTestDataManage teachTaskTestDataManage)
    {
        startPage();
        List<TeachTaskTestDataManage> list = teachTaskTestDataManageService.selectTeachTaskTestDataManageList(teachTaskTestDataManage);
        List<TeachTaskTestDataManageVo> dataVoList = new ArrayList<>();

        for(TeachTaskTestDataManage item : list){
            TeachTaskTestDataManageVo vo = new TeachTaskTestDataManageVo();
            // 基础字段
            vo.setId(item.getId());
            vo.setTask(teachTaskService.selectTeachTaskById(item.getTaskId()).getTitle());
            vo.setTaskTestId(item.getTaskTestId());
            vo.setDataManageId(item.getDataManageId());
            vo.setStatus(item.getStatus());
            vo.setTeacherId(item.getTeacherId());

            // 关联数据管理信息
            DataManage data = dataManageService.selectDataManageById(item.getDataManageId());
            if (data != null) {
                vo.setDataType(data.getDataType());
                vo.setDataPath(data.getFilePath());
            }

            dataVoList.add(vo);
        }
        return getDataTable(dataVoList);
    }

    /**
     * 导出教学任务实验与数据管理关联列表
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task_test_data_manage:export')")
    @Log(title = "教学任务实验与数据管理关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TeachTaskTestDataManage teachTaskTestDataManage)
    {
        List<TeachTaskTestDataManage> list = teachTaskTestDataManageService.selectTeachTaskTestDataManageList(teachTaskTestDataManage);
        ExcelUtil<TeachTaskTestDataManage> util = new ExcelUtil<TeachTaskTestDataManage>(TeachTaskTestDataManage.class);
        util.exportExcel(response, list, "教学任务实验与数据管理关联数据");
    }

    /**
     * 获取教学任务实验与数据管理关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task_test_data_manage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        TeachTaskTestDataManage item = teachTaskTestDataManageService.selectTeachTaskTestDataManageById(id);
        if (item == null) {
            return AjaxResult.error("关联记录不存在");
        }

        TeachTaskTestDataManageVo vo = new TeachTaskTestDataManageVo();
        // 基础字段
        vo.setId(item.getId());
        vo.setTask(teachTaskService.selectTeachTaskById(item.getTaskId()).getTitle());
        vo.setTaskTestId(item.getTaskTestId());
        vo.setDataManageId(item.getDataManageId());
        vo.setStatus(item.getStatus());
        vo.setTeacherId(item.getTeacherId());

        // 关联数据管理信息
        DataManage data = dataManageService.selectDataManageById(item.getDataManageId());
        if (data != null) {
            vo.setDataType(data.getDataType());
            vo.setDataPath(data.getFilePath());
        }

        return AjaxResult.success(vo);
    }

    /**
     * 新增教学任务实验与数据管理关联
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task_test_data_manage:add')")
    @Log(title = "教学任务实验与数据管理关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeachTaskTestDataManage teachTaskTestDataManage)
    {
        return toAjax(teachTaskTestDataManageService.insertTeachTaskTestDataManage(teachTaskTestDataManage));
    }

    /**
     * 修改教学任务实验与数据管理关联
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task_test_data_manage:edit')")
    @Log(title = "教学任务实验与数据管理关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeachTaskTestDataManage teachTaskTestDataManage)
    {
        return toAjax(teachTaskTestDataManageService.updateTeachTaskTestDataManage(teachTaskTestDataManage));
    }

    /**
     * 删除教学任务实验与数据管理关联
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task_test_data_manage:remove')")
    @Log(title = "教学任务实验与数据管理关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teachTaskTestDataManageService.deleteTeachTaskTestDataManageByIds(ids));
    }
}
