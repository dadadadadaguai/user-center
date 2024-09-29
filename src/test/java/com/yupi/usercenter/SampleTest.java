package com.yupi.usercenter;

import com.yupi.usercenter.mapper.UserMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SampleTest {

  @Autowired
  private UserMapper userMapper;
}
