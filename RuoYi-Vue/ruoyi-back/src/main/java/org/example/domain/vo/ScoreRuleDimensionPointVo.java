package org.example.domain.vo;

import com.ruoyi.common.annotation.Excel;
import org.example.domain.ScoreRuleDimensionPoint;

public class ScoreRuleDimensionPointVo extends ScoreRuleDimensionPoint {
    @Excel(name = "规则名称")
    private String rule_name;

    @Excel(name = "纬度")
    private String dimension;

    public String getRule_name() {
        return rule_name;
    }

    public void setRule_name(String rule_name) {
        this.rule_name = rule_name;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }
}
