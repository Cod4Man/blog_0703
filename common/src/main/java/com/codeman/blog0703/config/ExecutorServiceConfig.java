package com.codeman.blog0703.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/7/5 21:36
 * @version: 1.0
 */
@Configuration
@Slf4j
public class ExecutorServiceConfig {

    @Value("${blog0703.thread-pool.core-thread-num:10}")
    private Integer coreThreadNum;
    @Value("${blog0703.thread-pool.max-thread-num:10}")
    private Integer maxThreadNum;
    @Value("${blog0703.thread-pool.keep-alive-time:30}")
    private Long keepAliveTime;
    @Value("${blog0703.thread-pool.keep-alive-time-unit:SECONDS}")
    private TimeUnit keepAliveTimeUnit;
    @Value("${blog0703.thread-pool.bq-num:30}")
    private Integer bqNum;

    @Bean
    public ExecutorService executorService() {
       // CPU核数
       // int cpuCount = Runtime.getRuntime().availableProcessors();
        log.info("new ThreadPoolExecutor({}, {}, {}, {}, new LinkedBlockingQueue<>({}))", coreThreadNum, maxThreadNum, keepAliveTime, keepAliveTimeUnit, bqNum);
        return new ThreadPoolExecutor(coreThreadNum, maxThreadNum, keepAliveTime, keepAliveTimeUnit, new LinkedBlockingQueue<>(bqNum));
    }
}
