package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.ImageProcessResult;
import com.ruoyi.system.service.IImageService;
import com.ruoyi.system.Utils.OpenCVUtils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/**
 * 图像处理服务实现类
 * 调用OpenCV工具类完成具体算法处理
 */
@Service
public class ImageServiceImpl implements IImageService {

    OpenCVUtils utils = new OpenCVUtils();

    @Override
    public ImageProcessResult grayscale(MultipartFile file) {
        Mat src = readMatFromFile(file);
        return utils.toGrayScale(src);
    }

    @Override
    public ImageProcessResult cannyEdge(MultipartFile file, double threshold1, double threshold2) {
        Mat src = readMatFromFile(file);
        return utils.cannyEdge(src, threshold1, threshold2);
    }

    @Override
    public ImageProcessResult histogramEqualization(MultipartFile file) {
        Mat src = readMatFromFile(file);
        return utils.histogramEqualization(src);
    }

    @Override
    public ImageProcessResult gaussianBlur(MultipartFile file, int kernelSize) {
        Mat src = readMatFromFile(file);
        return utils.gaussianBlur(src, kernelSize);
    }

    @Override
    public ImageProcessResult rotate(MultipartFile file, double angle) {
        Mat src = readMatFromFile(file);
        return utils.rotate(src, angle);
    }

    /**
     * 将MultipartFile转换为OpenCV的Mat对象
     * @param file 上传的文件
     * @return Mat格式的图像数据
     * @throws RuntimeException 如果文件读取失败
     */
    private Mat readMatFromFile(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            return Imgcodecs.imdecode(new MatOfByte(bytes), Imgcodecs.IMREAD_COLOR);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read image file", e);
        }
    }
}