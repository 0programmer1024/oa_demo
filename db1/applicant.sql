/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80041 (8.0.41)
 Source Host           : localhost:3306
 Source Schema         : oa_demo

 Target Server Type    : MySQL
 Target Server Version : 80041 (8.0.41)
 File Encoding         : 65001

 Date: 26/05/2025 20:01:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for applicant
-- ----------------------------
DROP TABLE IF EXISTS `applicant`;
CREATE TABLE `applicant`  (
  `id` bigint NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名称',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮件',
  `gender` tinyint(1) NULL DEFAULT NULL COMMENT '性别',
  `birth_time` datetime NULL DEFAULT NULL COMMENT '出生日期',
  `graduate_time` datetime NULL DEFAULT NULL COMMENT '毕业日期',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `resume_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '简历路径',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人',
  `version` bigint NULL DEFAULT NULL COMMENT '版本',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除标记',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '居住地',
  `evaluation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评价',
  `interviewer_id` bigint NULL DEFAULT NULL COMMENT '面试官id',
  `interview_time` datetime NULL DEFAULT NULL COMMENT '应聘时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of applicant
-- ----------------------------
INSERT INTO `applicant` VALUES (1, 'name1', 'email1', 0, '2025-05-21 15:11:47', '2025-05-31 15:11:53', '12345', 'abcd', '2025-05-22 15:12:04', 2, '2025-05-26 18:32:19', 1, 1, 0, '地址1', '评价1', 3, '2025-05-22 16:10:26');
INSERT INTO `applicant` VALUES (2, 'name2', 'email2', 1, '2025-05-21 15:11:47', '2025-05-31 15:11:53', '13333333333', 'abcd', '2025-05-22 15:12:04', 2, '2025-05-22 15:12:09', 20250522151212, 1, 0, '地址2', NULL, 3, '2025-04-09 16:10:30');
INSERT INTO `applicant` VALUES (3, 'name3', 'email3', 0, '2025-05-21 15:11:47', '2025-05-31 15:11:53', '13333333333', 'abcd', '2025-05-22 15:12:04', 2, '2025-05-22 15:12:09', 20250522151212, 1, 0, NULL, '评价2', 3, '2025-05-24 16:10:34');
INSERT INTO `applicant` VALUES (1926930938935074817, 'name1', 'email1', NULL, '2025-05-21 15:11:47', '2025-05-31 15:11:53', '13333333333', 'abcd', '2025-05-26 17:18:31', 1, '2025-05-26 17:18:31', 1, NULL, 0, '地址1', NULL, 3, NULL);
INSERT INTO `applicant` VALUES (1926937625238687745, 'dwadwadaw', 'dwadawd', 1, '2025-05-26 17:29:40', '2025-05-26 17:29:42', 'dwadaw', NULL, '2025-05-26 17:45:05', 1, '2025-05-26 17:45:05', 1, NULL, 0, 'awdawdawdaw', NULL, 2, NULL);
INSERT INTO `applicant` VALUES (1926941909158187010, 'dwadwadwadwa', 'awdaw', NULL, '2025-05-26 18:02:04', '2025-05-26 18:02:05', 'wadawdaw', 'd433cd3a-7719-4251-b2e2-20a082d32194.doc', '2025-05-26 18:02:07', 1, '2025-05-26 18:02:07', 1, NULL, 0, 'dwadwad', NULL, 2, NULL);
INSERT INTO `applicant` VALUES (1926942266319949825, 'dwadwa', 'dawdaw', 0, '2025-05-26 18:03:21', '2025-05-26 18:03:22', 'dwadaw', '451bfba4-e5b9-431a-b44c-7ee19a4cbe1f.doc', '2025-05-26 18:03:32', 1, '2025-05-26 18:43:05', 1, NULL, 1, 'dawdawdaw', NULL, 2, NULL);

SET FOREIGN_KEY_CHECKS = 1;
