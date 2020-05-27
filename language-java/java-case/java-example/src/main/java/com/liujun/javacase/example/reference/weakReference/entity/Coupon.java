package com.liujun.javacase.example.reference.weakReference.entity;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * 优惠券信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
@ToString
public class Coupon {

  private String id;

  /** 范围 */
  private int scope;

  /** 折扣 */
  private float discount;

  /** 过期时间 */
  private long expire;

  public static Coupon getRandomInstance() {
    Coupon instance = new Coupon();
    instance.setId(RandomStringUtils.randomAlphanumeric(10));
    instance.setScope(RandomUtils.nextInt());
    instance.setDiscount(RandomUtils.nextFloat());
    instance.setExpire(RandomUtils.nextLong());
    return instance;
  }
}
