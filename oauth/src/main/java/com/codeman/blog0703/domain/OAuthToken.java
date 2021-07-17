package com.codeman.blog0703.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述: [自定义token]
 * 创建时间: 2021/6/8
 *
 * @author hxr
 * @version 1.0.0
 * @update [序号][日期YYYY-MM-DD] [更改人姓名][变更描述]
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OAuthToken {

    private String access_token;

    private String token_type = "bearer";

    public OAuthToken accessToken(String accessToken) {
        this.access_token = accessToken;
        return this;
    }

    public OAuthToken tokenType(String tokenType) {
        this.token_type = tokenType;
        return this;
    }

}