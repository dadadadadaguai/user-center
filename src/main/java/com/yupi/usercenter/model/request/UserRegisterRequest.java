package com.yupi.usercenter.model.request;

import com.sun.istack.internal.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {
  private static final long serialVersionUID = 3191241716373120793L;
  @NotNull String userAccount;
  @NotNull String userPassword;
  @NotNull String checkPassword;
  @NotNull String plantCode;
}
