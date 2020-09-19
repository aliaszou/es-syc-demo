package com.mingo.es.sync.handler;

import com.mingo.es.sync.mybaties.plugin.ElasticsearchInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.stereotype.Component;

/**
 * 物理删除数据es同步处理器
 *
 * @author Doflamingo
 */
@Slf4j
@Component("deleteEsSyncHandler")
class DeleteSyncHandlerImpl extends AbstractEsSyncHandler {

    public DeleteSyncHandlerImpl(ElasticsearchInterceptor interceptor) {
        // 注册
        interceptor.regHandler("com.mingo.es.sync.mybaties.mapper.OrderMapper.delete", this);
    }

    /**
     * 删除文档
     *
     * @param statement
     * @param parameter
     */
    private void delete(MappedStatement statement, Object parameter) {
        String tradeNo = ((MapperMethod.ParamMap) parameter).get("param1").toString();
        log.info("删除索引：{}", tradeNo);
        repository.deleteById(tradeNo);
        log.info("删除索引成功：{}", tradeNo);
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
            this.delete(statement, parameter);
        }
    }
}
