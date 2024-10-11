package com.yupi.usercenter.model.request;

import java.io.Serializable;
import lombok.Data;

@Data
public class UserRegisterRequest implements Serializable {
  private static final long serialVersionUID = 3191241716373120793L;
  String userAccount;
  String userPassword;
  String checkPassword;
  String plantCode;
}
