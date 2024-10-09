package com.yupi.usercenter.constant;

import lombok.ToString;

/** 状态码 */
@ToString
public enum HttpRespCode {
  CONTINUE("100", "等待后续请求"),
  SUCCESS("200", "请求成功"),
  ERROR("400", "请求错误"),
  CREATED("201", "创建成功"),
  ACCEPTED("202", "请求已接收，等待处理"),
  NO_CONTENT("204", "请求成功"),
  BAD_REQUEST("400", "语义或请求参数有误"),
  UNAUTHORIZED("401", "用户身份验证失败"),
  PAYMENT_REQUIRED("402", "预留状态码"),
  FORBIDDEN("403", "服务器拒绝执行"),
  NOT_FOUND("404", "请求资源不存在"),
  METHOD_NOT_ALLOWED("405", "请求方法无法响应"),
  REQUEST_TIMEOUT("408", "请求超时"),
  LENGTH_REQUIRED("411", "未找到请求Content-Length头"),
  PRECONDITION_FAILED("412", "先决条件不满足"),
  REQUEST_ENTITY_TOO_LARGE("413", "请求资源过大"),
  UNSUPPORTED_MEDIA_TYPE("415", "提交或请求数据格式不支持"),
  EXPECTATION_FAILED("417", "服务器无法满足预期内容"),
  TOO_MANY_CONNECTIONS("421", "连接用户数过多"),
  UNPROCESSABLE_ENTITY("422", "语义错误"),
  LOCKED("423", "资源被锁定"),
  FAILED_DEPENDENCY("424", "前序请求出错"),
  UNAVAILABLE_FOR_LEGAL_REASONS("451", "该请求因法律原因不可用"),
  INTERNAL_SERVER_ERROR("500", "服务器内部出错"),
  NOT_IMPLEMENT("501", "服务器不支持当前请求"),
  BAD_GATEWAY("502", "网关出错"),
  SERVICE_UNAVAILABLE("503", "临时服务器维护"),
  GATEWAY_TIMEOUT("504", "网关超时"),
  HTTP_VERSION_NOT_SUPPORTED("505", "服务器不支持该http版本"),
  BANDWIDTH_LIMIT_EXCEEDED("509", "服务器达到带宽限制"),
  NOT_EXTENDED("510", "获取资源所需要的策略未满足"),
  UNPARSEABLE_RESPONSE_HEADERS("600", "只返回实体内容"),
  SAVE_SUCCESS("200", "保存成功"),
  UPDATE_SUCCESS("200", "更新成功"),
  ADD_SUCCESS("200", "添加成功"),
  ADD_FAIL("500201", "添加失败"),
  UPDATE_FAIL("500202", "更新失败"),
  DELETE_FAIL("500203", "删除失败"),
  EXECUTE_FAIL("500204", "执行失败"),
  SEND_EMAIL_SUCCESS("200", "邮件已发送到您邮箱"),
  IMAGE_UPLOAD_SUCCESS("200", "图片上传成功"),
  DELETE_SUCCESS("200", "删除成功"),
  OPERATE_SUCCESS("200", "操作成功"),
  COLLECTION_SUCCESS("200", "收藏成功"),
  CANCEL_COLLECTION_SUCCESS("200", "取消收藏成功"),
  ADD_REQUIRE_SUCCESS("200", "需求发布成功"),
  LOGIN_SUCCESS("200", "登录成功"),
  SEND_AUTH_CODE_SUCCESS("200", "验证码发送成功"),
  CLOSE_REQUIRE_SUCCESS("200", "关闭需求成功"),
  PARAM_ERROR("400", "参数不正确或缺少参数!"),
  BRAND_AUTH_ERROR("40001", "没有该品牌的查询权限"),
  CATE_AUTH_ERROR("40001", "没有该品类的查询权限"),
  AUTHENTICATION_FAILURE("401", "用户验证失败"),
  PERMISSION_ERROR("403", "权限不足"),
  PERMISSION_MISSING("40301", "授权丢失"),
  OVER_DATE("500016", "不得超过2年"),
  DOWNLOAD_KEY_ERROR("40319", "下载请求失效，服务器拒绝执行"),
  TA_AUTH_ERROR("40001", "没有该TA人群的查询权限"),
  DONT_SEND_VERIFICATION_CODE_REPEATEDLY("500", "一分钟不要重复发送验证码"),
  SAME_USER_SMS_OVERLOAD("500", "短信发送量超过上线，请稍后再试。"),
  SAME_USER_BRAND_OVERLOAD("500", "短时间里你查看的品牌过度，请稍后再试。"),
  SENT_SMS_FAIL_CODE("500", "短信发送失败"),
  SENT_MAIL_FAIL_CODE("500", "邮件发送失败"),
  HAVE_NOT_MOBILE("500", "目前你还没有验证你的手机号"),
  NO_MORE_POINT("500", "剩余查看报告次数为0，不能查看"),
  INCORRECT_VERIFICATION_CODE("500", "手机验证码不正确"),
  NO_PERMISSION_TO_SHARE_THIS_REPORT("50001", "无权分享此报告"),
  NOT_ENOUGH_INVITATIONS("50002", "没有多余的邀请次数"),
  INVITATION_CODE_HAS_EXPIRED("50003", "邀请码已过期或被使用"),
  INVITATION_CODE_IS_EMPTY("50012", "没有找到你的邀请码"),
  HAVE_THE_MAIL_ADDR("50004", "邮箱已注册"),
  HAVE_THE_MOBIL_NUM("50005", "手机已注册"),
  VERIFY_MAIL_CODE_FAIL("50006", "邮箱码验证失败"),
  VERIFY_MAIL_NOT_ACTIVATE("50007", "验证邮箱未激活"),
  IS_NOT_COMPANY_MAIL_POSTFIX("50008", "请尽可能使用公司邮箱，谢谢！"),
  THIS_IS_BLOCK_MAIL("50009", "此邮箱已被该邮箱用户设为免打扰，因此我们不会发送邮件给该邮箱。"),
  EMPTY_MAIL_ADDR("50010", "邮箱地址不能为空"),
  WRONG_VER_CODE("50011", "验证码不能为空"),
  NOT_FINISH_REGISTER("50012", "你注册还没有完成"),
  INVITATION_ERROR("50013", "您的邀请码已经失效，继续注册将不会拥有内测用户的权益，是否继续注册？"),
  LOST_INVITATION_CODE("50014", "您的邀请码已丢失，继续注册将不会拥有内测用户的权益，是否继续注册？"),
  HAVE_THE_MAIL_ADDR_FOR_ME("500015", "邮箱已激活，请点击下方下方'我已通过电子激活账号'来完成注册"),
  ERROR_DATABASE("500101", "数据查询错误"),
  ERROR_DATABASE_TIMEOUT("500102", "数据库通讯错误"),
  NOT_FOUND_CHART_TYPE("500103", "图标类型不存在"),
  NOT_FOUND_TABLE_TYPE("500104", "表格类型不存在"),
  NOT_FOUND_FOLLOWER("500105", "没有找到你所关注的内容");
  private String code;
  private String text;

  HttpRespCode(String code, String text) {
    this.code = code;
    this.text = text;
  }

  public String getCode() {
    return this.code;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
