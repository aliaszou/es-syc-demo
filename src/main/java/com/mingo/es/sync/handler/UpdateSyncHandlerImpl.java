package com.mingo.es.sync.handler;

import com.mingo.es.sync.document.OrderEntity;
import com.mingo.es.sync.mybaties.dataobjact.OrderDO;
import com.mingo.es.sync.mybaties.plugin.ElasticsearchInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 更新数据es同步处理器
 *
 * @author Doflamingo
 */
@Slf4j
@Component("updateEsSyncHandler")
class UpdateSyncHandlerImpl extends AbstractEsSyncHandler {

    public UpdateSyncHandlerImpl(ElasticsearchInterceptor interceptor) {
        // 注册
        interceptor.regHandler("com.mingo.es.sync.mybaties.mapper.OrderMapper.updatePayStatus", this);
        interceptor.regHandler("com.mingo.es.sync.mybaties.mapper.OrderMapper.updateDeliveryStatus", this);
        interceptor.regHandler("com.mingo.es.sync.mybaties.mapper.OrderMapper.updateReceivingStatus", this);
        interceptor.regHandler("com.mingo.es.sync.mybaties.mapper.OrderMapper.updateStatus", this);
    }

    /**
     * 修改文档
     *
     * @param statement
     * @param parameter
     */
    private void update(MappedStatement statement, Object parameter) {
        String tradeNo = null;
        Object obj = ((MapperMethod.ParamMap) parameter).get("param1");
        if (obj instanceof OrderDO) {
            tradeNo = ((OrderDO) obj).getTradeNo();
        } else {
            tradeNo = obj.toString();
        }
        Optional<OrderEntity> optional = repository.findById(tradeNo);
        log.info("修改索引：{}", tradeNo);
        optional.ifPresent(orderEntity -> {
            this.updateEntity(orderEntity, (MapperMethod.ParamMap) parameter);
            repository.save(orderEntity);
            log.info("修改索引成功：{}", orderEntity.getTradeNo());
        });
    }

    /**
     * 更新索引字段
     *
     * @param orderEntity
     * @param paramMap
     */
    private void updateEntity(OrderEntity orderEntity, MapperMethod.ParamMap paramMap) {
        Field[] fields = orderEntity.getClass().getDeclaredFields();
        Map<String, Field> fieldMap = Arrays.stream(fields).collect(Collectors.toMap(Field::getName, Function.identity()));
        paramMap.forEach((k, v) -> {
            if (!"tradeNo".equals(k) && !"id".equals(k) && !k.toString().startsWith("param")) {
                Field field = fieldMap.get(k);
                if (null != field) {
                    field.setAccessible(true);
                    try {
                        field.set(orderEntity, v);
                    } catch (IllegalAccessException e) {
                    }
                }
            }
        });
    }

    /**
     * 同步处理器
     *
     * @param statement
     * @param parameter
     */
    @Override
    public void handler(MappedStatement statement, Object parameter, Object res) {
        if (this.checkResult(res)) {
            this.update(statement, parameter);
        }
    }
}
