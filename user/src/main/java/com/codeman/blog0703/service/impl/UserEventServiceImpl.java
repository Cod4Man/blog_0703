package com.codeman.blog0703.service.impl;

import com.codeman.blog0703.entity.UserEvent;
import com.codeman.blog0703.mapper.UserEventMapper;
import com.codeman.blog0703.service.IUserEventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhanghongjie
 * @since 2021-07-03
 */
@Service
public class UserEventServiceImpl extends ServiceImpl<UserEventMapper, UserEvent> implements IUserEventService {

}
