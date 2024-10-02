package com.yupi.usercenter.controller;

import com.yupi.usercenter.model.domain.User;
import com.yupi.usercenter.model.request.UserLoginRequest;
import com.yupi.usercenter.model.request.UserRegisterRequest;
import com.yupi.usercenter.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户接口
 *
 * @create 2024-10-02 12:39 @Author dadaguai
 */
@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * 用户注册
   *
   * @param userRegisterRequest
   * @return
   */
  @RequestMapping("/register")
  public long userRegister(@RequestBody @Validated UserRegisterRequest userRegisterRequest) {

    return userService.userRegister(
        userRegisterRequest.getUserAccount(),
        userRegisterRequest.getUserPassword(),
        userRegisterRequest.getCheckPassword());
  }

  /**
   * 用户登录
   * @param userLogin
   * @param request
   * @return
   */
  @PostMapping("/login")
  public User userLogin(
          @RequestBody UserLoginRequest userLogin, HttpServletRequest request) {
    return userService.userLogin(userLogin.getUserAccount(), userLogin.getUserPassword(), request);
  }
}
