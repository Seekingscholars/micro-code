/*
 Navicat Premium Data Transfer

 Source Server         : 3
 Source Server Type    : MySQL
 Source Server Version : 50621 (5.6.21)
 Source Host           : 192.168.0.3:3306
 Source Schema         : micro

 Target Server Type    : MySQL
 Target Server Version : 50621 (5.6.21)
 File Encoding         : 65001

 Date: 25/04/2023 11:44:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `del_flag` smallint(6) NULL DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `app_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用名称',
  `image_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片名称',
  `color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '背景颜色',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '应用类别id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '应用' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of app
-- ----------------------------
INSERT INTO `app` VALUES (596408555333, NULL, NULL, NULL, NULL, NULL, '数据分析', 'calendar', '#6495ED', 596199975045);
INSERT INTO `app` VALUES (596958858821, NULL, NULL, NULL, 'admin', '2023-04-19 00:00:00', '知识图谱', 'document', '#1E90FF', 596199975045);
INSERT INTO `app` VALUES (596973595141, NULL, NULL, NULL, 'admin', '2023-04-21 00:00:00', '用户画像', 'avatar', '#BDB76B', 596199975045);

-- ----------------------------
-- Table structure for app_category
-- ----------------------------
DROP TABLE IF EXISTS `app_category`;
CREATE TABLE `app_category`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类别名称',
  `order_no` int(11) NULL DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '应用类别' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of app_category
-- ----------------------------
INSERT INTO `app_category` VALUES (596199975045, '数据中台', 1);
INSERT INTO `app_category` VALUES (596199975046, '智慧能源', 2);
INSERT INTO `app_category` VALUES (596199975047, '安环一体化', 3);

-- ----------------------------
-- Table structure for app_form
-- ----------------------------
DROP TABLE IF EXISTS `app_form`;
CREATE TABLE `app_form`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `del_flag` smallint(6) NULL DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `form_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表单名称',
  `app_id` bigint(20) NULL DEFAULT NULL COMMENT '应用id',
  `form_json` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '表单数据',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `start_expire_time` datetime NULL DEFAULT NULL COMMENT '有效时间起',
  `end_expire_time` datetime NULL DEFAULT NULL COMMENT '有效时间止',
  `open` smallint(6) NULL DEFAULT NULL COMMENT '是否开启访问',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '表单' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of app_form
-- ----------------------------
INSERT INTO `app_form` VALUES (601437254789, 0, 'admin', '2023-04-19 00:00:00', 'admin', '2023-04-19 00:00:00', '实体属性', 596408555333, '{\"widgetList\":[{\"type\":\"grid\",\"name\":\"栅格\",\"icon\":\"grid\",\"widgetList\":[{\"type\":\"input\",\"name\":\"输入框\",\"icon\":\"text-field\",\"options\":{\"name\":\"name\",\"group\":\"searchForm\",\"type\":\"text\",\"defaultValue\":\"\",\"placeholder\":\"\",\"size\":\"\",\"readonly\":false,\"disabled\":false,\"hidden\":false,\"clearable\":true,\"showPassword\":false,\"span\":6,\"minLength\":null,\"maxLength\":null,\"showWordLimit\":false},\"form-item\":{\"label\":\"字段名称\",\"disabled\":false,\"hidden\":false,\"width\":null,\"required\":false,\"validation\":\"\",\"validationHint\":\"\"},\"events\":{\"onValidate\":\"\",\"onAppendButtonClick\":\"\",\"input\":\"\",\"change\":\"\",\"focus\":\"\",\"blur\":\"\"},\"id\":\"input28274621\"},{\"type\":\"button\",\"name\":\"按钮\",\"icon\":\"\",\"options\":{\"name\":\"button2205476\",\"label\":\"查询\",\"size\":\"\",\"disabled\":false,\"hidden\":false,\"type\":\"\",\"plain\":false,\"round\":false,\"circle\":false,\"icon\":null,\"span\":6},\"events\":{\"click\":\"listEntity()\"},\"id\":\"button2205476\"}],\"options\":{\"name\":\"searchForm\",\"gutter\":10,\"type\":\"\",\"justify\":\"start\",\"align\":\"\",\"hidden\":false,\"span\":24},\"id\":\"grid28259823\"},{\"type\":\"table\",\"name\":\"表格\",\"icon\":\"\",\"options\":{\"name\":\"table26237206\",\"stripe\":true,\"border\":true,\"showHeader\":true,\"highlightCurrentRow\":false,\"size\":\"\",\"span\":24,\"data\":\"tables\"},\"table-column\":{\"columns\":[{\"id\":\"column26245499\",\"prop\":\"name\",\"label\":\"字段名称\",\"showOverflowTooltip\":true,\"width\":\"\"},{\"id\":\"column1283088\",\"prop\":\"field\",\"label\":\"字段编码\",\"showOverflowTooltip\":true,\"width\":\"\"},{\"id\":\"column1298248\",\"prop\":\"type\",\"label\":\"字段类型\",\"showOverflowTooltip\":true,\"width\":\"\"},{\"id\":\"column1330475\",\"prop\":\"tableName\",\"label\":\"表名\",\"showOverflowTooltip\":true,\"width\":\"\"},{\"id\":\"column1311972\",\"prop\":\"createDate\",\"label\":\"创建时间\",\"showOverflowTooltip\":true,\"width\":\"\"}]},\"table-toolbar\":{\"disabled\":true,\"showCount\":2,\"label\":\"操作\",\"align\":\"center\",\"width\":\"\",\"toolbar\":[{\"id\":\"button1\",\"label\":\"编辑\",\"icon\":\"el-icon-edit\",\"click\":\"\"},{\"id\":\"button2\",\"label\":\"删除\",\"icon\":\"el-icon-delete\",\"click\":\"\"}]},\"pagination\":{\"disabled\":false,\"pageSize\":10,\"pageSizes\":[{\"pageSize\":10},{\"pageSize\":20},{\"pageSize\":50},{\"pageSize\":100}]},\"id\":\"table26237206\"}],\"formConfig\":{\"size\":\"\",\"labelWidth\":80,\"labelPosition\":\"left\",\"disabled\":false,\"dataCode\":\"tables:[]\",\"functionCode\":\"\",\"onCreated\":\"\",\"onMounted\":\"listEntity()\",\"apis\":[{\"id\":1,\"name\":\"listEntity\",\"url\":\"/test/table\",\"method\":\"GET\",\"timeout\":5000,\"remark\":null,\"header\":null,\"param\":\"...searchForm\",\"body\":null,\"delFlag\":0,\"successFunction\":\"tables=res\",\"failFunction\":null,\"formId\":601437254789}]}}', NULL, NULL, NULL, NULL);
INSERT INTO `app_form` VALUES (602891849349, 0, 'admin', '2023-04-20 00:00:00', 'admin', '2023-04-20 00:00:00', '报表分析', 596408555333, '{\"widgetList\":[{\"type\":\"grid\",\"name\":\"栅格\",\"icon\":\"grid\",\"widgetList\":[{\"type\":\"select\",\"name\":\"选择框\",\"icon\":\"select-field\",\"options\":{\"name\":\"type\",\"group\":\"searchForm\",\"defaultValue\":\"\",\"placeholder\":\"\",\"span\":12,\"size\":\"\",\"disabled\":false,\"hidden\":false,\"clearable\":true,\"filterable\":false,\"allowCreate\":false,\"remote\":false,\"automaticDropdown\":false,\"multiple\":true,\"multipleLimit\":0},\"option-item\":{\"data\":\"\",\"items\":[{\"label\":\"毛利额\",\"value\":\"MLEQH\"},{\"label\":\"成本\",\"value\":\"cost_sum\"},{\"label\":\"净收入\",\"value\":\"HYMLL\"},{\"value\":\"HYMLL_JSR\",\"label\":\"渠道收入\",\"disabled\":false},{\"value\":\"QDMLL\",\"label\":\"行业收入\",\"disabled\":false},{\"value\":\"QDMLL_JSR\",\"label\":\"成交额\",\"disabled\":false}]},\"form-item\":{\"label\":\"收入类型\",\"disabled\":false,\"hidden\":false,\"width\":null,\"required\":false,\"validation\":\"\",\"validationHint\":\"\"},\"events\":{\"change\":\"\",\"focus\":\"\",\"blur\":\"\"},\"id\":\"select7208863\"},{\"type\":\"button\",\"name\":\"按钮\",\"icon\":\"\",\"options\":{\"name\":\"button15497050\",\"label\":\"查询\",\"size\":\"\",\"disabled\":false,\"hidden\":false,\"type\":\"\",\"plain\":false,\"round\":false,\"circle\":false,\"icon\":null,\"span\":6},\"events\":{\"click\":\"listChartDong()\\nlistChartZhong()\\nlistChartBei()\\nlistChartXi()\\n\"},\"id\":\"button15497050\"}],\"options\":{\"name\":\"grid7213456\",\"gutter\":10,\"type\":\"\",\"justify\":\"start\",\"align\":\"\",\"hidden\":false,\"span\":24},\"id\":\"grid7213456\"},{\"type\":\"chart\",\"name\":\"坐标图\",\"icon\":\"\",\"options\":{\"name\":\"chart2580793\",\"span\":12,\"height\":350,\"dimension\":\"REGIO_BEZEI\",\"flip\":false,\"data\":\"chartBei\",\"items\":[{\"id\":\"item1\",\"value\":\"MLEQH\",\"label\":\"毛利润\",\"type\":\"bar\",\"color\":\"\"},{\"id\":\"item23757894\",\"value\":\"cost_sum\",\"label\":\"成本\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"},{\"id\":\"item23774679\",\"value\":\"HYMLL\",\"label\":\"净收入\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"},{\"id\":\"item23786368\",\"value\":\"HYMLL_JSR\",\"label\":\"渠道收入\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"},{\"id\":\"item23790889\",\"value\":\"QDMLL\",\"label\":\"行业收入\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"},{\"id\":\"item23799579\",\"value\":\"QDMLL_JSR\",\"label\":\"成交额\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"}]},\"chart-title\":{\"hidden\":false,\"title\":\"东北地区收入\",\"color\":\"#333333\",\"fontSize\":18,\"left\":\"left\",\"top\":\"top\"},\"chart-legend\":{\"hidden\":false,\"icon\":\"rect\",\"orient\":\"horizontal\",\"left\":\"center\",\"top\":\"top\"},\"chart-label\":{\"hidden\":false,\"position\":\"top\"},\"chart-xAxis\":{\"hidden\":false,\"rotate\":0,\"color\":\"#333333\"},\"chart-yAxis\":{\"hidden\":false,\"rotate\":0,\"color\":\"#333333\"},\"id\":\"chart2580793\"},{\"type\":\"chart\",\"name\":\"坐标图\",\"icon\":\"\",\"options\":{\"name\":\"chart2580793\",\"span\":12,\"height\":350,\"dimension\":\"REGIO_BEZEI\",\"flip\":false,\"data\":\"chartZhong\",\"items\":[{\"id\":6849011,\"value\":\"MLEQH\",\"label\":\"毛利润\",\"type\":\"bar\",\"color\":\"\"},{\"id\":6849012,\"value\":\"cost_sum\",\"label\":\"成本\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"},{\"id\":6849013,\"value\":\"HYMLL\",\"label\":\"净收入\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"},{\"id\":6849014,\"value\":\"HYMLL_JSR\",\"label\":\"渠道收入\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"},{\"id\":6849015,\"value\":\"QDMLL\",\"label\":\"行业收入\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"},{\"id\":6849016,\"value\":\"QDMLL_JSR\",\"label\":\"成交额\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"}]},\"chart-title\":{\"hidden\":false,\"title\":\"中部地区收入\",\"color\":\"#333333\",\"fontSize\":18,\"left\":\"left\",\"top\":\"top\"},\"chart-legend\":{\"hidden\":false,\"icon\":\"rect\",\"orient\":\"horizontal\",\"left\":\"center\",\"top\":\"top\"},\"chart-label\":{\"hidden\":false,\"position\":\"top\"},\"chart-xAxis\":{\"hidden\":false,\"rotate\":0,\"color\":\"#333333\"},\"chart-yAxis\":{\"hidden\":false,\"rotate\":0,\"color\":\"#333333\"},\"id\":6849017},{\"type\":\"chart\",\"name\":\"坐标图\",\"icon\":\"\",\"options\":{\"name\":\"chart2580793\",\"span\":12,\"height\":350,\"dimension\":\"REGIO_BEZEI\",\"flip\":false,\"data\":\"chartDong\",\"items\":[{\"id\":6851986,\"value\":\"MLEQH\",\"label\":\"毛利润\",\"type\":\"bar\",\"color\":\"\"},{\"id\":6851987,\"value\":\"cost_sum\",\"label\":\"成本\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"},{\"id\":6851988,\"value\":\"HYMLL\",\"label\":\"净收入\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"},{\"id\":6851989,\"value\":\"HYMLL_JSR\",\"label\":\"渠道收入\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"},{\"id\":6851990,\"value\":\"QDMLL\",\"label\":\"行业收入\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"},{\"id\":6851991,\"value\":\"QDMLL_JSR\",\"label\":\"成交额\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"}]},\"chart-title\":{\"hidden\":false,\"title\":\"东部地区收入\",\"color\":\"#333333\",\"fontSize\":18,\"left\":\"left\",\"top\":\"top\"},\"chart-legend\":{\"hidden\":false,\"icon\":\"rect\",\"orient\":\"horizontal\",\"left\":\"center\",\"top\":\"top\"},\"chart-label\":{\"hidden\":false,\"position\":\"top\"},\"chart-xAxis\":{\"hidden\":false,\"rotate\":0,\"color\":\"#333333\"},\"chart-yAxis\":{\"hidden\":false,\"rotate\":0,\"color\":\"#333333\"},\"id\":6851992},{\"type\":\"chart\",\"name\":\"坐标图\",\"icon\":\"\",\"options\":{\"name\":\"chart2580793\",\"span\":12,\"height\":350,\"dimension\":\"REGIO_BEZEI\",\"flip\":false,\"data\":\"chartXi\",\"items\":[{\"id\":6854857,\"value\":\"MLEQH\",\"label\":\"毛利润\",\"type\":\"bar\",\"color\":\"\"},{\"id\":6854858,\"value\":\"cost_sum\",\"label\":\"成本\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"},{\"id\":6854859,\"value\":\"HYMLL\",\"label\":\"净收入\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"},{\"id\":6854860,\"value\":\"HYMLL_JSR\",\"label\":\"渠道收入\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"},{\"id\":6854861,\"value\":\"QDMLL\",\"label\":\"行业收入\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"},{\"id\":6854862,\"value\":\"QDMLL_JSR\",\"label\":\"成交额\",\"disabled\":false,\"type\":\"bar\",\"color\":\"\"}]},\"chart-title\":{\"hidden\":false,\"title\":\"西部地区收入\",\"color\":\"#333333\",\"fontSize\":18,\"left\":\"left\",\"top\":\"top\"},\"chart-legend\":{\"hidden\":false,\"icon\":\"rect\",\"orient\":\"horizontal\",\"left\":\"center\",\"top\":\"top\"},\"chart-label\":{\"hidden\":false,\"position\":\"top\"},\"chart-xAxis\":{\"hidden\":false,\"rotate\":0,\"color\":\"#333333\"},\"chart-yAxis\":{\"hidden\":false,\"rotate\":0,\"color\":\"#333333\"},\"id\":6854863}],\"formConfig\":{\"size\":\"\",\"labelWidth\":80,\"labelPosition\":\"left\",\"disabled\":false,\"dataCode\":\"chartDong:[],\\nchartZhong:[],\\nchartXi:[],\\nchartBei:[]\",\"functionCode\":\"\",\"onCreated\":\"\",\"onMounted\":\"listChartDong()\\nlistChartZhong()\\nlistChartBei()\\nlistChartXi()\\n\",\"apis\":[{\"id\":605967211397,\"name\":\"listChartDong\",\"url\":\"/test/chartDong\",\"method\":\"GET\",\"timeout\":5000,\"remark\":\"查询东部地区经济\",\"header\":null,\"param\":\"...searchForm\",\"body\":\"\",\"delFlag\":0,\"successFunction\":\"chartDong=res\",\"failFunction\":null,\"formId\":602891849349},{\"id\":606804793029,\"name\":\"listChartZhong\",\"url\":\"/test/chartZhong\",\"method\":\"GET\",\"timeout\":5000,\"remark\":\"查询中部地区经济\",\"header\":null,\"param\":\"...searchForm\",\"body\":null,\"delFlag\":0,\"successFunction\":\"chartZhong=res\",\"failFunction\":null,\"formId\":602891849349},{\"id\":606808050629,\"name\":\"listChartXi\",\"url\":\"/test/chartXi\",\"method\":\"GET\",\"timeout\":5000,\"remark\":\"查询西部地区经济\",\"header\":null,\"param\":\"...searchForm\",\"body\":null,\"delFlag\":0,\"successFunction\":\"chartXi=res\",\"failFunction\":null,\"formId\":602891849349},{\"id\":606811191493,\"name\":\"listChartBei\",\"url\":\"/test/chartBei\",\"method\":\"GET\",\"timeout\":5000,\"remark\":\"查询东北地区经济\",\"header\":null,\"param\":\"...searchForm\",\"body\":null,\"delFlag\":0,\"successFunction\":\"chartBei=res\",\"failFunction\":null,\"formId\":602891849349}]}}', 'YB8HVn', NULL, NULL, 1);
INSERT INTO `app_form` VALUES (616901522949, 1, 'admin', '2023-04-22 00:00:00', NULL, NULL, '未命名表单', 596408555333, '{\"widgetList\":[],\"formConfig\":{\"size\":\"\",\"labelWidth\":80,\"labelPosition\":\"left\",\"disabled\":false,\"dataCode\":\"\",\"functionCode\":\"\",\"onCreated\":\"\",\"onMounted\":\"\"}}', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for rest_api
-- ----------------------------
DROP TABLE IF EXISTS `rest_api`;
CREATE TABLE `rest_api`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '函数名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '请求地址',
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '0' COMMENT '请求方法',
  `timeout` int(11) NULL DEFAULT NULL COMMENT '超时时长',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '函数备注',
  `header` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '请求头',
  `param` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '请求参数',
  `body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '请求体',
  `del_flag` smallint(6) NULL DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `success_function` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '请求成功处理函数',
  `fail_function` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '请求失败处理函数',
  `form_id` bigint(20) NULL DEFAULT NULL COMMENT '表单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'rest api请求' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of rest_api
-- ----------------------------
INSERT INTO `rest_api` VALUES (1, 'listEntity', '/test/table', 'GET', 5000, NULL, NULL, '...searchForm', NULL, 0, 'admin', '2023-03-17 19:38:53', 'admin', '2023-04-20 00:00:00', 'tables=res', NULL, 601437254789);
INSERT INTO `rest_api` VALUES (605967211397, 'listChartDong', '/test/chartDong', 'GET', 5000, '查询东部地区经济', NULL, '...searchForm', '', 0, 'admin', '2023-04-20 00:00:00', 'admin', '2023-04-20 00:00:00', 'chartDong=res', NULL, 602891849349);
INSERT INTO `rest_api` VALUES (606804793029, 'listChartZhong', '/test/chartZhong', 'GET', 5000, '查询中部地区经济', NULL, '...searchForm', NULL, 0, 'admin', '2023-04-20 00:00:00', 'admin', '2023-04-20 00:00:00', 'chartZhong=res', NULL, 602891849349);
INSERT INTO `rest_api` VALUES (606808050629, 'listChartXi', '/test/chartXi', 'GET', 5000, '查询西部地区经济', NULL, '...searchForm', NULL, 0, 'admin', '2023-04-20 00:00:00', 'admin', '2023-04-20 00:00:00', 'chartXi=res', NULL, 602891849349);
INSERT INTO `rest_api` VALUES (606811191493, 'listChartBei', '/test/chartBei', 'GET', 5000, '查询东北地区经济', NULL, '...searchForm', NULL, 0, 'admin', '2023-04-20 00:00:00', 'admin', '2023-04-20 00:00:00', 'chartBei=res', NULL, 602891849349);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `realname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `sex` smallint(6) NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `del_flag` smallint(6) NULL DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '管理员', '$2a$10$GK123CZgMaU2HQXFJGM46Or3EcQyZktQm6xeN1rR548Zvb/KA6QPG', '', 0, '', 0, NULL, '2019-06-21 17:54:10', 'admin', '2023-02-19 16:48:22');

SET FOREIGN_KEY_CHECKS = 1;
