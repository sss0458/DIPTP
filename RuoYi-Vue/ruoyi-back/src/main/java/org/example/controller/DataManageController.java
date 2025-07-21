package org.example.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.example.domain.vo.DataManageVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import org.example.domain.DataManage;
import org.example.service.IDataManageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 数据管理Controller
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@RestController
@RequestMapping("/data_manage")
public class DataManageController extends BaseController
{
    @Autowired
    private IDataManageService dataManageService;

    /**
     * 查询数据管理列表
     */
    @PreAuthorize("@ss.hasPermi('example:data_manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(DataManage dataManage)
    {
        startPage();
        List<DataManageVo> list = dataManageService.selectDataManageList(dataManage);
        return getDataTable(list);
    }



    /**
     * 获取数据管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('example:data_manage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(dataManageService.selectDataManageVoById(id));
    }

    /**
     * 新增数据管理
     */
    @PreAuthorize("@ss.hasPermi('example:data_manage:add')")
    @Log(title = "数据管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DataManage dataManage)
    {
        return toAjax(dataManageService.insertDataManage(dataManage));
    }

    /**
     * 修改数据管理
     */
    @PreAuthorize("@ss.hasPermi('example:data_manage:edit')")
    @Log(title = "数据管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DataManage dataManage)
    {
        return toAjax(dataManageService.updateDataManage(dataManage));
    }

    /**
     * 删除数据管理
     */
    @PreAuthorize("@ss.hasPermi('example:data_manage:remove')")
    @Log(title = "数据管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(dataManageService.deleteDataManageByIds(ids));
    }

    /**
     * 新增的通用媒体文件上传接口，支持按用户ID创建文件夹并只存储文件夹路径
     */
    @PreAuthorize("@ss.hasPermi('system:dataManage:upload')")
    @Log(title = "媒体文件及管理信息上传(用户定制路径，只存目录)", businessType = BusinessType.INSERT)
    @PostMapping("/uploadFileWithUserDetailsOnlyDir")
    public AjaxResult uploadFileWithUserDetailsOnlyDir(
            @RequestPart("file") MultipartFile file,
            @RequestPart("dataManage") DataManage dataManage,
            @RequestParam("userId") Long userId) {

        if (file.isEmpty()) {
            return AjaxResult.error("上传文件不能为空。");
        }

        if (userId == null) {
            return AjaxResult.error("用户ID不能为空，无法创建用户专属文件夹。");
        }

        try {
            String relativeUserDirPath = File.separator + "user_uploads" + File.separator + userId;
            String absoluteUserUploadPath = RuoYiConfig.getProfile() + relativeUserDirPath;
            File userDir = new File(absoluteUserUploadPath);

            if (!userDir.exists()) {
                boolean created = userDir.mkdirs();
                if (!created) {
                    return AjaxResult.error("无法创建用户专属文件夹：" + absoluteUserUploadPath);
                }
            }

            String fullFilePath = FileUploadUtils.upload(absoluteUserUploadPath, file);
            if (StringUtils.isEmpty(fullFilePath)) {
                return AjaxResult.error("文件上传失败，未能生成有效的文件路径。");
            }

            // 核心：只存储文件夹的相对路径，例如：/user_uploads/123
            String storedFilePath = relativeUserDirPath.replace(File.separator, "/");
            dataManage.setFilePath(storedFilePath);

            // 如果需要存储文件名，请确保 DataManage 类有相应字段并在此处设置
            // dataManage.setOriginalFileName(file.getOriginalFilename());

            dataManage.setCreateBy(SecurityUtils.getUsername());
            dataManage.setCreateTime(DateUtils.getNowDate());
            if (StringUtils.isEmpty(dataManage.getDelFlag())) {
                dataManage.setDelFlag("0");
            }
            if (StringUtils.isEmpty(dataManage.getStatus())) {
                dataManage.setStatus("0");
            }
            if (StringUtils.isEmpty(dataManage.getDataType())) {
                dataManage.setDataType("FILE");
            }
            if (StringUtils.isEmpty(dataManage.getSource())) {
                dataManage.setSource("用户专属上传");
            }
            if (StringUtils.isEmpty(dataManage.getSign())) {
                dataManage.setSign("用户文件_" + userId + "_" + DateUtils.dateTimeNow("yyyyMMddHHmmss") + "_" + UUID.randomUUID().toString().substring(0, 4));
            }

            int rows = dataManageService.insertDataManage(dataManage);

            if (rows > 0) {
                DataManageVo resultVo = dataManageService.selectDataManageVoById(dataManage.getId());
                return success(resultVo);
            } else {
                return error("文件及管理信息保存失败，请稍后重试或联系管理员。");
            }

        } catch (Exception e) {
            return AjaxResult.error("文件及管理信息上传过程中发生错误，请联系管理员。");
        }
    }

    /**
     * 新增的通用媒体文件上传接口
     */
    @PreAuthorize("@ss.hasPermi('system:dataManage:upload')")
    @Log(title = "媒体文件及管理信息上传", businessType = BusinessType.INSERT)
    @PostMapping("/uploadFileWithDetails")
    public AjaxResult uploadFileWithDetails(
            @RequestPart("file") MultipartFile file, // 接收文件
            @RequestPart("dataManage") DataManage dataManage) { // 接收 DataManage 对象，注意参数名改为 "dataManage"

        if (file.isEmpty()) {
            return AjaxResult.error("上传文件不能为空。");
        }
        // dataManage 对象如果接收失败（如 JSON 格式问题），Spring 会直接抛异常，不会为空
        // 但可以在业务逻辑上检查其关键字段是否有效

        try {
            // 1. 上传文件到服务器，获取文件路径
            String filePath = FileUploadUtils.upload(RuoYiConfig.getProfile(), file);
            if (StringUtils.isEmpty(filePath)) {
                return AjaxResult.error("文件上传失败，未能生成有效的文件路径。");
            }

            // 2. 填充 DataManage 对象（由 Spring 自动绑定后，这里进行后端填充）
            dataManage.setFilePath(filePath); // 将上传后的文件路径设置到 DataManage 对象中
            // 自动填充审计字段，这些字段建议统一在 Service 层填充，这里仅作示例
            dataManage.setCreateBy(SecurityUtils.getUsername());
            dataManage.setCreateTime(DateUtils.getNowDate());
            if (StringUtils.isEmpty(dataManage.getDelFlag())) {
                dataManage.setDelFlag("0"); // 默认未删除
            }
            if (StringUtils.isEmpty(dataManage.getStatus())) {
                dataManage.setStatus("0"); // 默认正常状态
            }
            if (StringUtils.isEmpty(dataManage.getDataType())) {
                dataManage.setDataType("FILE"); // 如果前端未指定，默认设为通用文件类型
            }
            if (StringUtils.isEmpty(dataManage.getSource())) {
                dataManage.setSource("用户通用上传"); // 如果前端未指定，默认来源
            }
            // 如果 sign 字段为空，自动生成一个默认值
            if (StringUtils.isEmpty(dataManage.getSign())) {
                dataManage.setSign("通用文件_" + DateUtils.dateTimeNow("yyyyMMddHHmmss") + "_" + UUID.randomUUID().toString().substring(0, 4));
            }


            // 3. 调用 Service 层方法进行插入，包括 DataManage 和其关联的 DataManageExtend
            int rows = dataManageService.insertDataManage(dataManage); // insertDataManage 会回填ID并处理 extendMetadata

            if (rows > 0) {
                // 4. 插入成功后，重新查询完整的 DataManageVo 返回给前端
                DataManageVo resultVo = dataManageService.selectDataManageVoById(dataManage.getId()); // 使用回填的ID查询
                return success(resultVo);
            } else {
                return error("文件及管理信息保存失败，请稍后重试或联系管理员。");
            }

        } catch (Exception e) {
            return AjaxResult.error("文件及管理信息上传过程中发生错误，请联系管理员。");
        }
    }


    /**
     * 上传视频截图
     */
    @PreAuthorize("@ss.hasPermi('system:dataManage:add')")
    @Log(title = "视频截图上传", businessType = BusinessType.INSERT)
    @PostMapping("/uploadScreenshot")
    public AjaxResult uploadScreenshot(
            @RequestParam("file") MultipartFile file,
            @RequestParam("dataManageId") Long dataManageId,
            @RequestParam(value = "screenshotTime", required = false) String screenshotTimeStr) {

        Float screenshotTime = null;
        if (StringUtils.isNotEmpty(screenshotTimeStr)) {
            try {
                screenshotTime = Float.parseFloat(screenshotTimeStr);
            } catch (NumberFormatException e) {
                return AjaxResult.error("截图时间格式不正确，请传入有效的浮点数。");
            }
        }

        DataManageVo newScreenshotDataManage = dataManageService.uploadScreenshot(file, dataManageId, screenshotTime);

        if (newScreenshotDataManage != null && newScreenshotDataManage.getId() != null) {
            return success(newScreenshotDataManage);
        } else {
            return error("截图上传失败，请稍后重试或联系管理员。");
        }
    }
}
