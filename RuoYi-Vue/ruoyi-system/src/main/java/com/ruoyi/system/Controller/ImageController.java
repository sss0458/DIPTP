package com.ruoyi.system.Controller;

import com.ruoyi.system.service.PythonExecutionService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.ImageProcessResult;
import com.ruoyi.system.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图像处理API控制器
 * 提供RESTful接口供前端调用
 */
@RestController
@RequestMapping("/image")
public class ImageController extends BaseController {

    @Autowired
    private IImageService imageService;

    @Autowired
    private PythonExecutionService pythonExecutionService;

    /**
     * 执行Python脚本处理图像（上传脚本文件）
     * @param imageFile 图像文件（必须）
     * @param pythonFile Python脚本文件（必须）
     * @return 统一响应格式AjaxResult
     */
    @PostMapping("/execute-python-file")
    @PreAuthorize("@ss.hasPermi('example:API:python')")
    public AjaxResult executePythonWithFile(
            @RequestParam("file") MultipartFile imageFile,
            @RequestParam("script") MultipartFile pythonFile) {
        try {
            // 1. 基本参数校验
            if (imageFile.isEmpty()) {
                return AjaxResult.error("请上传图像文件");
            }
            if (pythonFile.isEmpty()) {
                return AjaxResult.error("请上传Python脚本文件");
            }

            // 2. 检查文件类型
            if (!pythonFile.getOriginalFilename().endsWith(".py")) {
                return AjaxResult.error("脚本文件必须是.py格式");
            }

            // 3. 调用服务执行脚本
            ImageProcessResult result = pythonExecutionService.executePythonScript(imageFile, pythonFile);

            // 4. 返回处理结果
            return AjaxResult.success("脚本执行成功", result);
        } catch (Exception e) {
            // 5. 错误处理
            return AjaxResult.error("脚本执行失败: " + e.getMessage());
        }
    }

    /**
     * 执行Python脚本处理图像（直接提供脚本内容）
     * @param imageFile 图像文件（必须）
     * @param script Python脚本内容（必须）
     * @return 统一响应格式AjaxResult
     */
    @PostMapping("/execute-python-script")
    @PreAuthorize("@ss.hasPermi('example:API:python')")
    public AjaxResult executePythonWithScript(
            @RequestParam("file") MultipartFile imageFile,
            @RequestParam("script") String script) {
        try {
            // 1. 基本参数校验
            if (imageFile.isEmpty()) {
                return AjaxResult.error("请上传图像文件");
            }
            if (script == null || script.trim().isEmpty()) {
                return AjaxResult.error("请输入Python脚本内容");
            }

            // 2. 调用服务执行脚本
            ImageProcessResult result = pythonExecutionService.executePythonScript(imageFile, script);

            // 3. 返回处理结果
            return AjaxResult.success("脚本执行成功", result);
        } catch (Exception e) {
            // 4. 错误处理
            return AjaxResult.error("脚本执行失败: " + e.getMessage());
        }
    }

    /**
     * 灰度化处理接口
     * @param file 上传的图片文件（表单字段名必须为"file"）
     * @return 统一响应格式AjaxResult
     */
    @PostMapping("/grayscale")
    @PreAuthorize("@ss.hasPermi('example:API:opcvGray')")
    public AjaxResult grayscale(@RequestParam("file") MultipartFile file) {
        ImageProcessResult result = imageService.grayscale(file);
        return success(result);
    }

    /**
     * Canny边缘检测接口
     * @param file 上传的图片文件
     * @param threshold1 低阈值（默认100）
     * @param threshold2 高阈值（默认200）
     * @return 统一响应格式AjaxResult
     */
    @PostMapping("/canny-edge")
    @PreAuthorize("@ss.hasPermi('example:API:opcvCanny')")
    public AjaxResult cannyEdge(
            @RequestParam("file") MultipartFile file,
            @RequestParam(defaultValue = "100") double threshold1,
            @RequestParam(defaultValue = "200") double threshold2) {
        ImageProcessResult result = imageService.cannyEdge(file, threshold1, threshold2);
        return success(result);
    }

    /**
     * 直方图均衡化接口
     * @param file 上传的图片文件
     * @return 统一响应格式AjaxResult
     */
    @PostMapping("/histogram-equalization")
    @PreAuthorize("@ss.hasPermi('example:API:opcvHistogram')")
    public AjaxResult histogramEqualization(@RequestParam("file") MultipartFile file) {
        ImageProcessResult result = imageService.histogramEqualization(file);
        return success(result);
    }

    /**
     * 高斯模糊接口
     * @param file 上传的图片文件
     * @param kernelSize 模糊核大小（必须为奇数，默认5）
     * @return 统一响应格式AjaxResult
     */
    @PostMapping("/gaussian-blur")
    @PreAuthorize("@ss.hasPermi('example:API:opcvGaussian')")
    public AjaxResult gaussianBlur(
            @RequestParam("file") MultipartFile file,
            @RequestParam(defaultValue = "5") int kernelSize) {
        ImageProcessResult result = imageService.gaussianBlur(file, kernelSize);
        return success(result);
    }

    /**
     * 图像旋转接口
     * @param file 上传的图片文件
     * @param angle 旋转角度（默认90度）
     * @return 统一响应格式AjaxResult
     */
    @PostMapping("/rotate")
    @PreAuthorize("@ss.hasPermi('example:API:opcvRotate')")
    public AjaxResult rotate(
            @RequestParam("file") MultipartFile file,
            @RequestParam(defaultValue = "90") double angle) {
        ImageProcessResult result = imageService.rotate(file, angle);
        return success(result);
    }


}