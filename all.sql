/*
 <<数字图像处理>> 数据库分析
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `nick_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户昵称',
  `role_id` bigint NOT NULL DEFAULT '0' COMMENT '用户类型',
  `avatar` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `pwd_update_date` datetime NULL DEFAULT NULL COMMENT '密码最后更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `student_id` bigint NOT NULL AUTO_INCREMENT COMMENT '学生ID',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户ID',
  `name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '名称',
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '性别（0男 1女 2未知）',
  `address` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '地址',
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '学生信息表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `teacher_id` bigint NOT NULL AUTO_INCREMENT COMMENT '教师ID',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户ID',
  `name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '名称',
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '性别（0男 1女 2未知）',
  `address` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '地址',
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '教师信息表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for algorithm
-- ----------------------------
DROP TABLE IF EXISTS `algorithm`;
CREATE TABLE `algorithm`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '算法' ROW_FORMAT = Dynamic;



-- ----------------------------
-- Table structure for data_manage
-- ----------------------------


DROP TABLE IF EXISTS `data_manage`;
CREATE TABLE `data_manage`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `status` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态:启用/停用',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `sign` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据标识',
  `source` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据来源',
  `data_type` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据类型',
  `scene_type` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '场景类型',
  `target_type` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标类型',
  `imaging_type` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '成像条件',
  `algorithm_id` bigint(0) NULL DEFAULT NULL COMMENT '算法',
  `file_path` varchar(2550) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 158 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for data_manage_extend
-- ----------------------------

DROP TABLE IF EXISTS `data_manage_extend`;
CREATE TABLE `data_manage_extend`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `data_manage_id` bigint(0) NULL DEFAULT NULL COMMENT '数据管理id',
  `meta_data_id` bigint(0) NULL DEFAULT NULL COMMENT '元数据id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '拓展内容',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `status` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态:启用/停用',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1307 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据管理拓展' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for example
-- ----------------------------
DROP TABLE IF EXISTS `example`;
CREATE TABLE `example`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务标题',
  `task_describe` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '任务描述',
  `test_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '实验标题',
  `data_type` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '实验类型',
  `test_describe` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '实验描述/目的及要求',
  `theory` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '实验原理',
  `rule_id` bigint(0) NULL DEFAULT NULL COMMENT '评分规则id',
  `algorithm_id` bigint(0) NULL DEFAULT NULL COMMENT '算法id',
  `data_manage_id` bigint(0) NULL DEFAULT NULL COMMENT '数据管理id/图片',
  `case_path` varchar(2550) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件地址/新图片',
  `code` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '代码',
  `result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '实验结果与分析',
  `main_body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '实验主体',
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '思考与总结',
  `step` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '步骤内容',
  `grade` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '等级',
  `work_id` bigint(0) NULL DEFAULT NULL COMMENT '作业id',
  `status` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '案例库表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for example_step
-- ----------------------------

DROP TABLE IF EXISTS `example_step`;
CREATE TABLE `example_step`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `example_id` bigint(0) NULL DEFAULT NULL COMMENT '案例id',
  `sort` int(0) NULL DEFAULT NULL COMMENT '序号',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `status` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '案例库步骤' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure formeta_data
-- ----------------------------
DROP TABLE IF EXISTS `meta_data`;
CREATE TABLE `meta_data`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据名称',
  `content_length` bigint(0) NULL DEFAULT NULL COMMENT '内容长度',
  `is_required` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否必填',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `status` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态:启用/停用',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '元数据管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for score_rule
-- ----------------------------
DROP TABLE IF EXISTS `score_rule`;
CREATE TABLE `score_rule`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则名称',
  `describe` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '规则描述',
  `status` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评分规则' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for score_rule_dimension
-- ----------------------------
DROP TABLE IF EXISTS `score_rule_dimension`;
CREATE TABLE `score_rule_dimension`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rule_id` bigint(0) NULL DEFAULT NULL COMMENT '评分规则id',
  `dimension` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `scale` int(0) NULL DEFAULT NULL COMMENT '权重',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `status` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 181 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评分规则维度' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for score_rule_dimension_point
-- ----------------------------
DROP TABLE IF EXISTS `score_rule_dimension_point`;
CREATE TABLE `score_rule_dimension_point`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rule_id` bigint(0) NULL DEFAULT NULL COMMENT '评分规则id',
  `dimension_id` bigint(0) NULL DEFAULT NULL COMMENT '纬度id',
  `score_point` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评分要点',
  `scale` int(0) NULL DEFAULT NULL COMMENT '权重',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `status` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 270 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评分规则维度要点' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student_work
-- ----------------------------
DROP TABLE IF EXISTS `student_work`;
CREATE TABLE `student_work`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_id` bigint(0) NULL DEFAULT NULL COMMENT '任务id',
  `task_test_id` bigint(0) NULL DEFAULT NULL COMMENT '实验id',
  `data_manage_id` bigint(0) NULL DEFAULT NULL COMMENT '数据管理id',
  `student_id` bigint(0) NULL DEFAULT NULL COMMENT '学生id',
  `teacher_id` bigint(0) NULL DEFAULT NULL COMMENT '老师id',
  `quantity` int(0) NULL DEFAULT NULL COMMENT '提交次数',
  `last_date` datetime(0) NULL DEFAULT NULL COMMENT '最近保存时间',
  `status` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '作业状态',
  `criticize_status` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '批改状态',
  `score` double(5, 2) NULL DEFAULT 0.00 COMMENT '得分',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1524 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学生作业' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student_work_content
-- ----------------------------
DROP TABLE IF EXISTS `student_work_content`;
CREATE TABLE `student_work_content`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_id` bigint(0) NULL DEFAULT NULL COMMENT '任务id',
  `task_test_id` bigint(0) NULL DEFAULT NULL COMMENT '实验id',
  `work_id` bigint(0) NULL DEFAULT NULL COMMENT '作业id',
  `version` int(0) NULL DEFAULT NULL COMMENT '版本号',
  `code` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '代码',
  `work_path` varchar(2550) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件地址/新图片',
  `result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '实验结果与分析',
  `main_body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '实验主体',
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '思考与总结',
  `last_date` datetime(0) NULL DEFAULT NULL COMMENT '保存时间',
  `status` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `teacher_id` bigint(0) NULL DEFAULT NULL COMMENT '老师id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学生作业内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student_work_content_step
-- ----------------------------
DROP TABLE IF EXISTS `student_work_content_step`;
CREATE TABLE `student_work_content_step`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_id` bigint(0) NULL DEFAULT NULL COMMENT '任务id',
  `task_test_id` bigint(0) NULL DEFAULT NULL COMMENT '实验id',
  `work_id` bigint(0) NULL DEFAULT NULL COMMENT '作业id',
  `work_content_id` bigint(0) NULL DEFAULT NULL COMMENT '作业内容id',
  `sort` int(0) NULL DEFAULT NULL COMMENT '序号',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `status` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `teacher_id` bigint(0) NULL DEFAULT NULL COMMENT '老师id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学生作业内容步骤' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student_work_score
-- ----------------------------
DROP TABLE IF EXISTS `student_work_score`;
CREATE TABLE `student_work_score`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_id` bigint(0) NULL DEFAULT NULL COMMENT '任务id',
  `task_test_id` bigint(0) NULL DEFAULT NULL COMMENT '实验id',
  `work_id` bigint(0) NULL DEFAULT NULL COMMENT '作业id',
  `work_content_id` bigint(0) NULL DEFAULT NULL COMMENT '作业内容id',
  `rule_id` bigint(0) NULL DEFAULT NULL COMMENT '评分规则id',
  `dimension_id` bigint(0) NULL DEFAULT 0 COMMENT '评分规则维度id',
  `score` double(5, 2) NULL DEFAULT 0.00 COMMENT '得分',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '批语',
  `status` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '作业状态',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `teacher_id` bigint(0) NULL DEFAULT NULL COMMENT '老师id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 185 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学生作业评分' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teach_task
-- ----------------------------
DROP TABLE IF EXISTS `teach_task`;
CREATE TABLE `teach_task`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `describe` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '任务描述',
  `start_date` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `status` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '任务状态',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `teacher_id` bigint(0) NULL DEFAULT NULL COMMENT '老师id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '教学任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teach_task_test
-- ----------------------------
DROP TABLE IF EXISTS `teach_task_test`;
CREATE TABLE `teach_task_test`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_id` bigint(0) NULL DEFAULT NULL COMMENT '任务id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '实验标题',
  `data_type` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '实验类型',
  `describe` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '实验描述/目的及要求',
  `theory` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '实验原理',
  `algorithm_id` bigint(0) NULL DEFAULT NULL COMMENT '算法id',
  `case_id` bigint(0) NULL DEFAULT NULL COMMENT '案例库id',
  `rule_id` bigint(0) NULL DEFAULT NULL COMMENT '评分规则id',
  `status` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `teacher_id` bigint(0) NULL DEFAULT NULL COMMENT '老师id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 793 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '教学任务实验' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teach_task_test_data_manage
-- ----------------------------
DROP TABLE IF EXISTS `teach_task_test_data_manage`;
CREATE TABLE `teach_task_test_data_manage`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_id` bigint(0) NULL DEFAULT NULL COMMENT '任务id',
  `task_test_id` bigint(0) NULL DEFAULT NULL COMMENT '实验id',
  `data_manage_id` bigint(0) NULL DEFAULT NULL COMMENT '数据管理id',
  `status` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `teacher_id` bigint(0) NULL DEFAULT NULL COMMENT '老师id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1014 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '教学任务实验与数据管理关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teach_task_user
-- ----------------------------
DROP TABLE IF EXISTS `teach_task_user`;
CREATE TABLE `teach_task_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_id` bigint(0) NULL DEFAULT NULL COMMENT '任务id',
  `student_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `status` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `teacher_id` bigint(0) NULL DEFAULT NULL COMMENT '老师id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49949 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '教学任务与用户关联' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
