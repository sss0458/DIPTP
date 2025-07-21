package org.example.domain.vo;

import com.ruoyi.common.annotation.Excel;
import org.example.domain.ScoreRuleDimension;

public class ScoreRuleDimensionVo extends ScoreRuleDimension {
    @Excel(name = "规则名称")
    private String rule_name;

    public String getRule_name() {
        return rule_name;
    }

    public void setRule_name(String rule_name) {
        this.rule_name = rule_name;
    }
}
