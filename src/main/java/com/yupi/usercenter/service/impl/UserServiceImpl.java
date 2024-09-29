package com.yupi.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.usercenter.model.domain.User;
import com.yupi.usercenter.mapper.UserMapper;
import com.yupi.usercenter.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author 12086
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-09-29 11:19:49
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




