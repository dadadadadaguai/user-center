package com.yupi.usercenter.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.usercenter.common.BusinessException;
import com.yupi.usercenter.constant.HttpRespCode;
import com.yupi.usercenter.constant.UserConstant;
import com.yupi.usercenter.mapper.UserMapper;
import com.yupi.usercenter.model.domain.User;
import com.yupi.usercenter.service.UserService;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import static com.yupi.usercenter.constant.UserConstant.SALT;

/**
 * @author dadaguai
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2024-09-29 11:19:49
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  private static final String USER_INFO_STATE = "USER_INFO_STATE";

  /**
   * @param userAccount
   * @param userPassword
   * @param checkPassword
   * @return
   */
  @Override
  public long userRegister(
      String userAccount, String userPassword, String checkPassword, String plantCode) {
    // 校验
    if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, plantCode)) {
      throw new BusinessException(HttpRespCode.BAD_REQUEST, "参数为空");
    }
    if (userAccount.length() < 4) {
      throw new BusinessException(HttpRespCode.BAD_REQUEST, "账号长度不小于4");
    }
    if (userPassword.length() < 8 || checkPassword.length() < 8) {
      throw new BusinessException(HttpRespCode.BAD_REQUEST, "密码长度不小于8");
    }
    if (!userPassword.equals(checkPassword)) {
      throw new BusinessException(HttpRespCode.BAD_REQUEST, "密码不一致");
    }
    if (plantCode.length() > 5) {
      throw new BusinessException(HttpRespCode.BAD_REQUEST, "星球编号长度不大于5");
    }
    // 校验账号是否含有不合法字符
    String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    if (userAccount.matches(validPattern)) {
      throw new BusinessException(HttpRespCode.BAD_REQUEST, "账号含有非法字符");
    }
    // 密码加密
    String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
    // 查询账号是否重复
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    QueryWrapper<User> DbUser = queryWrapper.eq("userAccount", userAccount);
    if (this.count(DbUser) > 0) {
      throw new BusinessException(HttpRespCode.BAD_REQUEST, "账号重复");
    }
    // 查询星球编号
    queryWrapper = new QueryWrapper<>();
    QueryWrapper<User> queryPlantCode = queryWrapper.eq("plantCode", plantCode);
    if (this.count(queryPlantCode) > 0) {
      throw new BusinessException(HttpRespCode.BAD_REQUEST, "星球编号重复");
    }

    // 插入数据
    User user =
        User.builder()
            .userAccount(userAccount)
            .userPassword(encryptPassword)
            .plantCode(plantCode)
            .build();
    boolean saveResult = this.save(user);
    if (!saveResult) {
      throw new BusinessException(HttpRespCode.BAD_REQUEST, "注册失败");
    }
    return user.getId();
  }

  /**
   * @param userAccount
   * @param userPassword
   * @param request
   * @return
   */
  public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
    // 校验
    if (StringUtils.isAnyBlank(userAccount, userPassword)) {
      return null;
    }
    int len = userAccount.length();
    if (len < 4) {
      throw new BusinessException(HttpRespCode.BAD_REQUEST, "账号长度不小于4");
    }
    if (userPassword.length() < 8) {
      throw new BusinessException(HttpRespCode.BAD_REQUEST, "密码长度不小于8");
    }
    // 校验账号是否含有不合法字符
    String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    if (userAccount.matches(validPattern)) {
      throw new BusinessException(HttpRespCode.BAD_REQUEST, "账号含有非法字符");
    }
    String Md5Password = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
    // 查询
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("userAccount", userAccount);
    queryWrapper.eq("userPassword", Md5Password);
    User dbUser = this.getOne(queryWrapper);
    if (dbUser == null) {
      throw new BusinessException(HttpRespCode.BAD_REQUEST, "账号或密码错误");
    }
    // 脱敏
    dbUser = getSafetyUser(dbUser);
    request.getSession().setAttribute(USER_INFO_STATE, dbUser);
    return dbUser;
  }

  @Override
  public List<User> searchUsers(String username, HttpServletRequest request) {
    Object userobj = request.getSession().getAttribute(USER_INFO_STATE);
    User loginUser = (User) userobj;
    if (!Objects.equals(loginUser.getUserRole(), UserConstant.ADMINROLE)
        || StringUtils.isNotBlank(username)) {
      return Collections.emptyList();
    }
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("username", username); // 模糊查询
    queryWrapper.eq("userStatus", 0);
    List<User> userList = this.list(queryWrapper);
    return userList.stream().map(this::getSafetyUser).collect(Collectors.toList());
  }

  /**
   * 删除用户
   *
   * @param id
   * @return
   */
  @Override
  public Boolean removeUser(long id, HttpServletRequest request) {
    Object userobj = request.getSession().getAttribute(USER_INFO_STATE);
    User loginUser = (User) userobj;
    if (!Objects.equals(loginUser.getUserRole(), UserConstant.ADMINROLE)) {
      return false;
    }
    return this.removeById(id);
  }

  @Override
  public User getCurrentUser(HttpServletRequest request) {
    Object user = request.getSession().getAttribute(USER_INFO_STATE);
    User currentUser = (User) user;
    if (currentUser == null) {
      throw new BusinessException(HttpRespCode.BAD_REQUEST, "未登录");
    }
    User DbUser = this.getById(currentUser.getId());
    User safetyUser = getSafetyUser(DbUser);
    return safetyUser;
  }

  /**
   * 用户注销
   *
   * @param request
   * @return
   */
  @Override
  public Integer userLogout(HttpServletRequest request) {
    request.getSession().removeAttribute(USER_INFO_STATE);
    return 1;
  }

  private User getSafetyUser(User dbUser) {
    if (dbUser == null) {
      throw new BusinessException(HttpRespCode.BAD_REQUEST, "用户不存在");
    }
    User safeUser = new User();
    BeanUtils.copyProperties(dbUser, safeUser);
    safeUser.setUserStatus(null);
    safeUser.setCreateTime(null);
    safeUser.setIsDelete(null);
    safeUser.setUpdateTime(null);
    return safeUser;
  }
}
