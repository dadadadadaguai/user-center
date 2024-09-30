package com.yupi.usercenter.service.impl;

import static com.sun.javafx.font.FontResource.SALT;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.usercenter.mapper.UserMapper;
import com.yupi.usercenter.model.domain.User;
import com.yupi.usercenter.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
  public long userRegister(String userAccount, String userPassword, String checkPassword) {
    // 校验
    if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
      return -1;
    }
    int len = userAccount.length();
    if (len < 4) {
      return -1;
    }
    if (userPassword.length() < 8 || checkPassword.length() < 8) {
      return -1;
    }
    if (!checkPassword.equals(checkPassword)) {
      return -1;
    }
    // 校验账号是否含有不合法字符
    String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    if (userAccount.matches(validPattern)) {
      return -1;
    }
    // 密码加密
    String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
    // 查询账号是否重复
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    QueryWrapper<User> DbUser = queryWrapper.eq("userAccount", userAccount);
    if (this.count(DbUser) > 0) {
      return -1;
    }
    // 插入数据
    User user = User.builder().userAccount(userAccount).userPassword(encryptPassword).build();
    boolean saveResult = this.save(user);
    if (!saveResult) {
      return -1;
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
      return null;
    }
    if (userPassword.length() < 8) {
      return null;
    }
    // 校验账号是否含有不合法字符
    String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    if (userAccount.matches(validPattern)) {
      return null;
    }
    String Md5Password = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
    // 查询
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("userAccount", userAccount);
    queryWrapper.eq("userPassword", Md5Password);
    User dbUser = this.getOne(queryWrapper);
    if (dbUser == null) {
      return null;
    }
    // 脱敏
    dbUser = getSafetyUser(dbUser);
    request.getSession().setAttribute(USER_INFO_STATE, dbUser);
    return dbUser;
  }

  private User getSafetyUser(User dbUser) {
    User safeUser = new User();
    BeanUtils.copyProperties(dbUser, safeUser);
    safeUser.setUserStatus(null);
    safeUser.setCreateTime(null);
    safeUser.setIsDelete(null);
    safeUser.setUpdateTime(null);
    return safeUser;
  }
}
