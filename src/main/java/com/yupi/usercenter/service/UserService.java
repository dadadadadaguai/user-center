package com.yupi.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.usercenter.model.domain.User;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 12086
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2024-09-29 11:19:49
 */
public interface UserService extends IService<User> {

  long userRegister(String userAccount, String userPassword, String checkPassword);

  User userLogin(String userAccount, String userPassword, HttpServletRequest request);
}
