package com.ruoyi.system.Controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.ImageProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 图像处理API控制器
 * 提供RESTful接口
 */
@RestController
@RequestMapping("/imageJ")
public class ImageProcessingController extends BaseController {

    @Autowired
    private ImageProcessingService imageProcessingService;

    /**
     * 边缘检测接口
     * @param file 上传的图像文件(表单字段名必须为"file")
     * @return 统一响应格式
     */
    @PreAuthorize("@ss.hasPermi('example:API:ImagejEdge')")
    @PostMapping("/edge-detection")
    public AjaxResult edgeDetection(@RequestParam("file") MultipartFile file) {
        try {
            return success(imageProcessingService.edgeDetection(file));
        } catch (IOException e) {
            return error("边缘检测失败: " + e.getMessage());
        }
    }

    /**
     * 二值化接口
     * @param file 上传的图像文件
     * @param threshold 阈值(默认128)
     * @return 统一响应格式
     */
    @PreAuthorize("@ss.hasPermi('example:API:ImagejThreshold')")
    @PostMapping("/threshold")
    public AjaxResult threshold(
            @RequestParam("file") MultipartFile file,
            @RequestParam(defaultValue = "128") int threshold) {
        try {
            return success(imageProcessingService.threshold(file, threshold));
        } catch (IOException e) {
            return error("二值化失败: " + e.getMessage());
        }
    }

    /**
     * 高斯模糊接口
     * @param file 上传的图像文件
     * @param sigma 高斯核大小(默认2.0)
     * @return 统一响应格式
     */
    @PreAuthorize("@ss.hasPermi('example:API:ImagejGaussian')")
    @PostMapping("/gaussian-blur")
    public AjaxResult gaussianBlur(
            @RequestParam("file") MultipartFile file,
            @RequestParam(defaultValue = "2.0") double sigma) {
        try {
            return success(imageProcessingService.gaussianBlur(file, sigma));
        } catch (IOException e) {
            return error("高斯模糊失败: " + e.getMessage());
        }
    }
}