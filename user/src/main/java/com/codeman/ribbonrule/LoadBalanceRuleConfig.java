package com.codeman.ribbonrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/5/17 14:53
 * @version: 1.0
 */
@Configuration
public class LoadBalanceRuleConfig {

    @Bean
    public IRule rule() {
        return new RandomRule();
    }
}
