package com.cloud.setup.service.config;

import ch.qos.logback.classic.LoggerContext;
import com.cloud.setup.service.properties.AppProperties;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.encoder.LogstashEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Config {
    private static final String LOGSTASH_APPENDER_NAME = "LOGSTASH";
    private final AppProperties appProperties;

    Config(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    @ConditionalOnProperty(value = "logstash.LOGSTASH_HOST")
    public LogstashTcpSocketAppender logstashAppender() {
        String logstashUrl = String.format("%s:%s", appProperties.getLogstash().getHost(), appProperties.getLogstash().getPort());
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        LogstashTcpSocketAppender logstashTcpSocketAppender = new LogstashTcpSocketAppender();
        logstashTcpSocketAppender.setName(LOGSTASH_APPENDER_NAME);
        logstashTcpSocketAppender.setContext(loggerContext);
        logstashTcpSocketAppender.addDestination(logstashUrl);
        LogstashEncoder encoder = new LogstashEncoder();
        encoder.setContext(loggerContext);
        encoder.setIncludeContext(true);
        encoder.setCustomFields("{\"appname\":\"" + "spring-cloud-setup" + "\"}");
        encoder.start();
        logstashTcpSocketAppender.setEncoder(encoder);
        logstashTcpSocketAppender.start();
        loggerContext.getLogger(Logger.ROOT_LOGGER_NAME).addAppender(logstashTcpSocketAppender);
        return logstashTcpSocketAppender;
    }
}
