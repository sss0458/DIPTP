package org.example.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import org.example.domain.*;
import org.example.domain.vo.*;
import org.example.service.IDataManageService;
import org.example.service.IStudentWorkContentService;
import org.example.service.ITeachTaskService;
import org.example.utils.FileUtils;
import org.example.utils.PDFUtils;
import org.example.utils.WordUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import org.example.service.IStudentWorkService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;
import org.example.utils.AutomatedScoringService;
import com.ruoyi.system.service.ISysUserService;

/**
 * 学生作业Controller
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@RestController
@RequestMapping("/work")
public class StudentWorkController extends BaseController
{

    @Autowired
    private IStudentWorkService studentWorkService;

    @Autowired
    private IStudentWorkContentService studentWorkContentService;

    @Autowired
    private AutomatedScoringService scoreService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ITeachTaskService teachTaskService;
    @Autowired
    private IDataManageService dataManageService;

    private FileUtils utils = new FileUtils();

    /**
     * 根据作业ID获取所有数据（包含内容和Base64图片）
     * @param workId 作业ID
     * @return 包含所有数据的视图对象
     */
    @PreAuthorize("@ss.hasPermi('example:work:query')")
    @GetMapping("/fullwork/{workId}")
    public AjaxResult getFullWorkDataByWorkID(@PathVariable Long workId) {
        // 获取作业基本信息
        StudentWorkVo studentWorkVo = studentWorkService.selectStudentWorkVoById(workId);
        if (studentWorkVo == null) {
            return error("作业不存在");
        }
        DataManage dataManage = dataManageService.selectDataManageById(studentWorkVo.getDataManageId());
        String path = dataManage.getFilePath();

        // 获取作业内容列表
        StudentWorkContent contentQuery = new StudentWorkContent();
        contentQuery.setWorkId(workId);
        List<StudentWorkContent> contents = studentWorkContentService.selectStudentWorkContentList(contentQuery);

        // 转换为视图对象列表
        List<StudentWorkContentVo> contentVos = contents.stream().map(content -> {
            StudentWorkContentVo contentVo = new StudentWorkContentVo();
            contentVo.setId(content.getId());
            contentVo.setTaskId(content.getTaskId());
            contentVo.setTaskTestId(content.getTaskTestId());
            contentVo.setWorkId(content.getWorkId());
            contentVo.setVersion(content.getVersion());
            contentVo.setCode(content.getCode());
            contentVo.setResult(content.getResult());
            contentVo.setMainBody(content.getMainBody());
            contentVo.setSummary(content.getSummary());
            contentVo.setStatus(content.getStatus());
            contentVo.setSteps(content.getStudentWorkContentStepList());

            // 读取图片文件并转换为Base64
            if (content.getWorkPath() != null && !content.getWorkPath().isEmpty()) {
                contentVo.setWorkPath(content.getWorkPath());
                String finallPath = path+"/"+contentVo.getWorkPath();
                try {
                    byte[] fileContent = Files.readAllBytes(Paths.get(finallPath));
                    String base64Image = Base64.getEncoder().encodeToString(fileContent);
                    contentVo.setImageBase64(base64Image);
                } catch (IOException e) {
                    // 如果读取失败，imageBase64保持为null
                }
            }
            return contentVo;
        }).collect(Collectors.toList());
        studentWorkVo.setContents(contentVos);
        return success(studentWorkVo);
    }

    /**
     * 根据学生ID获取所有作业数据（包含内容和Base64图片）
     * @param studentId 学生ID
     * @return 包含所有作业数据的视图对象列表
     */
    @PreAuthorize("@ss.hasPermi('example:work:query')")
    @GetMapping("/fullstudent/{studentId}")
    public AjaxResult getFullWorkDataByStudentId(@PathVariable Long studentId) {
        // 获取该学生的所有作业基本信息
        StudentWork workQuery = new StudentWork();
        workQuery.setStudentId(studentId);
        List<StudentWork> works = studentWorkService.selectStudentWorkList(workQuery);

        if (works == null || works.isEmpty()) {
            return success("该学生暂无作业数据");
        }

        // 转换为视图对象列表
        List<StudentWorkVo> workVos = works.stream().map(work -> {
            StudentWorkVo workVo = studentWorkService.selectStudentWorkVoById(work.getId());
            DataManage manage = dataManageService.selectDataManageById(workVo.getDataManageId());
            if (workVo != null) {
                // 获取作业内容列表
                StudentWorkContent contentQuery = new StudentWorkContent();
                contentQuery.setWorkId(work.getId());
                List<StudentWorkContent> contents = studentWorkContentService.selectStudentWorkContentList(contentQuery);

                // 转换为内容视图对象列表
                List<StudentWorkContentVo> contentVos = contents.stream().map(content -> {
                    StudentWorkContentVo contentVo = new StudentWorkContentVo();
                    contentVo.setId(content.getId());
                    contentVo.setTaskId(content.getTaskId());
                    contentVo.setTaskTestId(content.getTaskTestId());
                    contentVo.setWorkId(content.getWorkId());
                    contentVo.setVersion(content.getVersion());
                    contentVo.setCode(content.getCode());
                    contentVo.setResult(content.getResult());
                    contentVo.setMainBody(content.getMainBody());
                    contentVo.setSummary(content.getSummary());
                    contentVo.setStatus(content.getStatus());
                    contentVo.setSteps(content.getStudentWorkContentStepList());

                    // 读取图片文件并转换为Base64
                    if (content.getWorkPath() != null && !content.getWorkPath().isEmpty()) {
                        contentVo.setWorkPath(content.getWorkPath());
                        String fin = manage.getFilePath()+"/"+contentVo.getWorkPath();
                        try {
                            byte[] fileContent = Files.readAllBytes(Paths.get(fin));
                            String base64Image = Base64.getEncoder().encodeToString(fileContent);
                            contentVo.setImageBase64(base64Image);
                        } catch (IOException e) {
                            // 如果读取失败，imageBase64保持为null
                        }
                    }
                    return contentVo;
                }).collect(Collectors.toList());
                workVo.setContents(contentVos);
            }
            return workVo;
        }).collect(Collectors.toList());

        return success(workVos);
    }

    /**
     * 获取所有作业及其完整数据（包含内容和Base64图片）
     * @return 包含所有作业数据的列表
     */
    @PreAuthorize("@ss.hasPermi('example:work:list')")
    @GetMapping("/full/all")
    public TableDataInfo getAllFullWorkData() {
        // 获取所有作业
        List<StudentWorkVo> works = studentWorkService.selectStudentWorkVoList();

        // 转换为视图对象列表
        List<StudentWorkVo> workVos = works.stream().map(work -> {

            // 获取作业内容列表
            StudentWorkContent contentQuery = new StudentWorkContent();
            contentQuery.setWorkId(work.getId());
            DataManage dataManage = dataManageService.selectDataManageById(work.getDataManageId());
            String path = dataManage.getFilePath();
            List<StudentWorkContent> contents = studentWorkContentService.selectStudentWorkContentList(contentQuery);

            // 转换为内容视图对象列表
            List<StudentWorkContentVo> contentVos = contents.stream().map(content -> {
                StudentWorkContentVo contentVo = new StudentWorkContentVo();
                contentVo.setId(content.getId());
                contentVo.setTaskId(content.getTaskId());
                contentVo.setTaskTestId(content.getTaskTestId());
                contentVo.setWorkId(content.getWorkId());
                contentVo.setVersion(content.getVersion());
                contentVo.setCode(content.getCode());
                contentVo.setResult(content.getResult());
                contentVo.setMainBody(content.getMainBody());
                contentVo.setSummary(content.getSummary());
                contentVo.setStatus(content.getStatus());
                contentVo.setSteps(content.getStudentWorkContentStepList());

                // 读取图片文件并转换为Base64
                if (content.getWorkPath() != null && !content.getWorkPath().isEmpty()) {
                    contentVo.setWorkPath(content.getWorkPath());
                    try {
                        byte[] fileContent = Files.readAllBytes(Paths.get(path+"/"+content.getWorkPath()));
                        String base64Image = Base64.getEncoder().encodeToString(fileContent);
                        contentVo.setImageBase64(base64Image);
                    } catch (IOException e) {
                        // 如果读取失败，imageBase64保持为null
                    }
                }
                return contentVo;
            }).collect(Collectors.toList());
            work.setContents(contentVos);
            return work;
        }).collect(Collectors.toList());
        return getDataTable(workVos);
    }

    /**
     * 导出学生实验报告PDF
     *
     */
    @PreAuthorize("@ss.hasPermi('example:work:export')")
    @Log(title = "学生作业", businessType = BusinessType.EXPORT)
    @PostMapping("/exportPDF")
    public void exportPDF(HttpServletResponse response, Long id) throws IOException {
        StudentWork studentWork = studentWorkService.selectStudentWorkById(id);
        List<StudentWorkContent> studentWorkContentList = studentWorkContentService.selectStudentWorkContentListByWordId(studentWork.getId());
        SysUser user = sysUserService.selectUserById(studentWork.getStudentId());
        TeachTask task = teachTaskService.selectTeachTaskById(studentWork.getTaskId());
        StudentWorkReportVo studentWorkReportVo = new StudentWorkReportVo();
        List<StudentWorkContentStep> studentWorkContentStepList = new ArrayList<>();
        List<StudentWorkReportVo> list = new ArrayList<>();
        StringBuilder step = new StringBuilder();
        StringBuilder workPath = new StringBuilder();
        int i = 1;
        for(StudentWorkContent studentWorkContent : studentWorkContentList){
            studentWorkContentStepList = studentWorkContentService.selectStudentWorkContentStepListByContentId(studentWorkContent.getId());
            for(StudentWorkContentStep studentWorkContentStep : studentWorkContentStepList){
                step.append(i).append(". ").append(studentWorkContentStep.getContent()).append("\n");
                i++;
            }
            workPath.append(studentWorkContent.getWorkPath()).append("\n");
        }
        studentWorkReportVo.setStudentName(user.getUserName());
        studentWorkReportVo.setWorkPath(workPath.toString());
        studentWorkReportVo.setScore(studentWork.getScore().toString());
        studentWorkReportVo.setStep(step.toString());
        studentWorkReportVo.setTaskTitle(task.getTitle());

        list.add(studentWorkReportVo);

        PDFUtils<StudentWorkReportVo> util = new PDFUtils<StudentWorkReportVo>(StudentWorkReportVo.class);
        util.exportPDF(response, list, "实验报告");
    }

    /**
     * 导出学生实验报告PDF
     *
     */
    @PreAuthorize("@ss.hasPermi('example:work:export')")
    @Log(title = "学生作业", businessType = BusinessType.EXPORT)
    @PostMapping("/exportWord")
    public void exportWord(HttpServletResponse response, Long id) throws IOException {
        StudentWork studentWork = studentWorkService.selectStudentWorkById(id);
        List<StudentWorkContent> studentWorkContentList = studentWorkContentService.selectStudentWorkContentListByWordId(studentWork.getId());
        SysUser user = sysUserService.selectUserById(studentWork.getStudentId());
        TeachTask task = teachTaskService.selectTeachTaskById(studentWork.getTaskId());
        StudentWorkReportVo studentWorkReportVo = new StudentWorkReportVo();
        List<StudentWorkContentStep> studentWorkContentStepList = new ArrayList<>();
        List<StudentWorkReportVo> list = new ArrayList<>();
        StringBuilder step = new StringBuilder();
        StringBuilder workPath = new StringBuilder();
        int i = 1;
        for(StudentWorkContent studentWorkContent : studentWorkContentList){
            studentWorkContentStepList = studentWorkContentService.selectStudentWorkContentStepListByContentId(studentWorkContent.getId());
            for(StudentWorkContentStep studentWorkContentStep : studentWorkContentStepList){
                step.append(i).append(". ").append(studentWorkContentStep.getContent()).append("\n");
                i++;
            }
            workPath.append(studentWorkContent.getWorkPath()).append("\n");
        }
        studentWorkReportVo.setStudentName(user.getUserName());
        studentWorkReportVo.setWorkPath(workPath.toString());
        studentWorkReportVo.setScore(studentWork.getScore().toString());
        studentWorkReportVo.setStep(step.toString());
        studentWorkReportVo.setTaskTitle(task.getTitle());

        list.add(studentWorkReportVo);

        WordUtils<StudentWorkReportVo> util = new WordUtils<StudentWorkReportVo>(StudentWorkReportVo.class);
        util.exportWord(response, list, "实验报告");
    }


    /**
     * 添加作业
     */

    @PreAuthorize("@ss.hasPermi('example:work:add')")
    @Log(title = "学生作业内容", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult addWorkContent(
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestPart("content") StudentWorkContent content,
            @RequestPart("dataManage") DataManage mange
              ) {
        StudentWork work = studentWorkService.selectStudentWorkById(content.getWorkId());
        if (work == null) {return error("作业不存在");}
        try {
            // 1. 处理图片文件
            if (image != null && !image.isEmpty()) {
                utils.uploadFileAndSaveDataManage(image, work.getStudentId(), mange);
                content.setWorkPath(image.getOriginalFilename());
            }

            // 2. 更新作业内容版本和最后修改时间
            content.setVersion(content.getVersion() == null ? 1 : content.getVersion() + 1);
            content.setLastDate(new Date());
            content.setStatus("1"); // 设置为已提交状态

            // 3. 保存作业内容
            int contentResult = studentWorkContentService.insertStudentWorkContent(content);
            if (contentResult <= 0) {
                return error("保存作业内容失败");
            }

            //4. 实现自动化评分
            scoreService.scoreStudentWork(content);

            // 5. 更新关联的StudentWork信息
                // 增加提交次数
                work.setQuantity(work.getQuantity() == null ? 1 : work.getQuantity() + 1);
                // 更新状态为已提交
                work.setStatus("1");
                // 更新最后修改时间
                work.setLastDate(new Date());
                work.setCriticizeStatus("1");
                studentWorkService.updateStudentWork(work);

            return success("保存成功");
        } catch (Exception e) {
            return error("保存失败: " + e.getMessage());
        }
    }




    /**
     * 查询学生作业列表
     */
    @PreAuthorize("@ss.hasPermi('example:work:list')")
    @GetMapping("/list")
    public TableDataInfo list(StudentWork studentWork)
    {
        startPage();
        List<StudentWork> list = studentWorkService.selectStudentWorkList(studentWork);
        return getDataTable(list);
    }

    /**
     * 导出学生作业列表
     */
    @PreAuthorize("@ss.hasPermi('example:work:export')")
    @Log(title = "学生作业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StudentWork studentWork)
    {
        List<StudentWork> list = studentWorkService.selectStudentWorkList(studentWork);
        ExcelUtil<StudentWork> util = new ExcelUtil<StudentWork>(StudentWork.class);
        util.exportExcel(response, list, "学生作业数据");
    }

    /**
     * 获取学生作业详细信息
     */
    @PreAuthorize("@ss.hasPermi('example:work:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(studentWorkService.selectStudentWorkById(id));
    }

    /**
     * 新增学生作业
     */
    @PreAuthorize("@ss.hasPermi('example:work:add')")
    @Log(title = "学生作业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StudentWork studentWork)
    {
        return toAjax(studentWorkService.insertStudentWork(studentWork));
    }

    /**
     * 修改学生作业
     */
    @PreAuthorize("@ss.hasPermi('example:work:edit')")
    @Log(title = "学生作业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StudentWork studentWork)
    {
        return toAjax(studentWorkService.updateStudentWork(studentWork));
    }

    /**
     * 删除学生作业
     */
    @PreAuthorize("@ss.hasPermi('example:work:remove')")
    @Log(title = "学生作业", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(studentWorkService.deleteStudentWorkByIds(ids));
    }

    /**
     * 统计学生结果
     */
    @PreAuthorize("@ss.hasPermi('example:work:statistic')")
    @GetMapping("/statisticSummary")
    public StudentWorkStatisticVo getStatisticSummary(
            @RequestParam(value = "taskId", required = false) Long taskId,
            @RequestParam(value = "testId", required = false) Long testId
    ) {
        return studentWorkService.getStatisticSummary(taskId, testId);
    }
}
