package com.ruoyi.system.Utils;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import com.ruoyi.system.domain.ImageProcessResult;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * OpenCV图像处理工具类
 * 封装常用图像处理算法，兼容Java 8
 */
public class OpenCVUtils {


    /**
     * 图像灰度化处理
     * @param src 输入图像矩阵（BGR格式）
     * @return 包含Base64结果和元数据的对象
     */
    public static ImageProcessResult toGrayScale(Mat src) {
        long startTime = System.currentTimeMillis();
        Mat dst = new Mat();

        // 调用OpenCV的BGR转灰度方法
        Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGR2GRAY);

        // 构建元数据
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("operation", "grayscale");

        return buildResult(dst, startTime, metadata);
    }

    /**
     * Canny边缘检测
     * @param src 输入图像矩阵
     * @param threshold1 低阈值
     * @param threshold2 高阈值
     * @return 处理结果对象
     */
    public static ImageProcessResult cannyEdge(Mat src, double threshold1, double threshold2) {
        long startTime = System.currentTimeMillis();
        Mat edges = new Mat();

        // 调用Canny算法
        Imgproc.Canny(src, edges, threshold1, threshold2);

        // 构建元数据
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("operation", "canny-edge");
        metadata.put("threshold1", threshold1);
        metadata.put("threshold2", threshold2);

        return buildResult(edges, startTime, metadata);
    }

    /**
     * 直方图均衡化（增强对比度）
     * @param src 输入图像矩阵
     * @return 处理结果对象
     */
    public static ImageProcessResult histogramEqualization(Mat src) {
        long startTime = System.currentTimeMillis();
        Mat gray = new Mat();
        Mat dst = new Mat();

        // 先转为灰度图再均衡化
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(gray, dst);

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("operation", "histogram-equalization");

        return buildResult(dst, startTime, metadata);
    }

    /**
     * 高斯模糊（降噪）
     * @param src 输入图像矩阵
     * @param kernelSize 模糊核大小（必须为奇数）
     * @return 处理结果对象
     */
    public static ImageProcessResult gaussianBlur(Mat src, int kernelSize) {
        long startTime = System.currentTimeMillis();
        Mat dst = new Mat();

        // 高斯模糊处理
        Imgproc.GaussianBlur(src, dst, new Size(kernelSize, kernelSize), 0);

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("operation", "gaussian-blur");
        metadata.put("kernelSize", kernelSize);

        return buildResult(dst, startTime, metadata);
    }

    /**
     * 图像旋转
     * @param src 输入图像矩阵
     * @param angle 旋转角度（顺时针）
     * @return 处理结果对象
     */
    public static ImageProcessResult rotate(Mat src, double angle) {
        long startTime = System.currentTimeMillis();
        Mat dst = new Mat();

        // 计算旋转中心和变换矩阵
        Point center = new Point(src.width() / 2.0, src.height() / 2.0);
        Mat rotationMatrix = Imgproc.getRotationMatrix2D(center, angle, 1.0);

        // 执行仿射变换
        Imgproc.warpAffine(src, dst, rotationMatrix, src.size());

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("operation", "rotate");
        metadata.put("angle", angle);

        return buildResult(dst, startTime, metadata);
    }

    /**
     * 图像二值化
     * @param src 输入图像矩阵
     * @param threshold 阈值
     * @param maxVal 最大值
     * @return 处理结果对象
     */
    public static ImageProcessResult threshold(Mat src, double threshold, double maxVal) {
        long startTime = System.currentTimeMillis();
        Mat dst = new Mat();

        // 阈值处理
        Imgproc.threshold(src, dst, threshold, maxVal, Imgproc.THRESH_BINARY);

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("operation", "threshold");
        metadata.put("threshold", threshold);
        metadata.put("maxVal", maxVal);

        return buildResult(dst, startTime, metadata);
    }

    /**
     * 图像缩放
     * @param src 输入图像矩阵
     * @param width 目标宽度
     * @param height 目标高度
     * @return 处理结果对象
     */
    public static ImageProcessResult resize(Mat src, int width, int height) {
        long startTime = System.currentTimeMillis();
        Mat dst = new Mat();

        // 图像缩放
        Imgproc.resize(src, dst, new Size(width, height));

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("operation", "resize");
        metadata.put("width", width);
        metadata.put("height", height);

        return buildResult(dst, startTime, metadata);
    }

    /**
     * 构建标准化返回结果
     * @param mat 处理后的图像矩阵
     * @param startTime 处理开始时间戳
     * @param params 算法参数元数据
     * @return 标准化结果对象
     */
    private static ImageProcessResult buildResult(Mat mat, long startTime, Map<String, Object> params) {
        ImageProcessResult result = new ImageProcessResult();

        // 使用MatOfByte作为输出容器
        MatOfByte matOfByte = new MatOfByte();
        if (Imgcodecs.imencode(".jpg", mat, matOfByte)) {
            // 将MatOfByte转换为byte[]
            byte[] byteArray = matOfByte.toArray();

            // Base64编码
            String base64Image = Base64.getEncoder().encodeToString(byteArray);
            result.setBase64Image(base64Image);

            // 设置元数据
            Map<String, Object> metadata = new HashMap<>(params);
            metadata.put("timeCost", (System.currentTimeMillis() - startTime) + "ms");
            metadata.put("processor","Opencv");
            result.setMetadata(metadata);
        } else {
            throw new RuntimeException("图像编码失败");
        }

        return result;
    }
}