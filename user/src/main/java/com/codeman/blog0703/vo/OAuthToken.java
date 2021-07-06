package com.codeman.blog0703.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class OAuthToken implements Serializable {

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

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
}
