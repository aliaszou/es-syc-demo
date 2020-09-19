package com.mingo.es.sync.config;

import com.mingo.es.sync.constant.type.EsIndexType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 索引命名
 */
@Component
public class EsConfig {

    @Value("${spring.application.name:order-service}")
    private String appName;

    public String name(EsIndexType type) {
        String yyyyMM = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM"));
        String patter = "%s-%s-%s";
        return String.format(patter, appName.toLowerCase(), type.getName(), yyyyMM);
    }
}
