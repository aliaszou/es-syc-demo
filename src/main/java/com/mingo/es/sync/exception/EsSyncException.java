package com.mingo.es.sync.exception;

import lombok.Getter;

/**
 * es 同步异常
 */
public class EsSyncException extends RuntimeException {

    @Getter
    private String code;

    public EsSyncException(String msg) {
        super(msg);
    }

    public EsSyncException(String code, String msg) {
        super(msg);
        this.code = code;
    }
}
