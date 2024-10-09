package com.yupi.usercenter.common;

import com.yupi.usercenter.constant.HttpRespCode;

public class RunException extends Resp {

  public RunException(HttpRespCode resCode, String description) {
    super(resCode);
  }

  public RunException(String resCode, String resMsg, String description) {
    super(resCode, resMsg,description);
  }

  public RunException(HttpRespCode respCode, Object data) {
    super(respCode, data);
  }
}
