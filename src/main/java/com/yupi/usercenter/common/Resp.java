package com.yupi.usercenter.common;

import com.yupi.usercenter.constant.HttpRespCode;
import java.io.Serializable;
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
  private String description;

  public Resp(HttpRespCode resCode, T data) {
    this.resCode = resCode.getCode();
    this.resMsg = resCode.getText();
    this.data = data;
  }

  public Resp(HttpRespCode resCode) {
    this.resCode = resCode.getCode();
    this.resMsg = resCode.getText();
  }

  public Resp(String resCode, String resMsg, String description) {
    this.resCode = resCode;
    this.resMsg = resMsg;
    this.description = description;
  }

  public Resp(String resCode, String description) {
    this.resCode = resCode;
    this.description = description;
  }
}
