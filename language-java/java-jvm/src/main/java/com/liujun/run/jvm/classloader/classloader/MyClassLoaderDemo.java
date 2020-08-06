package com.liujun.run.jvm.classloader.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义类加载器
 *
 * @author liujun
 * @version 0.0.1
 */
public class MyClassLoaderDemo {

  public static final String CLASS_SUFFIX_NAME = ".class";

  public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    // 自定义类加载器
    ClassLoader mycalssLoader =
        new ClassLoader() {
          @Override
          public Class<?> loadClass(String name) throws ClassNotFoundException {
            try {
              String loadFileName = name.substring(name.lastIndexOf(".") + 1) + CLASS_SUFFIX_NAME;
              // 获取文件流
              InputStream input = getClass().getResourceAsStream(loadFileName);

              // 如果当前类加载不到，则使用父级加载
              if (input == null) {
                return super.loadClass(name);
              }

              byte[] data = new byte[input.available()];
              input.read(data);

              // 执行类加载
              return defineClass(name, data, 0, data.length);

            } catch (IOException e) {
              e.printStackTrace();
            }

            return null;
          }
        };

    // 进行类的加载测试
    Object dataInfo =
        mycalssLoader
            .loadClass("com.liujun.run.jvm.classloader.classloader.MyClassLoaderDemo")
            .newInstance();
    System.out.println(dataInfo.getClass());
    System.out.println(dataInfo instanceof MyClassLoaderDemo);
  }
}
