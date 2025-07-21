package org.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

//修改
@Service
public class ImageSimilarityService {
    private static final Logger log = LoggerFactory.getLogger(ImageSimilarityService.class);

    private static final int TARGET_SIZE = 256;
    private static final double MAX_PIXEL_VALUE = 255.0;
    private static final double PSNR_CUTOFF = 30.0;
    private static final double PSNR_MAX = 50.0;
    private static final double SSIM_C1 = Math.pow(0.01 * MAX_PIXEL_VALUE, 2);
    private static final double SSIM_C2 = Math.pow(0.03 * MAX_PIXEL_VALUE, 2);
    private static final int GAUSSIAN_WINDOW_SIZE = 11;
    private static final double GAUSSIAN_SIGMA = 1.5;

    /**
     * 计算图像相似度 (优先使用SSIM)
     */
    public double calculateSimilarity(String imgPath1, String imgPath2) {
        try {
            // 1. 检查文件存在性
            File file1 = new File(imgPath1);
            File file2 = new File(imgPath2);
            if (!file1.exists() || !file2.exists()) {
                throw new IOException("图像文件不存在: " +
                        (file1.exists() ? imgPath2 : imgPath1));
            }

            // 2. 读取图像
            BufferedImage img1 = readImage(file1);
            BufferedImage img2 = readImage(file2);

            // 3. 统一尺寸和灰度
            img1 = preprocessImage(img1);
            img2 = preprocessImage(img2);

            // 4. 计算相似度 (优先使用SSIM)
            return computeSSIM(img1, img2);
        } catch (Exception e) {
            log.error("图像相似度计算失败: {} vs {} - {}", imgPath1, imgPath2, e.getMessage());
            return 0.0;
        }
    }

    /**
     * 读取图像文件 (支持多种格式)
     */
    private BufferedImage readImage(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        if (image == null) {
            throw new IOException("不支持的图像格式: " + file.getName());
        }
        return image;
    }

    /**
     * 图像预处理 (调整大小并转为灰度)
     */
    private BufferedImage preprocessImage(BufferedImage original) {
        // 调整大小
        BufferedImage resized = new BufferedImage(
                TARGET_SIZE, TARGET_SIZE, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(original, 0, 0, TARGET_SIZE, TARGET_SIZE, null);
        g.dispose();
        return resized;
    }

    /**
     * 计算结构相似性指数 (SSIM)
     */
    private double computeSSIM(BufferedImage img1, BufferedImage img2) {
        int width = img1.getWidth();
        int height = img1.getHeight();

        // 获取像素数据
        byte[] pixels1 = ((DataBufferByte) img1.getRaster().getDataBuffer()).getData();
        byte[] pixels2 = ((DataBufferByte) img2.getRaster().getDataBuffer()).getData();

        double ssimSum = 0.0;
        int windowCount = 0;

        // 滑动窗口计算SSIM
        for (int y = 0; y <= height - GAUSSIAN_WINDOW_SIZE; y += GAUSSIAN_WINDOW_SIZE / 2) {
            for (int x = 0; x <= width - GAUSSIAN_WINDOW_SIZE; x += GAUSSIAN_WINDOW_SIZE / 2) {
                double windowSsim = computeWindowSSIM(pixels1, pixels2, width, x, y);
                if (!Double.isNaN(windowSsim)) {
                    ssimSum += windowSsim;
                    windowCount++;
                }
            }
        }

        return windowCount > 0 ? ssimSum / windowCount : 0.0;
    }

    /**
     * 计算单个窗口的SSIM
     */
    private double computeWindowSSIM(byte[] pixels1, byte[] pixels2,
                                     int width, int startX, int startY) {
        double sum1 = 0.0, sum2 = 0.0;
        double sumSq1 = 0.0, sumSq2 = 0.0, sum12 = 0.0;
        int pixelCount = GAUSSIAN_WINDOW_SIZE * GAUSSIAN_WINDOW_SIZE;

        // 计算窗口内像素的统计值
        for (int y = 0; y < GAUSSIAN_WINDOW_SIZE; y++) {
            for (int x = 0; x < GAUSSIAN_WINDOW_SIZE; x++) {
                int index = (startY + y) * width + (startX + x);
                int val1 = pixels1[index] & 0xFF;
                int val2 = pixels2[index] & 0xFF;

                sum1 += val1;
                sum2 += val2;
                sumSq1 += val1 * val1;
                sumSq2 += val2 * val2;
                sum12 += val1 * val2;
            }
        }

        // 计算平均值
        double mean1 = sum1 / pixelCount;
        double mean2 = sum2 / pixelCount;

        // 计算方差和协方差
        double var1 = (sumSq1 / pixelCount) - (mean1 * mean1);
        double var2 = (sumSq2 / pixelCount) - (mean2 * mean2);
        double cov = (sum12 / pixelCount) - (mean1 * mean2);

        // 计算SSIM
        double numerator = (2 * mean1 * mean2 + SSIM_C1) * (2 * cov + SSIM_C2);
        double denominator = (mean1 * mean1 + mean2 * mean2 + SSIM_C1) *
                (var1 + var2 + SSIM_C2);

        return numerator / denominator;
    }

    /**
     * 备用方案：计算峰值信噪比 (PSNR)
     */
    public double calculatePSNR(BufferedImage img1, BufferedImage img2) {
        double mse = calculateMSE(img1, img2);
        if (mse == 0) return 100; // 完美匹配
        return 10 * Math.log10((MAX_PIXEL_VALUE * MAX_PIXEL_VALUE) / mse);
    }

    /**
     * 计算均方误差 (MSE)
     */
    private double calculateMSE(BufferedImage img1, BufferedImage img2) {
        int width = img1.getWidth();
        int height = img1.getHeight();
        long sum = 0;

        // 直接操作像素数据提高性能
        byte[] pixels1 = ((DataBufferByte) img1.getRaster().getDataBuffer()).getData();
        byte[] pixels2 = ((DataBufferByte) img2.getRaster().getDataBuffer()).getData();

        for (int i = 0; i < pixels1.length; i++) {
            int diff = (pixels1[i] & 0xFF) - (pixels2[i] & 0xFF);
            sum += diff * diff;
        }

        return (double) sum / (width * height);
    }

    /**
     * 将PSNR转换为相似度百分比
     */
    private double normalizePSNR(double psnr) {
        if (psnr >= PSNR_MAX) return 1.0;
        if (psnr <= PSNR_CUTOFF) return 0.0;
        return (psnr - PSNR_CUTOFF) / (PSNR_MAX - PSNR_CUTOFF);
    }
}