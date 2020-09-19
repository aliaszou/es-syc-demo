package com.mingo.es.sync.mybaties.plugin;

import com.mingo.es.sync.exception.EsSyncException;
import com.mingo.es.sync.handler.AbstractEsSyncHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * MyBaties 同步Elasticsearch拦截
 *
 * @author Doflamingo
 */
@Component
@Slf4j
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
})
public class ElasticsearchInterceptor implements Interceptor {

    /**
     * ES同步处理器
     */
    private final Map<String, AbstractEsSyncHandler> handlerMap = new HashMap<>();

    /**
     * 拦截处理
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object res = invocation.proceed();

        Object[] args = invocation.getArgs();
        if (2 == args.length) {
            MappedStatement statement = (MappedStatement) args[0];

            // mapper中的方法
            String key = statement.getId();
            // 处理
            AbstractEsSyncHandler esSynHandler = handlerMap.get(key);
            if (null != esSynHandler) {
                try {
                    esSynHandler.handler(statement, args[1], res);
                } catch (Exception e) {
                    log.error("ES同步异常：", e);
                    throw new EsSyncException("ES同步异常：" + e.getMessage());
                }
            }
        }

        return res;
    }

    /**
     * 生成代理对象
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        // 代理对象，用于触发intercept()
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    /**
     * 注册es处理器
     *
     * @param key
     * @param synHandler
     */
    public void regHandler(String key, AbstractEsSyncHandler synHandler) {
        handlerMap.put(key, synHandler);
    }
}
