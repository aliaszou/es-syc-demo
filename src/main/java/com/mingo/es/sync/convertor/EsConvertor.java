package com.mingo.es.sync.convertor;

import com.mingo.es.sync.document.OrderEntity;
import com.mingo.es.sync.mybaties.dataobjact.OrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * mapstruct
 */
@Mapper(componentModel = "spring")
public interface EsConvertor {

    /**
     * tradeNo 设值为 id
     *
     * @param orderDO
     * @return
     */
    @Mapping(target = "id", source = "tradeNo")
    OrderEntity doToEsEntity(OrderDO orderDO);
}
