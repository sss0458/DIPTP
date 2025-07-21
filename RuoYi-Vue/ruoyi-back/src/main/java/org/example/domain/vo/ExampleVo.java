package org.example.domain.vo;

import com.ruoyi.common.annotation.Excel;
import org.example.domain.Example;

public class ExampleVo extends Example {
    /** 规则名称 */
    @Excel(name = "规则名称")
    private String rule_name;

    @Excel(name = "base64新图片")
    private String imageBase64; // Base64格式的图片

    public String getRule_name() {
        return rule_name;
    }

    public void setRule_name(String rule_name) {
        this.rule_name = rule_name;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
