package com.mingo.es.sync.handler;

import com.mingo.es.sync.convertor.EsConvertor;
import com.mingo.es.sync.repository.EsOrderDocRepository;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * es同步处理器 抽象父类
 *
 * @author Doflamingo
 */
public abstract class AbstractEsSyncHandler {

    @Autowired
    protected EsOrderDocRepository repository;

    @Autowired
    protected EsConvertor convertor;

    /**
     * sql执行结果
     *
     * @param res
     * @return
     */
    protected boolean checkResult(Object res) {
        return res instanceof Number && Long.parseLong(res.toString()) > 0;
    }

    /**
     * 同步处理器
     *
     * @param statement
     * @param parameter
     */
    public abstract void handler(MappedStatement statement, Object parameter, Object res);
}
