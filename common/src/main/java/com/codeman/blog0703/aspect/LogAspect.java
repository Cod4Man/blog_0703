package com.codeman.blog0703.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/5/17 8:58
 * @version: 1.0
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private ExecutorService executorService;

    @Pointcut("execution(public * com.codeman.blog0703.controller.*.*(..))")
    public void pointCutLog() {

    }

    @Before(value = "pointCutLog()")
    public void methodBefore(JoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        try {
            executorService.execute(() -> {
                logBefore(joinPoint, startTime);
            });

        } finally {
//            executorService.shutdown();
        }
    }

    @AfterReturning(value = "pointCutLog()", returning = "result")
    public void methodAfter(JoinPoint joinPoint, Object result) {
        long endTime = System.currentTimeMillis();
        try {
            executorService.execute(() -> {
                logAfter(joinPoint, result, endTime);
            });

        } finally {
//            executorService.shutdown();
        }
    }

    private void logAfter(JoinPoint joinPoint, Object result, long endTime) {
        String name = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        StringBuilder stringBuffer = new StringBuilder("【自定义日志】======【方法结束】【")
                .append(endTime).append("】: ");
        stringBuffer.append(name).append(".").append(methodName).append("(");
        Arrays.stream(args).forEach(p -> stringBuffer.append(p.getClass().getTypeName()).append(" ").append(p).append(","));
        if (stringBuffer.lastIndexOf(",") == stringBuffer.length() - 1) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        stringBuffer.append(");");
        stringBuffer.append("return: " + result);
        dolog(stringBuffer);
    }

    private void logBefore(JoinPoint joinPoint, long startTime) {
        String name = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        StringBuilder stringBuffer = new StringBuilder("【自定义日志】======【方法开始】【")
                .append(startTime).append("】: ");
        stringBuffer.append(name).append(".").append(methodName).append("(");
        Arrays.stream(args).forEach(p -> stringBuffer.append(p.getClass().getTypeName()).append(" ").append(p).append(","));
        if (stringBuffer.lastIndexOf(",") == stringBuffer.length() - 1) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        stringBuffer.append(");");
        dolog(stringBuffer);
    }

    private void dolog(CharSequence charSequence) {
        if (log.isDebugEnabled()) {
            log.debug(charSequence.toString());
        }
    }
}
