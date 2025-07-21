package org.example.utils;

import org.example.mapper.StudentWorkMapper;
import org.example.mapper.TeachTaskMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

//修改
@Service
public class TaskExpirationService {

    private final TeachTaskMapper teachTaskMapper;
    private final StudentWorkMapper studentWorkMapper;

    public TaskExpirationService(TeachTaskMapper teachTaskMapper,
                                 StudentWorkMapper studentWorkMapper) {
        this.teachTaskMapper = teachTaskMapper;
        this.studentWorkMapper = studentWorkMapper;
    }

    // 每天凌晨1点执行
    @Scheduled(cron = "0 0 1 * * ?")
    @Transactional
    public void processExpiredTasks() {
        Date now = new Date();

        // 1. 标记过期任务为已删除
        teachTaskMapper.markExpiredTasksAsDeleted(now);

        // 2. 标记待提交的作业为已删除
        studentWorkMapper.markExpiredPendingWorksAsDeleted(now);
    }
}
