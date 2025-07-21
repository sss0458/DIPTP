package org.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.service.ISysDeptService;
import org.example.domain.TeachTaskTest;
import org.example.domain.vo.TeachTaskUserVo;
import org.example.domain.vo.TeachTaskTestVo;
import org.example.domain.vo.TeachTaskVo;
import org.example.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.example.domain.TeachTask;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.example.domain.TeachTaskUser;

/**
 * 教学任务Controller
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@RestController
@RequestMapping("/teach_task")
public class TeachTaskController extends BaseController
{

    @Autowired
    private ITeachTaskService teachTaskService;

    @Autowired
    private ITeachTaskUserService teachTaskUserService;

    @Autowired
    private ITeachTaskTestService teachTaskTestService;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private IExampleService exampleService;

    @Autowired IScoreRuleService scoreRuleService;


    /**
     * 查询教学任务列表
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeachTask teachTask)
    {
        startPage();
        List<TeachTask> list = teachTaskService.selectTeachTaskList(teachTask);
        List<TeachTaskVo> taskVoList = new ArrayList<>();

        for(TeachTask item : list){
            TeachTaskVo vo = new TeachTaskVo();
            // 基础字段
            vo.setId(item.getId());
            vo.setTitle(item.getTitle());
            vo.setDescribe(item.getDescribe());
            vo.setStartDate(item.getStartDate());
            vo.setEndDate(item.getEndDate());
            vo.setStatus(item.getStatus());
            vo.setTeacherId(item.getTeacherId());

            // 关联实验列表
            TeachTaskTest testQuery = new TeachTaskTest();
            testQuery.setTaskId(item.getId());
            List<TeachTaskTest> tests = teachTaskTestService.selectTeachTaskTestList(testQuery);
            vo.setTeachTaskTestList(tests.stream().map(test -> {
                TeachTaskTestVo testVo = new TeachTaskTestVo();
                testVo.setId(test.getId());
                testVo.setTitle(test.getTitle());
                testVo.setDataType(test.getDataType());
                testVo.setDescribe(test.getDescribe());
                testVo.setTheory(test.getTheory());
                testVo.setAlgorithmId(test.getAlgorithmId());
                testVo.setCasePath(exampleService.selectExampleById(test.getCaseId()).getCasePath());
                testVo.setRule(scoreRuleService.selectScoreRuleById(test.getRuleId()).getName());
                return testVo;
            }).collect(Collectors.toList()));

            // 关联班级列表
            TeachTaskUser userQuery = new TeachTaskUser();
            userQuery.setTaskId(item.getId());
            List<TeachTaskUser> users = teachTaskUserService.selectTeachTaskUserList(userQuery);
            vo.setTeachTaskUserList(users.stream().map(user -> {
                TeachTaskUserVo userVo = new TeachTaskUserVo();
                userVo.setId(user.getId());
                userVo.setDept(deptService.selectDeptById(user.getDeptId()).getDeptName());
                userVo.setStatus(user.getStatus());
                userVo.setTeacherId(user.getTeacherId());
                return userVo;
            }).collect(Collectors.toList()));

            taskVoList.add(vo);
        }
        return getDataTable(taskVoList);
    }

    /**
     * 根据部门ID获取用户的教学任务列表
     * @param deptId 部门ID
     * @return 教学任务列表
     */
    @PreAuthorize("@ss.hasPermi('example:work:list')")
    @GetMapping("/getDept/{deptId}")
    public AjaxResult getTasksByDeptId(@PathVariable Long deptId) {
        // 1. 查询 teach_task_user 表获取 taskId 列表
        TeachTaskUser taskUserQuery = new TeachTaskUser();
        taskUserQuery.setDeptId(deptId);
        List<TeachTaskUser> taskUsers = teachTaskUserService.selectTeachTaskUserList(taskUserQuery);

        if (taskUsers == null || taskUsers.isEmpty()) {
            return success(new ArrayList<>());
        }

        // 2. 提取 taskId 列表
        List<Long> taskIds = taskUsers.stream()
                .map(TeachTaskUser::getTaskId)
                .collect(Collectors.toList());

        // 3. 查询 teach_task 表获取任务详情
        List<TeachTask> tasks = teachTaskService.selectTeachTaskListByIds(taskIds);

        return success(tasks);
    }

    /**
     * 导出教学任务列表
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task:export')")
    @Log(title = "教学任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TeachTask teachTask)
    {
        List<TeachTask> list = teachTaskService.selectTeachTaskList(teachTask);
        ExcelUtil<TeachTask> util = new ExcelUtil<TeachTask>(TeachTask.class);
        util.exportExcel(response, list, "教学任务数据");
    }

    /**
     * 获取教学任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        TeachTask item = teachTaskService.selectTeachTaskById(id);
        if (item == null) {
            return AjaxResult.error("教学任务不存在");
        }

        TeachTaskVo vo = new TeachTaskVo();
        // 基础字段
        vo.setId(item.getId());
        vo.setTitle(item.getTitle());
        vo.setDescribe(item.getDescribe());
        vo.setStartDate(item.getStartDate());
        vo.setEndDate(item.getEndDate());
        vo.setStatus(item.getStatus());
        vo.setTeacherId(item.getTeacherId());

        // 关联实验列表
        TeachTaskTest testQuery = new TeachTaskTest();
        testQuery.setTaskId(item.getId());
        List<TeachTaskTest> tests = teachTaskTestService.selectTeachTaskTestList(testQuery);
        vo.setTeachTaskTestList(tests.stream().map(test -> {
            TeachTaskTestVo testVo = new TeachTaskTestVo();
            testVo.setId(test.getId());
            testVo.setTitle(test.getTitle());
            testVo.setDataType(test.getDataType());
            testVo.setDescribe(test.getDescribe());
            testVo.setTheory(test.getTheory());
            testVo.setAlgorithmId(test.getAlgorithmId());
            testVo.setCasePath(exampleService.selectExampleById(test.getCaseId()).getCasePath());
            testVo.setRule(scoreRuleService.selectScoreRuleById(test.getRuleId()).getName());
            testVo.setStatus(test.getStatus());
            return testVo;
        }).collect(Collectors.toList()));

        // 关联班级列表
        TeachTaskUser userQuery = new TeachTaskUser();
        userQuery.setTaskId(item.getId());
        List<TeachTaskUser> users = teachTaskUserService.selectTeachTaskUserList(userQuery);
        vo.setTeachTaskUserList(users.stream().map(user -> {
            TeachTaskUserVo userVo = new TeachTaskUserVo();
            userVo.setId(user.getId());
            userVo.setDept(deptService.selectDeptById(user.getDeptId()).getDeptName());
            userVo.setStatus(user.getStatus());
            userVo.setTeacherId(user.getTeacherId());
            return userVo;
        }).collect(Collectors.toList()));

        return AjaxResult.success(vo);
    }

    /**
     * 新增教学任务
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task:add')")
    @Log(title = "教学任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeachTask teachTask)
    {
        return toAjax(teachTaskService.insertTeachTask(teachTask));
    }

    /**
     * 修改教学任务
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task:edit')")
    @Log(title = "教学任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeachTask teachTask)
    {
        return toAjax(teachTaskService.updateTeachTask(teachTask));
    }

    /**
     * 删除教学任务
     */
    @PreAuthorize("@ss.hasPermi('example:teach_task:remove')")
    @Log(title = "教学任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teachTaskService.deleteTeachTaskByIds(ids));
    }
}
