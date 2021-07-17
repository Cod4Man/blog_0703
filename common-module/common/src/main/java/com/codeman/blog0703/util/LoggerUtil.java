package com.codeman.blog0703.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/7/5 18:36
 * @version: 1.0
 */
@Slf4j
public class LoggerUtil {

    public static void debug(String msg, Throwable e) {
        if (log.isDebugEnabled()) {
            log.debug(msg, e);
        }
    }

    public static void error(String msg, Throwable e) {
        if (log.isErrorEnabled()) {
            log.error(msg, e);
        }
    }

    public static void info(String msg, Throwable e) {
        if (log.isInfoEnabled()) {
            log.info(msg, e);
        }
    }
}
