package com.liujun.run.jvm.classloader.base;

/**
 * 类初始化的情况
 *
 * 1,通过new, getstatic,putstatic,invokestatic，如果遇到这4条指令，类型没有进行初始化，则需要先过行初始化阶段，
 *
 * 能够执行这4条指令的场景：
 * 1)：遇到new关键字初始化对象的时候。
 * 2)：读取或者设置一个静态类型的字段（初始final修饰，在编译期把结果放入常量池的静态字段除外）的时候
 * 3)：调用一个类型的静态方法的时候。
 *
 * 2,使用java.lang.reflect包的方法对类型进行反射调用的时候，如果类型没有进行初始化过，则需要先触发其初始化。
 *
 * 3，当初始化类的时候，如果发现其父类还没有被初始化，则需要先进行父类的初始化。
 *
 * 4，当虚拟机启动的时候，用户指定一个要执行的主类（包含main方法的类），虚拟机会先初始化这个类
 *
 * 5，当使用jdk7新加入的动态语言支持时，如果一个java.lang.invoke.MethodHandler最后解析的结果为REF_getstatic、REF_putstatic,
 * REF_invokestatic,REF_newinvokeSpecial这四种方式类型的句柄时，并且这个句柄对应的类没有被初始化过，则需要先进行初始化。
 *
 * 6，当一个接口定义了jdk8新加入的默认方法（被default修饰的关键字修饰的方法）时，如果这个类没有被初始化，则需要先进进行初始化。
 *
 * @author liujun
 * @version 0.0.1
 */
public class ClassInit {
}
