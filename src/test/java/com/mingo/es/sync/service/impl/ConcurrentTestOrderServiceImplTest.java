package com.mingo.es.sync.service.impl;

import com.mingo.es.sync.document.OrderEntity;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
class ConcurrentTestOrderServiceImplTest {

    @Autowired
    private TestOrderService testOrderService;

    @Autowired
    protected EsOrderDocRepository repository;

    private final AtomicInteger atomicInteger = new AtomicInteger(1);
    private final String YYYYMMDDHHMMSS = "yyyyMMddHHmmssSSS";
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(YYYYMMDDHHMMSS);

    public String getTradeNo() {
        return LocalDateTime.now().format(FORMATTER) + atomicInteger.getAndAdd(1);
    }

    // 12个线程
    private final ExecutorService executorService =
            new ThreadPoolExecutor(
                    12,
                    12,
                    0,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingDeque<>()
            );

    /**
     * 用于测试该时间后的数据删除：2020-09-19 22:05:33
     */
    private final Date start = new Date(1600524351243L);

    @Test
    void create() throws Exception {

        for (int i = 500; i > 0; i--) {
            // 启动
            executorService.execute(() -> {
                OrderDO orderDO = new OrderDO();
                orderDO.setTradeNo(this.getTradeNo());
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
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(20, TimeUnit.SECONDS);
    }

    @Test
    void updatePayStatus() {
        List<OrderEntity> entities = repository.findByCreateTimeGreaterThan(start);
        entities.parallelStream().forEach(entity ->
                testOrderService.updatePayStatus(entity.getTradeNo(), 3, BigDecimal.ONE, new Date())
        );
    }

    @Test
    void updateDeliveryStatus() {
        List<OrderEntity> entities = repository.findByCreateTimeGreaterThan(start);
        entities.parallelStream().forEach(entity ->
                testOrderService.updateDeliveryStatus(entity.getTradeNo(), 5, new Date())
        );
    }

    @Test
    void updateReceivingStatus() {
        List<OrderEntity> entities = repository.findByCreateTimeGreaterThan(start);
        entities.parallelStream().forEach(entity ->
                testOrderService.updateReceivingStatus(entity.getTradeNo(), 7, new Date())
        );
    }

    @Test
    void cancel() {
        List<OrderEntity> entities = repository.findByCreateTimeGreaterThan(start);
        entities.parallelStream().forEach(entity ->
                testOrderService.cancel(entity.getTradeNo())
        );
    }

    @Test
    void delete() {
        List<OrderEntity> entities = repository.findByCreateTimeGreaterThan(start);
        entities.parallelStream().forEach(entity ->
                testOrderService.delete(entity.getTradeNo())
        );
    }
}