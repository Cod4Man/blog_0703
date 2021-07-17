package com.codeman.blog0703.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codeman.blog0703.entity.SysOauthClient;
import com.codeman.blog0703.mapper.SysOauthClientMapper;
import com.codeman.blog0703.service.ISysOauthClientService;
import org.springframework.stereotype.Service;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/7/16 23:47
 * @version: 1.0
 */
@Service("iSysOauthClientService")
public class SysOauthClientServiceImpl extends ServiceImpl<SysOauthClientMapper, SysOauthClient> implements ISysOauthClientService {
}
