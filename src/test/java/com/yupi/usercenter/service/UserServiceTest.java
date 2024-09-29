package com.yupi.usercenter.service;

import static org.junit.jupiter.api.Assertions.*;

import com.yupi.usercenter.model.domain.User;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/** 用户测试 */
@Log4j2
@SpringBootTest
class UserServiceTest {

  @Autowired private UserService userService;

  @Test
  void testAddUser() {
    User user = new User();
    user.setUsername("testyupi");
    user.setUserAccount("123");
    user.setAvatarUrl(
        "https://cn.bing.com/images/search?q=%E5%9B%BE%E7%89%87&FORM=IQFRBA&id=D66BA18EE154D133745ABEF43F0A4AE1418ADA84");
    user.setGender(0);
    user.setUserPassword("123456");
    user.setPhone("123");
    user.setEmail("123");
    boolean result = userService.save(user);
    log.info("save user success, userId: " + user.getId());
    assertTrue(result);
  }

  @Test
  void userRegister() {
    String userAccount = "yupi111";
    String userPassword = "12345666666";
    String checkPassword = "12345666666";
    long result = userService.userRegister(userAccount, userPassword, checkPassword);
    log.info("register result: " + result);
  }
}
