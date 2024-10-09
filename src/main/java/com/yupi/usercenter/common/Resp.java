package com.yupi.usercenter.common;

import java.io.Serializable;

import com.yupi.usercenter.constant.HttpRespCode;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应结果
 *
 * @param <T>
 */
@NoArgsConstructor
@Data
public class Resp<T> implements Serializable {
  private static final long serialVersionUID = 1L;
  private String resCode;
  private String resMsg;
  private T data;

  public Resp(HttpRespCode resCode, String resMsg, T data) {
    this.resCode = resCode.getCode();
    this.resMsg = resCode.getText();
    this.data = data;
  }
}
