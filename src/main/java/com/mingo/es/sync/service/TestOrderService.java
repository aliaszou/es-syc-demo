package com.mingo.es.sync.service;

import com.mingo.es.sync.mybaties.dataobjact.OrderDO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 测试订单服务类
 */
public interface TestOrderService {

    /**
     * 创建订单
     *
     * @param orderDO
     */
    void create(OrderDO orderDO);

    /**
     * 查询
     *
     * @param tradeNo
     */
    OrderDO selectByTradeNo(String tradeNo);

    /**
     * 更新支付信息
     *
     * @param tradeNo
     * @param status
     * @param payAmount
     * @param payTime
     */
    void updatePayStatus(String tradeNo, Integer status, BigDecimal payAmount, Date payTime);

    /**
     * 更新发货状态和时间
     *
     * @param tradeNo
     * @param status
     * @param deliveryTime
     */
    void updateDeliveryStatus(String tradeNo, Integer status, Date deliveryTime);

    /**
     * 更新收货状态和时间
     *
     * @param tradeNo
     * @param status
     * @param receivingTime
     */
    void updateReceivingStatus(String tradeNo, Integer status, Date receivingTime);

    /**
     * 取消单据
     *
     * @param tradeNo
     */
    void cancel(String tradeNo);

    /**
     * 物理删除
     *
     * @param tradeNo
     */
    void delete(String tradeNo);
}
