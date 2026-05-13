/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : flower_mall

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 09/01/2026 12:35:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人名字',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '地址' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (7, '张佳佳', '13212351441', '幸福大街8号楼', 2);
INSERT INTO `address` VALUES (6, '番茄源码', '13212344562', '番茄大街3号楼', 2);
INSERT INTO `address` VALUES (9, '王萌萌', '13212344567', '升龙凤凰城街道', 1);

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456', '13612341234', 'admin', 'http://localhost:9090/files/1752836362840-1.jpg', 'fanqieyuanma.cn@qq.com', '管理员');

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '物品ID',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '轮播图' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (6, 'http://localhost:9090/files/1767514028661-生日轮播图.png', 27, 2);
INSERT INTO `banner` VALUES (8, 'http://localhost:9090/files/1767514003119-轮播玫瑰.png', 23, 1);
INSERT INTO `banner` VALUES (9, 'http://localhost:9090/files/1767931503702-祝福1111.webp', 29, 3);

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '物品ID',
  `shopkeeper_id` int(11) NULL DEFAULT NULL COMMENT '商家ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物车' ROW_FORMAT = FIXED;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (48, 1, 2, 22, 15);
INSERT INTO `cart` VALUES (46, 3, 2, 20, 15);

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `goods_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = FIXED;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES (17, 1, 20);
INSERT INTO `collect` VALUES (19, 1, 22);
INSERT INTO `collect` VALUES (24, 2, 22);
INSERT INTO `collect` VALUES (21, 2, 20);
INSERT INTO `collect` VALUES (23, 2, 23);
INSERT INTO `collect` VALUES (25, 1, 28);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT NULL,
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reply` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_id` int(11) NULL DEFAULT NULL,
  `shopkeeper_id` int(11) NOT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (12, '20260109115833E736B', 5, 'http://localhost:9090/files/1767931236465-2222.webp,http://localhost:9090/files/1767931238288-111.webp', '鲜花非常好看 一点也不枯萎', '', '2026-01-09 12:00:39', 31, 17, 1);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `num` int(11) NULL DEFAULT NULL COMMENT '库存',
  `type_id` int(11) NULL DEFAULT NULL COMMENT '分类ID',
  `material` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '材料',
  `wrapper` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '包装',
  `exp` int(11) NULL DEFAULT NULL COMMENT '保质期',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `shopkeeper_id` int(11) NULL DEFAULT NULL COMMENT '商家ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物品' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (20, '会议桌花专用', 'http://localhost:9090/files/1767507044196-会议.jpg', 66.00, '<p>会议办公室的不二之选</p>', 14, 6, '白雪山玫瑰9枝、蓝绣球1枝', ' 中号玻璃方缸', 5, '上架', 15);
INSERT INTO `goods` VALUES (23, '99枝红玫瑰', 'http://localhost:9090/files/1767506179879-红玫瑰.jpg', 269.00, '<p><img src=\"http://localhost:9090/files/1767506251265\" alt=\"\" data-href=\"\" style=\"\"/></p>', 95, 2, '玫瑰花99枝', '内层白色雪梨纸', 5, '上架', 16);
INSERT INTO `goods` VALUES (22, '开业花篮-繁花', 'http://localhost:9090/files/1767506951834-开业2.jpg', 452.00, '<p>开业</p>', 18, 7, '粉色绣球，紫罗兰粉色8枝，粉玫瑰12枝', '嫣粉/玫粉色欧雅纸15张', 8, '上架', 15);
INSERT INTO `goods` VALUES (24, '99枝戴安娜粉玫瑰', 'http://localhost:9090/files/1767506292706-粉玫瑰.jpg', 456.00, NULL, 99, 1, '粉色玫瑰花 99枝', '浅粉纸包装，精美罗纹带花结', 7, '上架', 16);
INSERT INTO `goods` VALUES (25, '鲜花系列11枝香槟玫瑰', 'http://localhost:9090/files/1767506446961-生日鲜花.jpg', 245.00, '<p>生日专用</p>', 100, 3, '玫瑰花11枝', '牛皮纸包装', 7, '上架', 16);
INSERT INTO `goods` VALUES (26, '12朵白百合', 'http://localhost:9090/files/1767506534841-百合.jpg', 236.00, '<p>看望病人</p>', 96, 5, '12支玫瑰 百合', '白色雾面纸', 5, '上架', 16);
INSERT INTO `goods` VALUES (27, '心愿盛放康乃馨', 'http://localhost:9090/files/1767506762356-康乃馨.jpg', 169.00, '<p>祝福</p>', 5, 1, ' 向日葵2枝，粉色康乃馨13枝，白色紫罗兰2枝，绿色桔梗5枝', '防水艺术范包装纸A款（粉）1张，白色防水纸2张', 7, '上架', 16);
INSERT INTO `goods` VALUES (28, '开业大卖麦子双花篮', 'http://localhost:9090/files/1767506892915-999.jpg', 266.00, '<p>开业专用花篮</p>', 99, 7, '本商品为2个开业花篮组合。单个花篮花材', '木三角架、塑料篮、花泥、红缎带', 30, '上架', 15);
INSERT INTO `goods` VALUES (29, '祝贺鲜花使用温暖阳光', 'http://localhost:9090/files/1767930372036-1111.jpg', 235.00, '<p>最好的祝福</p><p><img src=\"http://localhost:9090/files/1767930460373\" alt=\"\" data-href=\"\" style=\"\"/></p>', 96, 1, '11枝香槟玫瑰2枝向日葵', '黄色与红色雾面纸', 7, '上架', 17);
INSERT INTO `goods` VALUES (30, '精品白红玫瑰', 'http://localhost:9090/files/1767930574479-玫瑰.webp', 365.00, '<p>送对象</p>', 50, 2, '20支红玫瑰', '红色丝带', 5, '上架', 17);
INSERT INTO `goods` VALUES (31, '早日康复香槟玫瑰送病人', 'http://localhost:9090/files/1767930693122-12222.png', 146.00, '<p>病人慰问</p>', 45, 5, '8枝香槟玫瑰', '黄色雾面纸 白色棉纸', 6, '上架', 17);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建时间',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '公告信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '欢迎使用本系统', '网站已经上线，欢迎同学们使用 多多宣传 系统会逐步完善', '2025-03-05', 1);
INSERT INTO `notice` VALUES (2, '庆祝鲜花商城新增店铺', '今日商城新增了25家店铺，将会招聘更多配送人员保证送达速度', '2026-01-09', 1);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `total` decimal(10, 2) NULL DEFAULT NULL COMMENT '总价',
  `address_id` int(11) NULL DEFAULT NULL COMMENT '地址ID',
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '物品ID',
  `shopkeeper_id` int(11) NULL DEFAULT NULL COMMENT '商家ID',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '时间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (24, '20260109115833E736B', 5, 730.00, 9, 31, 17, 1, '2025-12-09 11:58:33', '已评价');
INSERT INTO `order` VALUES (23, '2026010911560902FCE', 1, 266.00, 9, 28, 15, 1, '2026-01-09 11:56:09', '已购买');
INSERT INTO `order` VALUES (22, '2026010911560896048', 4, 940.00, 9, 29, 17, 1, '2026-01-09 11:56:08', '已购买');
INSERT INTO `order` VALUES (19, '20260106182100B8050', 5, 845.00, 9, 27, 16, 1, '2026-01-06 18:21:00', '已购买');
INSERT INTO `order` VALUES (20, '20260106182120BD4F0', 3, 807.00, 9, 23, 16, 1, '2026-01-06 18:21:20', '已购买');
INSERT INTO `order` VALUES (21, '20260106182145C3786', 2, 904.00, 9, 22, 15, 1, '2026-01-06 18:21:45', '已购买');
INSERT INTO `order` VALUES (18, '202601061819587CCCA', 6, 396.00, 9, 20, 15, 1, '2026-01-06 18:19:58', '已购买');

-- ----------------------------
-- Table structure for shopkeeper
-- ----------------------------
DROP TABLE IF EXISTS `shopkeeper`;
CREATE TABLE `shopkeeper`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shop_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shopkeeper
-- ----------------------------
INSERT INTO `shopkeeper` VALUES (15, '666', '每日鲜花旗舰店', '李华华', '666', NULL, 'http://localhost:9090/files/default.jpg', NULL, 'shopkeeper');
INSERT INTO `shopkeeper` VALUES (16, '369', '鲜花优选', '杜萌萌', '369', '13212341234', 'http://localhost:9090/files/default.jpg', '5644444@qq.com', 'shopkeeper');
INSERT INTO `shopkeeper` VALUES (17, '123456', '甄选鲜花店', '李学然', '123456', '13212345678', 'http://localhost:9090/files/1767930846469-258.jpg', '16581214@qq.com', 'shopkeeper');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '祝贺鲜花');
INSERT INTO `type` VALUES (2, '爱情鲜花');
INSERT INTO `type` VALUES (3, '生日鲜花');
INSERT INTO `type` VALUES (5, '探病慰问');
INSERT INTO `type` VALUES (6, '会议桌花');
INSERT INTO `type` VALUES (7, '开业花篮');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '李华', '123', '123', 'http://localhost:9090/files/1755930614072-avatar5.webp', '男', 21, '13212348888', '1234567@qq.com', 'user', '2025-12-06 10:11:54');
INSERT INTO `user` VALUES (2, '张佳佳', '1234', '1234', 'http://localhost:9090/files/1759840966518-006i0nC8ly1hqy59asz8aj30og0ogad2.jpg', '男', NULL, '13212341234', '68753444@qq.com', 'user', '2025-12-06 10:11:54');
INSERT INTO `user` VALUES (5, '吴佳琪', '123456', '123456', 'http://localhost:9090/files/default.jpg', NULL, NULL, NULL, NULL, 'user', '2026-01-06 10:37:54');

SET FOREIGN_KEY_CHECKS = 1;
