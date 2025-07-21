package org.example.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.BaseEntity;
import org.example.domain.StudentWork;

/**
 * 学生作业视图对象（包含作业内容和Base64图片）
 */
public class StudentWorkVo extends BaseEntity {
    private Long id;
    private Long taskId;
    private String taskTitle;
    private Long taskTestId;
    private String testTestTitle;
    private Long dataManageId;
    private Long studentId;
    private Long teacherId;
    private Long quantity;
    private Date lastDate;

    private String studentName;

    private String teacherName;

    private String delFlag;

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    //0为未提交 1为提交
    private String status;

    //0为未批改 1为已批改
    private String criticizeStatus;
    private BigDecimal score;
    private List<StudentWorkContentVo> contents; // 作业内容列表

    public StudentWorkVo(){

    };
    // 从StudentWork转换的构造方法
    public StudentWorkVo(StudentWork studentWork) {
        this.id = studentWork.getId();
        this.taskId = studentWork.getTaskId();
        this.taskTestId = studentWork.getTaskTestId();
        this.dataManageId = studentWork.getDataManageId();
        this.studentId = studentWork.getStudentId();
        this.teacherId = studentWork.getTeacherId();
        this.quantity = studentWork.getQuantity();
        this.lastDate = studentWork.getLastDate();
        this.status = studentWork.getStatus();
        this.criticizeStatus = studentWork.getCriticizeStatus();
        this.score = studentWork.getScore();
    }

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

    public Long getDataManageId() {
        return dataManageId;
    }

    public void setDataManageId(Long dataManageId) {
        this.dataManageId = dataManageId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCriticizeStatus() {
        return criticizeStatus;
    }

    public void setCriticizeStatus(String criticizeStatus) {
        this.criticizeStatus = criticizeStatus;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public List<StudentWorkContentVo> getContents() {
        return contents;
    }

    public void setContents(List<StudentWorkContentVo> contents) {
        this.contents = contents;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTestTestTitle() {
        return testTestTitle;
    }

    public void setTestTestTitle(String testTestTitle) {
        this.testTestTitle = testTestTitle;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}