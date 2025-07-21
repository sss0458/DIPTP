package org.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import org.example.domain.TeachTaskTestDataManage;
import org.example.domain.vo.TeachTaskTestDataManageVo;
import org.example.domain.vo.TeachTaskTestVo;
import org.example.service.*;
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
import org.example.domain.TeachTaskTest;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 教学任务实验Controller
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@RestController
@RequestMapping("/teach_task_test")
public class TeachTaskTestController extends BaseController
{
    @Autowired
    private ITeachTaskTestService teachTaskTestService;

    @Autowired
    private ITeachTaskService teachTaskService;

    @Autowired
    private ITeachTaskTestDataManageService teachTaskTestDataManageService;

    @Autowired
    private IExampleService exampleService;

    @Autowired
    private IScoreRuleService scoreRuleService;

    /**
     * 查询教学任务实验列表
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task_test:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeachTaskTest teachTaskTest)
    {
        startPage();
        List<TeachTaskTest> list = teachTaskTestService.selectTeachTaskTestList(teachTaskTest);
        List<TeachTaskTestVo> testVoList = new ArrayList<>();

        for(TeachTaskTest item : list){
            TeachTaskTestVo vo = new TeachTaskTestVo();
            // 基础字段
            vo.setId(item.getId());
            vo.setTask(teachTaskService.selectTeachTaskById(item.getTaskId()).getTitle());
            vo.setTitle(item.getTitle());
            vo.setDataType(item.getDataType());
            vo.setDescribe(item.getDescribe());
            vo.setTheory(item.getTheory());
            vo.setAlgorithmId(item.getAlgorithmId());
            vo.setCasePath(exampleService.selectExampleById(item.getCaseId()).getCasePath());
            vo.setRule(scoreRuleService.selectScoreRuleById(item.getRuleId()).getName());
            vo.setStatus(item.getStatus());

            // 关联数据管理列表
            TeachTaskTestDataManage dataQuery = new TeachTaskTestDataManage();
            dataQuery.setTaskTestId(item.getId());
            List<TeachTaskTestDataManage> dataManages = teachTaskTestDataManageService.selectTeachTaskTestDataManageList(dataQuery);
            vo.setTeachTaskTestDataManageList(dataManages.stream().map(data -> {
                TeachTaskTestDataManageVo dataVo = new TeachTaskTestDataManageVo();
                dataVo.setId(data.getId());
                dataVo.setTask(teachTaskService.selectTeachTaskById(data.getTaskId()).getTitle());
                dataVo.setTaskTestId(data.getTaskTestId());
                dataVo.setDataManageId(data.getDataManageId());
                dataVo.setStatus(data.getStatus());
                dataVo.setTeacherId(data.getTeacherId());
                return dataVo;
            }).collect(Collectors.toList()));

            testVoList.add(vo);
        }
        return getDataTable(testVoList);
    }

    /**
     * 导出教学任务实验列表
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task_test:export')")
    @Log(title = "教学任务实验", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TeachTaskTest teachTaskTest)
    {
        List<TeachTaskTest> list = teachTaskTestService.selectTeachTaskTestList(teachTaskTest);
        ExcelUtil<TeachTaskTest> util = new ExcelUtil<TeachTaskTest>(TeachTaskTest.class);
        util.exportExcel(response, list, "教学任务实验数据");
    }

    /**
     * 获取教学任务实验详细信息
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task_test:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        TeachTaskTest item = teachTaskTestService.selectTeachTaskTestById(id);
        if (item == null) {
            return AjaxResult.error("实验不存在");
        }

        TeachTaskTestVo vo = new TeachTaskTestVo();
        // 基础字段
        vo.setId(item.getId());
        vo.setTask(teachTaskService.selectTeachTaskById(item.getTaskId()).getTitle());
        vo.setTitle(item.getTitle());
        vo.setDataType(item.getDataType());
        vo.setDescribe(item.getDescribe());
        vo.setTheory(item.getTheory());
        vo.setAlgorithmId(item.getAlgorithmId());
        vo.setCasePath(exampleService.selectExampleById(item.getCaseId()).getCasePath());
        vo.setRule(scoreRuleService.selectScoreRuleById(item.getRuleId()).getName());
        vo.setStatus(item.getStatus());

        // 关联数据管理列表
        TeachTaskTestDataManage dataQuery = new TeachTaskTestDataManage();
        dataQuery.setTaskTestId(item.getId());
        List<TeachTaskTestDataManage> dataManages = teachTaskTestDataManageService.selectTeachTaskTestDataManageList(dataQuery);
        vo.setTeachTaskTestDataManageList(dataManages.stream().map(data -> {
            TeachTaskTestDataManageVo dataVo = new TeachTaskTestDataManageVo();
            dataVo.setId(data.getId());
            dataVo.setTask(teachTaskService.selectTeachTaskById(data.getTaskId()).getTitle());
            dataVo.setTaskTestId(data.getTaskTestId());
            dataVo.setDataManageId(data.getDataManageId());
            dataVo.setStatus(data.getStatus());
            dataVo.setTeacherId(data.getTeacherId());
            return dataVo;
        }).collect(Collectors.toList()));

        return AjaxResult.success(vo);
    }

    /**
     * 新增教学任务实验
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task_test:add')")
    @Log(title = "教学任务实验", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeachTaskTest teachTaskTest)
    {
        return toAjax(teachTaskTestService.insertTeachTaskTest(teachTaskTest));
    }

    /**
     * 修改教学任务实验
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task_test:edit')")
    @Log(title = "教学任务实验", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeachTaskTest teachTaskTest)
    {
        return toAjax(teachTaskTestService.updateTeachTaskTest(teachTaskTest));
    }

    /**
     * 删除教学任务实验
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task_test:remove')")
    @Log(title = "教学任务实验", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teachTaskTestService.deleteTeachTaskTestByIds(ids));
    }
}
