package com.mingo.es.sync.service.impl;

import com.mingo.es.sync.mybaties.dataobjact.OrderDO;
import com.mingo.es.sync.mybaties.dataobjact.OrderLineDO;
import com.mingo.es.sync.mybaties.mapper.OrderLineMapper;
import com.mingo.es.sync.mybaties.mapper.OrderMapper;
import com.mingo.es.sync.service.TestOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 测试订单服务类。该类中方法只做测试
 */
@Slf4j
@Service
public class TestOrderServiceImpl implements TestOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderLineMapper orderLineMapper;

    /**
     * 创建订单
     *
     * @param orderDO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(OrderDO orderDO) {
        orderLineMapper.batchSave(orderDO.getLines());
        orderMapper.insert(orderDO);
    }

    /**
     * 查询
     *
     * @param tradeNo
     */
    @Override
    public OrderDO selectByTradeNo(String tradeNo) {
        OrderDO orderDO = orderMapper.selectByTradeNo(tradeNo);
        List<OrderLineDO> lines = orderLineMapper.select(tradeNo);
        orderDO.setLines(lines);
        return orderDO;
    }

    /**
     * 更新支付信息
     *
     * @param tradeNo
     * @param status
     * @param payAmount
     * @param payTime
     */
    @Override
    public void updatePayStatus(String tradeNo, Integer status, BigDecimal payAmount, Date payTime) {
        orderMapper.updatePayStatus(tradeNo, status, payAmount, payTime);
    }

    /**
     * 更新发货状态和时间
     *
     * @param tradeNo
     * @param status
     * @param deliveryTime
     */
    @Override
    public void updateDeliveryStatus(String tradeNo, Integer status, Date deliveryTime) {
        orderMapper.updateDeliveryStatus(tradeNo, status, deliveryTime);
    }

    /**
     * 更新收货状态和时间
     *
     * @param tradeNo
     * @param status
     * @param receivingTime
     */
    @Override
    public void updateReceivingStatus(String tradeNo, Integer status, Date receivingTime) {
        orderMapper.updateReceivingStatus(tradeNo, status, receivingTime);
    }

    /**
     * 取消单据
     *
     * @param tradeNo
     */
    @Override
    public void cancel(String tradeNo) {
        orderMapper.updateStatus(tradeNo, 0);
    }

    /**
     * 物理删除
     *
     * @param tradeNo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String tradeNo) {
        orderMapper.delete(tradeNo);
        orderLineMapper.delete(tradeNo);
    }
}
