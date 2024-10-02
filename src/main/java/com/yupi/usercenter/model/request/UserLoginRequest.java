package com.yupi.usercenter.model.request;

import com.sun.istack.internal.NotNull;
import java.io.Serializable;
import lombok.Data;

@Data
public class UserLoginRequest implements Serializable {
  private static final long serialVersionUID = 3191241716373120793L;
  @NotNull
  String userAccount;
  @NotNull
  String userPassword;
}
