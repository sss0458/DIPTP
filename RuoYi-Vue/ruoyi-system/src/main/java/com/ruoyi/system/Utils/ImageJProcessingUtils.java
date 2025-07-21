package com.ruoyi.system.Utils;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import com.ruoyi.system.domain.ImageProcessResult;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * ImageJ图像处理工具类
 */
public class ImageJProcessingUtils {

    /**
     * 边缘检测处理
     * @param inputImage 输入图像(BufferedImage格式)
     * @return 包含Base64结果和元数据的对象
     */
    public static ImageProcessResult detectEdges(BufferedImage inputImage) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("operation", "edge-detection");

        try {
            ImagePlus imagePlus = new ImagePlus("Edge Detection", inputImage);
            IJ.run(imagePlus, "Find Edges", "");
            return buildResult(imagePlus, startTime, metadata);
        } catch (Exception e) {
            throw new RuntimeException("边缘检测失败", e);
        }
    }

    /**
     * 图像二值化处理
     * @param inputImage 输入图像
     * @param threshold 阈值(0-255)
     * @return 处理结果对象
     */
    public static ImageProcessResult applyThreshold(BufferedImage inputImage, int threshold) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("operation", "threshold");
        metadata.put("threshold", threshold);

        try {
            ImagePlus imagePlus = new ImagePlus("Threshold", inputImage);
            ImageProcessor processor = imagePlus.getProcessor();
            processor.setThreshold(threshold, 255, ImageProcessor.NO_LUT_UPDATE);
            processor.threshold(255);
            return buildResult(imagePlus, startTime, metadata);
        } catch (Exception e) {
            throw new RuntimeException("二值化处理失败", e);
        }
    }

    /**
     * 高斯模糊处理
     * @param inputImage 输入图像
     * @param sigma 高斯核标准差
     * @return 处理结果对象
     */
    public static ImageProcessResult applyGaussianBlur(BufferedImage inputImage, double sigma) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("operation", "gaussian-blur");
        metadata.put("sigma", sigma);

        try {
            ImagePlus imagePlus = new ImagePlus("Gaussian Blur", inputImage);
            IJ.run(imagePlus, "Gaussian Blur...", "sigma=" + sigma);
            return buildResult(imagePlus, startTime, metadata);
        } catch (Exception e) {
            throw new RuntimeException("高斯模糊处理失败", e);
        }
    }

    /**
     * 构建标准化返回结果 (与OpenCVUtils风格一致)
     * @param imagePlus 处理后的图像对象
     * @param startTime 处理开始时间戳
     * @param params 算法参数元数据
     * @return 标准化结果对象
     */
    private static ImageProcessResult buildResult(ImagePlus imagePlus, long startTime, Map<String, Object> params) {
        ImageProcessResult result = new ImageProcessResult();

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // 将图像转换为Base64编码
            javax.imageio.ImageIO.write(imagePlus.getBufferedImage(), "png", baos);
            String base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());
            result.setBase64Image(base64Image);

            // 设置元数据
            Map<String, Object> metadata = new HashMap<>(params);
            metadata.put("processor", "ImageJ");
            metadata.put("timeCost", (System.currentTimeMillis() - startTime) + "ms");
            result.setMetadata(metadata);

            return result;
        } catch (Exception e) {
            throw new RuntimeException("图像编码失败", e);
        }
    }
}