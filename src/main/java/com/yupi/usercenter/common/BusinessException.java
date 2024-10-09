package com.yupi.usercenter.common;

import com.yupi.usercenter.constant.HttpRespCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** 统一返回结果异常类 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {
  private final String description;
  private final String resCode;

  public BusinessException(String resCode, String message, String description) {
    super(message);
    this.description = description;
    this.resCode = resCode;
  }

  public BusinessException(HttpRespCode resCode, String description) {
    super(resCode.getText());
    this.description = description;
    this.resCode = resCode.getCode();
  }
}
