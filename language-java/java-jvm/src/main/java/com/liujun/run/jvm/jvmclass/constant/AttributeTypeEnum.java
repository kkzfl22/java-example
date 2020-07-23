package com.liujun.run.jvm.jvmclass.constant;

/**
 * 属性类型的枚举
 *
 * @author liujun
 * @version 0.0.1
 */
public enum AttributeTypeEnum {
  /** java代码编译成的字节码指令 */
  CODE("Code"),

  /** 由final关键字定义的的常量值 */
  CONSTANT_VALUE("ConstantValue"),

  /** 被声明为deprecated的方法和字段 */
  DEPRECATED("Deprecated"),

  /** 方法抛出的异常列表 */
  EXCEPTIONS("Exceptions"),

  /** 仅当一个类为局部类或者匿名类时才能拥有这个属性，这个属性用于指示这个类所在外围方法 */
  ENCLOSING_METHOD("EnclosingMethod"),

  /** 内部类列表 */
  INNER_CLASSES("InnerClasses"),

  /** java源码的行号与字节码指令对应关系 */
  LINENUMBER_TABLE("LineNumberTable"),

  /** 方法的局部变量描述 */
  LOCAL_VARIABLE_TABLE("localVariableTable"),

  /** JDK6中新增的属性，供新的类型检查验证器(Type Checker)检查和处理目标方法的局部变量和操作数栈所需要的类型是否匹配。 */
  STACK_MAP_TABLE("StackMapTable"),

  /**
   * JDK5中新增加，用于支持范围情况下的方法签名。在java语言中，任何类、接口、初始化方法或成员的泛型签名如果包含了类型变量（Type Variables)
   * 或者参数化类型(Parameterized Type)则Signature属性会记录字泛型签名信息
   */
  SIGNATURE("Signature"),

  /** 记录源文件名称 */
  SOURCE_FILE("SourceFile"),

  /** jdk5新增，用于存储额外的调度信息，例如jsp文件。无法通过java堆来定位jsp中的行号 */
  SOURCE_DEBUG_EXTENSION("SourceDebugExtension"),

  /** 标识方法或者字段，编译器自动生成 */
  SYNTHETIC("Synthetic"),

  /** jdk5新增属性，它使用特征描述符代替描述符,是为了引入泛型语法之后能描述泛型参数化类型而添加 */
  LOCAL_VARIABLE_TYPE_TABLE("LocalVariableTypeTable"),

  /** jdk5中新增加的属性，为动态注解提供支持。该属性用于指明哪些属性是运行时可见的 */
  RUNTIME_VISIBLE_ANNOTATIONS("RuntimeVisibleAnnotations"),

  /** JDK5中新增的属性，与RuntimeVisibleAnnotation属性作用刚好相反。用于指明哪些注解是运行行时不可见的 */
  RUNTIME_INVISIBLE_ANNOTATIONS("RuntimeInvisibleAnnotations"),

  /** JDK5中新增加属性，作用与RuntimeVisibleAnnotations属性类似，作用对象为方法参数 */
  RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS("RuntimeVisibleParameterAnnotations"),

  /** JDK5中新加属性，作用与RuntimeInvisibleAnnotations类似，作用为方法参数。 */
  RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS("RuntimeInvisibleParameterAnnotations"),

  /** jdk5中新增加，用于记录注解类元素的默认值 */
  ANNOTATION_DEFAULT("AnnotationDefault"),

  /** jdk7新增属性，用于保存InvokeDynamic指令引用的引导方法限定符 */
  BOOTSTRAP_METHODS("BootstrapMethods"),

  /** jdk8中新增属性，为实现jsr308中新增的类型注解提供的支持。用于指明哪些类注解是运行时可见的 */
  RUNTIME_VISIBLE_TYPE_ANNOTATIONS("RuntimeVisibleTypeAnnotations"),

  /** jdk8中新增的属性，为实现jsr308中的新增的类型注解提供支持，与RuntimeVisibleTypeAnnotation属性作用刚好相反，用于指明哪些注解是运行时不可见的 */
  RUNTIME_INVISIBLE_TYPE_ANNOTATIONS("RuntimeInvisibleTypeAnnotations"),

  /** jdk8新增的属性，用于支持（编译时加上-parameters参数）将方法名编译进Class文件中，并可运行时获取。此前获取方法名称只能通过JavaDoc中得到 */
  METHOD_PARAMETERS("MethodParameters"),

  /** jdk9新增的属性，用于记录一个module的名称以及相关信息（requires、exports、opens、user、provides) */
  MODULE("Module"),

  /** jdk9新增的属性，用于记录一个模块中所有被exports或者opens的包 */
  MODULE_PACKAGES("ModulePackages"),

  /** jdk9新增加的属性，用于指定一个模块的主类。 */
  MODULE_MAIN_CLASS("ModuleMainClass"),

  /** jdk11中新增的属性、用于支持嵌套的反射和访问控制API,一个内部类通过该属性得知自己的宿主类 */
  NES_HOST("NesHost"),

  /** JDK11中新增的属性，用于支持嵌套的反射和访问控制的API，一个宿主类通过该属性得知自己有哪些内部类 */
  NEST_MEMBERS("NesMembers"),
  ;

  /** 类型信息 */
  private String type;

  AttributeTypeEnum(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  /**
   * 通过名称获得枚举类型信息
   *
   * @param name
   * @return
   */
  public static AttributeTypeEnum getType(String name) {
    AttributeTypeEnum result = null;

    for (AttributeTypeEnum attributeInfo : values()) {
      if (attributeInfo.getType().equals(name)) {
        result = attributeInfo;
        break;
      }
    }

    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("AttributeTypeEnum{");
    sb.append("type='").append(type).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
