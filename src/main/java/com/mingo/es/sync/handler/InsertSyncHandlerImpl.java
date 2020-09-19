package com.mingo.es.sync.handler;

import com.mingo.es.sync.document.OrderEntity;
import com.mingo.es.sync.mybaties.dataobjact.OrderDO;
import com.mingo.es.sync.mybaties.plugin.ElasticsearchInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.stereotype.Component;

/**
 * 插入数据es同步处理器
 *
 * @author Doflamingo
 */
@Slf4j
@Component("insertEsSyncHandler")
class InsertSyncHandlerImpl extends AbstractEsSyncHandler {

    public InsertSyncHandlerImpl(ElasticsearchInterceptor interceptor) {
        // 注册
        interceptor.regHandler("com.mingo.es.sync.mybaties.mapper.OrderMapper.insert", this);
    }

    /**
     * 创建文档
     *
     * @param statement
     * @param parameter
     */
    private void create(MappedStatement statement, Object parameter) {
        log.info("保存索引：{}", ((OrderDO) parameter).getTradeNo());
        // es实体
        OrderEntity document = convertor.doToEsEntity((OrderDO) parameter);
        // 保存
        OrderEntity save = repository.save(document);
        log.info("保存索引成功：{}", save.getId());
    }

    /**
     * 同步处理器
     *
     * @param statement
     * @param parameter
     */
    @Override
    public void handler(MappedStatement statement, Object parameter, Object res) {
        // sql执行失败不会保存
        if (this.checkResult(res)) {
            this.create(statement, parameter);
        }
    }
}
