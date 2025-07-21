package org.example.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.example.domain.vo.ScoreRuleDimensionVo;
import org.example.domain.vo.StudentWorkScoreVo;
import org.example.domain.vo.StudentWorkVo;
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
import org.example.domain.StudentWorkScore;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生作业评分Controller
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@RestController
@RequestMapping("/score")
public class StudentWorkScoreController extends BaseController
{
    @Autowired
    private IStudentWorkScoreService studentWorkScoreService;

    /**
     * 查询学生作业评分列表
     */
    @PreAuthorize("@ss.hasPermi('example:score:list')")
    @GetMapping("/list")
    public TableDataInfo list(StudentWorkScore studentWorkScore)
    {
        startPage();
        List<StudentWorkScore> list = studentWorkScoreService.selectStudentWorkScoreList(studentWorkScore);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('example:score:list')")
    @GetMapping("/tList")
    public TableDataInfo Teacherlist(Long teacherId)
    {
        startPage();
        List<StudentWorkScoreVo> list = studentWorkScoreService.selectStudentWorkScoreVoListByTeacherId(teacherId);
        return getDataTable(list);
    }

    /**
     * 根据评分ID获取所有案例数据
     * @param scoreId 评分ID
     * @return 包含对应数据的视图对象
     */
    //@PreAuthorize("@ss.hasPermi('example:score:query')")
    @GetMapping("/fullscore/{scoreId}")
    public AjaxResult getFullStudentWorkScoreDataByScoreId(@PathVariable Long scoreId) {

        StudentWorkScoreVo studentWorkScoreVo =  studentWorkScoreService.selectStudentWorkScoreVoById(scoreId);
        if (studentWorkScoreVo == null) {
            return error("维度不存在");
        }
        return success(studentWorkScoreVo);
    }

    /**
     * 获取所有评分数据
     *
     * @return 包含所有案例数据的视图对象列表
     */
    //@PreAuthorize("@ss.hasPermi('example:score:list')")
    @GetMapping("/full/all")
    public AjaxResult getAllFullStudentWorkScoreData() {
        List<StudentWorkScoreVo>  studentWorkScoreVos = studentWorkScoreService.selectStudentWorkScoreVoList();
        return success(studentWorkScoreVos);
    }

    /**
     * 导出学生作业评分列表
     */
    @PreAuthorize("@ss.hasPermi('example:score:export')")
    @Log(title = "学生作业评分", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StudentWorkScore studentWorkScore)
    {
        List<StudentWorkScore> list = studentWorkScoreService.selectStudentWorkScoreList(studentWorkScore);
        ExcelUtil<StudentWorkScore> util = new ExcelUtil<StudentWorkScore>(StudentWorkScore.class);
        util.exportExcel(response, list, "学生作业评分数据");
    }

    /**
     * 获取学生作业评分详细信息
     */
    @PreAuthorize("@ss.hasPermi('example:score:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(studentWorkScoreService.selectStudentWorkScoreById(id));
    }


    /**
     * 新增学生作业评分
     */
    @PreAuthorize("@ss.hasPermi('example:score:add')")
    @Log(title = "学生作业评分", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StudentWorkScore studentWorkScore)
    {
        return toAjax(studentWorkScoreService.insertStudentWorkScore(studentWorkScore));
    }

    /**
     * 修改学生作业评分
     */
    @PreAuthorize("@ss.hasPermi('example:score:edit')")
    @Log(title = "学生作业评分", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StudentWorkScore studentWorkScore)
    {
        return toAjax(studentWorkScoreService.updateStudentWorkScore(studentWorkScore));
    }

    /**
     * 删除学生作业评分
     */
    @PreAuthorize("@ss.hasPermi('example:score:remove')")
    @Log(title = "学生作业评分", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(studentWorkScoreService.deleteStudentWorkScoreByIds(ids));
    }
}
