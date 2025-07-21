package org.example.utils;

import org.example.domain.*;
import org.example.mapper.*;
import org.example.mapper.ExampleMapper;
import org.example.mapper.StudentWorkMapper;
import org.example.mapper.StudentWorkScoreMapper;
import org.example.mapper.TeachTaskTestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;


//修改
@Service
public class AutomatedScoringService {
    private static final Logger log = LoggerFactory.getLogger(AutomatedScoringService.class);

    @Autowired
    private ImageSimilarityService imageSimilarityService;
    @Autowired
    private StudentWorkScoreMapper scoreMapper;
    @Autowired
    private ExampleMapper exampleMapper;
    @Autowired
    private TeachTaskTestMapper taskTestMapper;
    @Autowired
    private StudentWorkMapper studentWorkMapper;

    /**
     * 异步执行自动评分
     */
    @Async("scoringTaskExecutor")
    @Transactional
    public CompletableFuture<Void> scoreStudentWork(StudentWorkContent workContent) {
        try {
            // 1. 获取学生作业信息
            StudentWork studentWork = studentWorkMapper.selectStudentWorkById(workContent.getWorkId());
            if (studentWork == null) {
                log.error("找不到学生作业: {}", workContent.getWorkId());
                saveErrorScore(workContent, "找不到作业信息");
                return CompletableFuture.completedFuture(null);
            }

            // 2. 获取任务实验信息
            TeachTaskTest taskTest = taskTestMapper.selectTeachTaskTestById(studentWork.getTaskTestId());
            if (taskTest == null) {
                log.error("找不到教学任务实验: {}", studentWork.getTaskTestId());
                saveErrorScore(workContent, "找不到实验信息");
                return CompletableFuture.completedFuture(null);
            }

            // 3. 获取标准答案
            Example example = exampleMapper.selectExampleById(taskTest.getCaseId());
            if (example == null) {
                log.error("找不到案例: {}", taskTest.getCaseId());
                saveErrorScore(workContent, "找不到标准答案");
                return CompletableFuture.completedFuture(null);
            }

            // 4. 验证文件路径
            if (example.getCasePath() == null || example.getCasePath().isEmpty()) {
                log.error("标准答案路径为空");
                saveErrorScore(workContent, "标准答案路径无效");
                return CompletableFuture.completedFuture(null);
            }

            if (workContent.getWorkPath() == null || workContent.getWorkPath().isEmpty()) {
                log.error("学生作业路径为空");
                saveErrorScore(workContent, "学生作业路径无效");
                return CompletableFuture.completedFuture(null);
            }

            // 5. 计算相似度
            double similarity = imageSimilarityService.calculateSimilarity(
                    example.getCasePath(),
                    workContent.getWorkPath()
            );

            // 6. 计算得分 (相似度>95%得满分)
            double score = Math.min(100, similarity * 100);
            String comment = String.format("自动评分结果：相似度 %.2f%%", similarity * 100);

            // 7. 保存评分结果
            saveScore(workContent, score, comment, "2");

            // 8. 更新作业状态
            updateWorkStatus(studentWork, score);

            log.info("作业评分完成: workId={}, score={}", workContent.getWorkId(), score);
        } catch (Exception e) {
            log.error("自动评分失败: workId={}, {}", workContent.getWorkId(), e.getMessage(), e);
            saveErrorScore(workContent, "自动评分失败: " + e.getMessage());
        }
        return CompletableFuture.completedFuture(null);
    }

    /**
     * 保存评分结果
     */
    private void saveScore(StudentWorkContent workContent, double score,
                           String comment, String status) {
        StudentWorkScore scoreRecord = new StudentWorkScore();
        scoreRecord.setWorkId(workContent.getWorkId());
        scoreRecord.setWorkContentId(workContent.getId());
        scoreRecord.setScore(BigDecimal.valueOf(score));
        scoreRecord.setComment(comment);
        scoreRecord.setStatus(status);
        scoreRecord.setTeacherId(0L); // 0表示系统自动评分
        scoreMapper.insertStudentWorkScore(scoreRecord);
    }

    /**
     * 保存错误评分
     */
    private void saveErrorScore(StudentWorkContent workContent, String errorMsg) {
        saveScore(workContent, 0.0, errorMsg, "error");
    }

    /**
     * 更新作业状态
     */
    private void updateWorkStatus(StudentWork studentWork, double score) {
        studentWork.setScore(BigDecimal.valueOf(score));
        studentWork.setCriticizeStatus("2"); // 已批改
        studentWork.setStatus("1"); // 已完成
        studentWorkMapper.updateStudentWork(studentWork);
    }
}