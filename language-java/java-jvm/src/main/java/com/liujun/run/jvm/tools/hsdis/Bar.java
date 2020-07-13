package com.liujun.run.jvm.tools.hsdis;

/**
 * 进行jit生成代码反汇编的简单java代码
 *
 * <p>由于是window环境，需要将hsdis-amd64.dll复制到\jdk1.8.0_241\jre\bin\server目录下
 *
 * <p>使用JVM参数
 *
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Xcomp -XX:CompileCommand=dotinline,*Bar.sum -XX:CompileCommand=compileonly,*Bar,sum
 *
 *
 * 如果使用jitwatch工具查看使用JVM参数
 *
 * -XX:+UnlockDiagnosticVMOptions -XX:+TraceClassLoading -XX:+LogCompilation -XX:LogFile=bar_hsdis_out.log -XX:+PrintAssembly -XX:+TraceClassLoading
 *
 * @author liujun
 * @version 0.0.1
 */
public class Bar {

  int a = 1;

  static int b = 2;

  public int sum(int c) {
    return a + b + c;
  }

  public static void main(String[] args) {
    new Bar().sum(3);
  }
}
