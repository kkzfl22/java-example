package com.liujun.run.jvm.memory.outofmemory.outof;

import com.liujun.run.jvm.memory.outofmemory.RunOutOfInf;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author liujun
 * @version 0.0.1
 */
public class MethodAreaOutOf implements RunOutOfInf {

  public static final MethodAreaOutOf INSTANCE = new MethodAreaOutOf();

  @Override
  public void runInvoke() {
    while (true) {
      Enhancer enhancer = new Enhancer();
      enhancer.setSuperclass(OOMObject.class);
      enhancer.setUseCache(false);
      enhancer.setCallback(
          new MethodInterceptor() {
            @Override
            public Object intercept(
                Object o, Method method, Object[] objects, MethodProxy methodProxy)
                throws Throwable {
              return methodProxy.invokeSuper(o, objects);
            }
          });
      enhancer.create();
    }
  }

  static class OOMObject {}
}
