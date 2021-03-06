package com.liujun.pattern.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * 动态代理类的核心实现
 *
 * @author liujun
 * @date 2015年5月4日
 * @vsersion 0.0.1
 */
public class CglibProxy {

  public Object newProxyInstance(Class infce) throws Exception {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(infce);
    enhancer.setCallback(new TimeHandler());
    return enhancer.create();
  }
}
