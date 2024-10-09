package com.yupi.usercenter.common;

import com.yupi.usercenter.constant.HttpRespCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** 全局异常处理器 @Author: dadaguai */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
  @ExceptionHandler(BusinessException.class)
  public Resp handleBusinessException(BusinessException e) {
    log.error("BusinessException:" + e.getDescription(), e);
    return new RunException(e.getResCode(), e.getMessage(), e.getDescription());
  }

  @ExceptionHandler(RuntimeException.class)
  public Resp handleRuntimeException(RuntimeException e) {
    log.error("RuntimeException:" + e.getMessage(), e);
    return new RunException(HttpRespCode.ERROR, e.getMessage());
  }
}
