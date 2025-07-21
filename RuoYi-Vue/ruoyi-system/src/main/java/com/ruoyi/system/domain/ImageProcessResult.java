// ImageProcessResult.java
package com.ruoyi.system.domain;

import java.util.Map;

public class ImageProcessResult {
    private String base64Image;  // Base64编码的处理后图片
    private Map<String, Object> metadata;  // 元数据（如算法参数、耗时）

    // Getters and Setters
    public String getBase64Image() {
        return base64Image;
    }
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}