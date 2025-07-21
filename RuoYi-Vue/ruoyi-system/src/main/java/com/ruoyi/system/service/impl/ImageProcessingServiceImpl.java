// ImageProcessingServiceImpl.java
package com.ruoyi.system.service.impl;

import com.ruoyi.system.Utils.ImageJProcessingUtils;
import com.ruoyi.system.domain.ImageProcessResult;
import com.ruoyi.system.service.ImageProcessingService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * ImageJ图像处理服务实现
 */
@Service
public class ImageProcessingServiceImpl implements ImageProcessingService {

    @Override
    public ImageProcessResult edgeDetection(MultipartFile file) throws IOException {
        // 将MultipartFile转换为BufferedImage
        BufferedImage image = ImageIO.read(file.getInputStream());
        // 调用工具类处理
        return ImageJProcessingUtils.detectEdges(image);
    }

    @Override
    public ImageProcessResult threshold(MultipartFile file, int threshold) throws IOException {
        BufferedImage image = ImageIO.read(file.getInputStream());
        return ImageJProcessingUtils.applyThreshold(image, threshold);
    }

    @Override
    public ImageProcessResult gaussianBlur(MultipartFile file, double sigma) throws IOException {
        BufferedImage image = ImageIO.read(file.getInputStream());
        return ImageJProcessingUtils.applyGaussianBlur(image, sigma);
    }
}