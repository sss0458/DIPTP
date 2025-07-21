package com.ruoyi.system.service;

import com.ruoyi.system.domain.ImageProcessResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图像处理服务接口
 * 定义所有图像处理操作的契约
 */
public interface IImageService {

    /**
     * 灰度化处理
     * @param file 上传的图片文件
     * @return 处理结果（包含Base64图像和元数据）
     */
    ImageProcessResult grayscale(MultipartFile file);

    /**
     * Canny边缘检测
     * @param file 上传的图片文件
     * @param threshold1 低阈值
     * @param threshold2 高阈值
     * @return 处理结果
     */
    ImageProcessResult cannyEdge(MultipartFile file, double threshold1, double threshold2);

    /**
     * 直方图均衡化
     * @param file 上传的图片文件
     * @return 处理结果
     */
    ImageProcessResult histogramEqualization(MultipartFile file);

    /**
     * 高斯模糊
     * @param file 上传的图片文件
     * @param kernelSize 模糊核大小（必须为奇数）
     * @return 处理结果
     */
    ImageProcessResult gaussianBlur(MultipartFile file, int kernelSize);

    /**
     * 图像旋转
     * @param file 上传的图片文件
     * @param angle 旋转角度（顺时针）
     * @return 处理结果
     */
    ImageProcessResult rotate(MultipartFile file, double angle);
}