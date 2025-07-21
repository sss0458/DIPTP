package com.ruoyi.system.service;

import com.ruoyi.system.domain.ImageProcessResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Python脚本执行服务
 */
public interface PythonExecutionService {

    /**
     * 执行Python图像处理脚本
     * @param imageFile 图像文件
     * @param pythonScript Python脚本内容
     * @return 处理结果
     */
    ImageProcessResult executePythonScript(MultipartFile imageFile, String pythonScript);

    /**
     * 执行Python图像处理脚本文件
     * @param imageFile 图像文件
     * @param pythonFile Python脚本文件
     * @return 处理结果
     */
    ImageProcessResult executePythonScript(MultipartFile imageFile, MultipartFile pythonFile);
}