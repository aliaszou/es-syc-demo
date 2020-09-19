package com.mingo.es.sync.constant.type;

import lombok.Getter;

/**
 * es索引枚举
 *
 * @author Doflamingo
 */
public enum EsIndexType {
    ORDER("order", "订单"),
    ;

    EsIndexType(String name, String msg) {
        this.name = name;
        this.msg = msg;
    }

    @Getter
    private String msg;

    @Getter
    private String name;
}
