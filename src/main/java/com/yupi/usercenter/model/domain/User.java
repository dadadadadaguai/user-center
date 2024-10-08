package com.yupi.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 用户表 @TableName user */
@TableName(value = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
  /** 用户id */
  @TableId(type = IdType.AUTO)
  private Long id;

  /** 用户名 */
  private String username;

  /** 用户账号 */
  private String userAccount;

  /** 用户头像 */
  private String avatarUrl;

  /** 性别 */
  private Integer gender;

  /** 用户密码 */
  private String userPassword;

  /** 手机号码 */
  private String phone;

  /** 邮箱 */
  private String email;

  /** 状态(0正常) */
  private Integer userStatus;

  /** 创建时间 */
  private Date createTime;

  /** 更新时间 */
  private Date updateTime;

  /** 是否删除 */
  @TableLogic private Integer isDelete;

  /** 角色(0用户，1管理员) */
  private Integer userRole;

  /** 编号 (最多4位编号) */
  private String plantCode;

  @TableField(exist = false)
  private static final long serialVersionUID = 1L;
}
