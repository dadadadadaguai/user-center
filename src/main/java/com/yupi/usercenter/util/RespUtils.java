package com.yupi.usercenter.util;

import com.yupi.usercenter.common.Resp;
import com.yupi.usercenter.constant.HttpRespCode;

/** 响应工具类 */
public class RespUtils {
  public static <T> Resp<T> success(T data) {
    return new Resp<>(HttpRespCode.SUCCESS, "请求成功", data);
  }
}
