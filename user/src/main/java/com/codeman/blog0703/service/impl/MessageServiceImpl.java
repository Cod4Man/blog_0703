package com.codeman.blog0703.service.impl;

import com.codeman.blog0703.entity.Message;
import com.codeman.blog0703.mapper.MessageMapper;
import com.codeman.blog0703.service.IMessageService;
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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
