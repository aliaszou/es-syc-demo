package com.mingo.es.sync.repository;

import com.mingo.es.sync.document.OrderEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * EsOrderDocRepository
 *
 * @author Doflamingo
 */
public interface EsOrderDocRepository extends ElasticsearchRepository<OrderEntity, String> {

    /**
     * 根据tradeNo查询单据信息
     *
     * @param tradeNo
     * @return
     */
    OrderEntity findByTradeNo(String tradeNo);
}
