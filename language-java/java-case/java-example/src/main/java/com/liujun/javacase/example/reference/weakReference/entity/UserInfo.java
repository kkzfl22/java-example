package com.liujun.javacase.example.reference.weakReference.entity;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * 用户信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
@ToString
public class UserInfo {

  private String userId;

  private String userName;

  private String userPassword;

  public static UserInfo getRandomInstance() {
    UserInfo userInfo = new UserInfo();
    userInfo.setUserId(RandomStringUtils.randomAlphanumeric(10));
    userInfo.setUserName(RandomStringUtils.randomAlphanumeric(20));
    userInfo.setUserPassword(RandomStringUtils.randomAlphanumeric(20));
    return userInfo;
  }
}
