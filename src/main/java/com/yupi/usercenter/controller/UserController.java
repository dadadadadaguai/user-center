package com.yupi.usercenter.controller;

import com.yupi.usercenter.model.domain.User;
import com.yupi.usercenter.model.request.UserLoginRequest;
import com.yupi.usercenter.model.request.UserRegisterRequest;
import com.yupi.usercenter.service.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
  @GetMapping("/register")
  public long userRegister(@RequestBody @Validated UserRegisterRequest userRegisterRequest) {

    return userService.userRegister(
        userRegisterRequest.getUserAccount(),
        userRegisterRequest.getUserPassword(),
        userRegisterRequest.getCheckPassword(),
        userRegisterRequest.getPlantCode());
  }

  /**
   * 用户登录
   *
   * @param userLogin
   * @param request
   * @return
   */
  @PostMapping("/login")
  public User userLogin(@RequestBody UserLoginRequest userLogin, HttpServletRequest request) {
    return userService.userLogin(userLogin.getUserAccount(), userLogin.getUserPassword(), request);
  }

  /**
   * 用户查询
   *
   * @param username
   * @return
   */
  @GetMapping("/search")
  public List<User> searchUsers(String username, HttpServletRequest request) {
    return userService.searchUsers(username, request);
  }

  @PostMapping("/delete")
  public boolean deleteUser(@RequestBody long id, HttpServletRequest request) {
    return userService.removeUser(id, request);
  }

  /**
   * 获取用户登录态信息
   *
   * @param request
   * @return
   */
  @GetMapping("/current")
  public User getCurrentUser(HttpServletRequest request) {
    return userService.getCurrentUser(request);
  }

  /**
   * 用户注销
   *
   * @param request
   * @return
   */
  @PostMapping("/logout")
  public Integer logout(HttpServletRequest request) {
    return userService.userLogout(request);
  }
}
