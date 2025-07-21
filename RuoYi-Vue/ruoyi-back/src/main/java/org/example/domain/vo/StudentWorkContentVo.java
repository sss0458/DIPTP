package org.example.domain.vo;

import java.util.List;
import org.example.domain.StudentWorkContentStep;

/**
 * 学生作业内容视图对象（包含Base64图片和步骤列表）
 */
public class StudentWorkContentVo {
    private Long id;
    private Long taskId;
    private Long taskTestId;
    private Long workId;
    private Long version;
    private String code;
    private String WorkPath;

    public String getWorkPath() {
        return WorkPath;
    }

    public void setWorkPath(String workPath) {
        WorkPath = workPath;
    }

    private String imageBase64; // Base64格式的图片
    private String result;
    private String mainBody;
    private String summary;
    private String status;
    private List<StudentWorkContentStep> steps; // 步骤列表

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getTaskTestId() {
        return taskTestId;
    }

    public void setTaskTestId(Long taskTestId) {
        this.taskTestId = taskTestId;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMainBody() {
        return mainBody;
    }

    public void setMainBody(String mainBody) {
        this.mainBody = mainBody;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<StudentWorkContentStep> getSteps() {
        return steps;
    }

    public void setSteps(List<StudentWorkContentStep> steps) {
        this.steps = steps;
    }
}