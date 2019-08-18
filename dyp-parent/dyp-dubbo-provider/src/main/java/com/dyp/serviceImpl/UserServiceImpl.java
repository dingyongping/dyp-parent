package com.dyp.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dyp.dao.UserMapper;
import com.dyp.entity.User;
import com.dyp.service.IUserService;
//import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author dyp
 * @since 2019-07-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
