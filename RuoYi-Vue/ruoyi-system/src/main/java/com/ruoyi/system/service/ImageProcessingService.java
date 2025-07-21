package com.ruoyi.system.service;

import com.ruoyi.system.domain.ImageProcessResult;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/**
 * 图像处理服务接口
 * 定义ImageJ处理操作契约
 */
public interface ImageProcessingService {

    /**
     * 边缘检测处理
     * @param file 上传的图像文件
     * @return 处理结果
     * @throws IOException 文件读取异常
     */
    ImageProcessResult edgeDetection(MultipartFile file) throws IOException;

    /**
     * 图像二值化
     * @param file 上传的图像文件
     * @param threshold 阈值(0-255)
     * @return 处理结果
     * @throws IOException 文件读取异常
     */
    ImageProcessResult threshold(MultipartFile file, int threshold) throws IOException;

    /**
     * 高斯模糊处理
     * @param file 上传的图像文件
     * @param sigma 高斯核标准差
     * @return 处理结果
     * @throws IOException 文件读取异常
     */
    ImageProcessResult gaussianBlur(MultipartFile file, double sigma) throws IOException;
}