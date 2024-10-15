package com.yupi.usercenter.controller;

import com.yupi.usercenter.common.Resp;
import com.yupi.usercenter.model.VO.UserLoginVO;
import com.yupi.usercenter.model.domain.User;
import com.yupi.usercenter.model.request.UserLoginRequest;
import com.yupi.usercenter.model.request.UserRegisterRequest;
import com.yupi.usercenter.properties.JwtProperties;
import com.yupi.usercenter.service.UserService;
import com.yupi.usercenter.util.JwtUtil;
import com.yupi.usercenter.util.RespUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * 用户接口
 *
 * @create 2024-10-02 12:39 @Author dadaguai
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {
  private static final Logger log = LoggerFactory.getLogger(UserController.class);
  private final JwtProperties jwtProperties;

  private final UserService userService;

  public UserController(JwtProperties jwtProperties, UserService userService) {
    this.jwtProperties = jwtProperties;
    this.userService = userService;
  }

  /**
   * 用户注册
   *
   * @param userRegisterRequest
   * @return
   */
  @GetMapping("/register")
  public Resp<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
    long result =
        userService.userRegister(
            userRegisterRequest.getUserAccount(),
            userRegisterRequest.getUserPassword(),
            userRegisterRequest.getCheckPassword(),
            userRegisterRequest.getPlantCode());
    return RespUtils.success(result);
  }

  /**
   * 用户登录
   *
   * @param userLogin
   * @param request
   * @return
   */
  @ApiOperation(value = "用户登录")
  @PostMapping("/login")
  public Resp<UserLoginVO> userLogin(
      @RequestBody UserLoginRequest userLogin, HttpServletRequest request) {
    User user =
        userService.userLogin(userLogin.getUserAccount(), userLogin.getUserPassword(), request);
    HashMap<String, Object> claims = new HashMap<>();
    claims.put("userId", user.getId());
    String token =
        JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
    UserLoginVO userLoginVO = UserLoginVO.builder().id(user.getId()).token(token).build();
    // 获取cookie的值
    String cookie = request.getHeader("Cookie");
    log.info("cookie:{}", cookie);
    return RespUtils.success(userLoginVO);
  }

  /**
   * 用户查询
   *
   * @param username
   * @return
   */
  @GetMapping("/search")
  public Resp<List<User>> searchUsers(String username, HttpServletRequest request) {
    return RespUtils.success(userService.searchUsers(username, request));
  }

  @PostMapping("/delete")
  public Resp<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {
    return RespUtils.success(userService.removeUser(id, request));
  }

  /**
   * 获取用户登录态信息
   *
   * @param request
   * @return
   */
  @GetMapping("/current")
  public Resp<User> getCurrentUser(HttpServletRequest request) {
    return RespUtils.success(userService.getCurrentUser(request));
  }

  /**
   * 用户注销
   *
   * @param request
   * @return
   */
  @PostMapping("/logout")
  public Resp<Integer> logout(HttpServletRequest request) {
    return RespUtils.success(userService.userLogout(request));
  }

  /**
   * 根据编号查询用户
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "根据编号查询用户")
  @GetMapping("/planetId/{id}")
  public Resp<User> getPlanetId(@PathVariable("id") Long id, HttpServletRequest request) {
    String cookie = request.getHeader("Cookie");
    log.info("planetDI,cookie:{}", cookie);
    return RespUtils.success(userService.getById(id));
  }
}
