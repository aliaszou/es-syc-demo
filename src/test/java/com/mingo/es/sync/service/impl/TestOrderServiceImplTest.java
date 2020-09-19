package com.mingo.es.sync.service.impl;

import com.mingo.es.sync.mybaties.dataobjact.OrderDO;
import com.mingo.es.sync.mybaties.dataobjact.OrderLineDO;
import com.mingo.es.sync.repository.EsOrderDocRepository;
import com.mingo.es.sync.service.TestOrderService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
class TestOrderServiceImplTest {

    @Autowired
    private TestOrderService testOrderService;

    @Autowired
    protected EsOrderDocRepository repository;

    // 测试单号
    private final String testTradeNo = "20200919018243198";

    @Test
    void create() {
        OrderDO orderDO = new OrderDO();
        orderDO.setTradeNo(testTradeNo);
        orderDO.setAmount(BigDecimal.ONE);
        orderDO.setBuyerId(9527L);
        orderDO.setCreateTime(new Date());
        orderDO.setDiscountAmount(BigDecimal.ZERO);
        orderDO.setOriginAmount(BigDecimal.ONE);
        orderDO.setType(1);
        orderDO.setSellerId(18899L);
        orderDO.setStatus(1);

        OrderLineDO lineDO = new OrderLineDO();
        lineDO.setCreateTime(orderDO.getCreateTime());
        lineDO.setDiscountPrice(BigDecimal.ZERO);
        lineDO.setItemCode("6352678819");
        lineDO.setItemName("泡椒凤爪");
        lineDO.setUnitCode("DAI");
        lineDO.setUnitName("袋");
        lineDO.setItemPrice(BigDecimal.ONE);
        lineDO.setPrice(BigDecimal.ONE);
        lineDO.setPaidPrice(BigDecimal.ONE);
        lineDO.setDiscountPrice(BigDecimal.ZERO);
        lineDO.setTotalPrice(BigDecimal.ONE);
        lineDO.setItemQty(BigDecimal.ONE);
        lineDO.setLineNo("1");
        lineDO.setTradeNo(orderDO.getTradeNo());
        lineDO.setType(1);
        orderDO.setLines(Lists.newArrayList(lineDO));
        testOrderService.create(orderDO);
    }

    @Test
    void updatePayStatus() {
        testOrderService.updatePayStatus(testTradeNo, 3, BigDecimal.ONE, new Date());
    }

    @Test
    void updateDeliveryStatus() {
        testOrderService.updateDeliveryStatus(testTradeNo, 5, new Date());
    }

    @Test
    void updateReceivingStatus() {
        testOrderService.updateReceivingStatus(testTradeNo, 7, new Date());
    }

    @Test
    void cancel() {
        testOrderService.cancel(testTradeNo);
    }

    @Test
    void delete() {
        testOrderService.delete(testTradeNo);
    }
}