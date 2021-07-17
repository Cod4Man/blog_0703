package com.codeman.blog0703.security.service;
import com.codeman.blog0703.api.OAuthClientFeignClient;
import com.codeman.blog0703.entity.SysOauthClient;
import com.codeman.blog0703.enums.PasswordEncoderTypeEnum;
import com.codeman.blog0703.vo.result.Result;
import lombok.SneakyThrows;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Resource
    private OAuthClientFeignClient oAuthClientFeignClient;

    @Override
    @SneakyThrows
    public ClientDetails loadClientByClientId(String clientId) {
        try {
            Result<SysOauthClient> result = oAuthClientFeignClient.getOAuthClientById(clientId);
            if (Result.success().getCode().equals(result.getCode())) {
                SysOauthClient client = result.getData();
                BaseClientDetails clientDetails = new BaseClientDetails(
                        client.getClientId(),
                        client.getResourceIds(),
                        client.getScope(),
                        client.getAuthorizedGrantTypes(),
                        client.getAuthorities(),
                        client.getWebServerRedirectUri());
                clientDetails.setClientSecret(PasswordEncoderTypeEnum.NOOP.getPrefix() + client.getClientSecret());
                return clientDetails;
            } else {
                throw new NoSuchClientException("No client with requested id: " + clientId);
            }
        } catch (EmptyResultDataAccessException var4) {
            throw new NoSuchClientException("No client with requested id: " + clientId);
        }
    }
}
