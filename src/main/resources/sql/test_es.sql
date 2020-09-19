/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2020-09-02 22:42:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for test_trade_order
-- ----------------------------
DROP TABLE IF EXISTS `test_trade_order`;
CREATE TABLE `test_trade_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `trade_no` varchar(30) NOT NULL COMMENT '交易订单号',
  `buyer_id` bigint(20) unsigned DEFAULT NULL COMMENT '买家id',
  `seller_id` bigint(20) unsigned DEFAULT NULL COMMENT '买家id',
  `type` tinyint(3) unsigned NOT NULL COMMENT '交易类型 1-官方商城',
  `status` tinyint(3) unsigned NOT NULL COMMENT '交易状态 1-待支付 3-待发货 5-待收货 7-已收货 9-完成 0-已取消',
  `amount` decimal(18,2) NOT NULL COMMENT '实际金额',
  `discount_amount` decimal(18,2) DEFAULT NULL COMMENT '折扣金额',
  `origin_amount` decimal(18,2) DEFAULT NULL COMMENT '原始金额',
  `pay_amount` decimal(18,2) DEFAULT NULL COMMENT '支付金额',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receiving_time` datetime DEFAULT NULL COMMENT '收货时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(11) unsigned DEFAULT '0' COMMENT '版本',
  `ext_data` varchar(2000) DEFAULT NULL COMMENT '扩展数据',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '是否删除 1-正常 2-删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_trade_no` (`trade_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for test_trade_order_line
-- ----------------------------
DROP TABLE IF EXISTS `test_trade_order_line`;
CREATE TABLE `test_trade_order_line` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `trade_no` varchar(30) NOT NULL COMMENT '交易订单号',
  `line_no` int(11) unsigned NOT NULL COMMENT '行号',
  `item_code` varchar(30) NOT NULL COMMENT '商品编码',
  `item_name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `unit_code` varchar(10) NOT NULL COMMENT '单位编码',
  `unit_name` varchar(10) NOT NULL COMMENT '单位名称',
  `type` tinyint(3) unsigned NOT NULL COMMENT '商品类型 1-普通',
  `item_price` decimal(18,2) NOT NULL COMMENT '原始金额',
  `price` decimal(18,2) NOT NULL COMMENT '实际金额',
  `discount_price` decimal(18,2) NOT NULL COMMENT '折扣金额',
  `item_qty` decimal(18,2) NOT NULL COMMENT '商品数量',
  `total_price` decimal(18,2) NOT NULL COMMENT '总原始金额',
  `paid_price` decimal(18,2) NOT NULL COMMENT '实际支付总金额',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(11) unsigned DEFAULT '0' COMMENT '版本',
  `ext_data` varchar(2000) DEFAULT NULL COMMENT '扩展数据',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '是否删除 1-正常 2-删除',
  PRIMARY KEY (`id`),
  KEY `idx_trade_no` (`trade_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表';
