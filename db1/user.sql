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

 Date: 26/05/2025 20:01:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `type` int NULL DEFAULT NULL COMMENT '类型（10：管理员, 20:面试官）',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = tis620 COLLATE = tis620_thai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin_name', 'admin', '202cb962ac59075b964b07152d234b70', 10, 0);
INSERT INTO `user` VALUES (2, 'user11', 'user1', '202cb962ac59075b964b07152d234b70', 20, 0);
INSERT INTO `user` VALUES (3, 'user22', 'user2', '12345', 20, 0);
INSERT INTO `user` VALUES (1925810363810316289, '1232123123', 'user111', '5b64a59f5250ac75314c54ebb76ecdb4', 20, 0);
INSERT INTO `user` VALUES (1925838224818569218, '1232123123', 'user111', '5b64a59f5250ac75314c54ebb76ecdb4', 20, 0);
INSERT INTO `user` VALUES (1926964161958309890, '', 'user444', '202cb962ac59075b964b07152d234b70', 20, 0);
INSERT INTO `user` VALUES (1926965015276892162, '', 'user555', '202cb962ac59075b964b07152d234b70', 20, 0);

SET FOREIGN_KEY_CHECKS = 1;
