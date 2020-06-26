package com.liujun.pattern.dynamicproxy.jdk;

import java.lang.reflect.Method;

/**
 * 进行时间的动态代理操作计算时间
 *
 * @author liujun
 * @date 2015年5月4日
 * @vsersion 0.0.1
 */
public class TimeHandler implements InvocationHandler {

  /** 代理的对象 */
  private Object target;

  public TimeHandler(Object target) {
    super();
    this.target = target;
  }

  @Override
  public void invoke(Object o, Method m) {
    long start = System.currentTimeMillis();

    System.out.println(" TimeHandler start time :" + start);

    try {
      m.invoke(target);
    } catch (Exception e) {
      e.printStackTrace();
    }
    long end = System.currentTimeMillis();

	  System.out.println(" TimeHandler end time :" + end);
	  System.out.println(" TimeHandler time difference :" + (end - start) + "(s)");
  }
}
