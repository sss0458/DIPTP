package org.example.domain.vo;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import org.example.domain.StudentWorkScore;

public class StudentWorkScoreVo extends StudentWorkScore {

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskTitle;

    /** 实验标题 */
    @Excel(name = "实验标题")
    private String testTitle;

    /** 规则名称 */
    @Excel(name = "规则名称")
    private String ruleName;

    /** 纬度 */
    @Excel(name = "纬度")
    private String dimension;


    /** 教师账号 */
    @Excel(name = "教师登录名称")
    private String userName;

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "StudentWorkScoreVo{" +
                "taskTitle='" + taskTitle + '\'' +
                ", testTitle='" + testTitle + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", dimension='" + dimension + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}