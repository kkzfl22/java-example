package com.liujun.run.jvm.jvmclass.bean;

import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeBase;
import com.liujun.run.jvm.jvmclass.bean.fieldtable.FieldTable;
import com.liujun.run.jvm.jvmclass.bean.functiontable.FunctionTable;
import com.liujun.run.jvm.jvmclass.constant.AccessFlagsEnum;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;

/**
 * java的字节码对象实体
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
@Builder
public class JavaClassEntity {

  /** 魔数，cafebabe,用于进行标识当前文件参被JVM虚拟机所接受的标识 */
  private int magicNumber;

  /** 次版本号 */
  private short minorVersion;

  /** 主版本号 ,52为jdk8的版本号 */
  private short majorVersion;

  /** 常量池 */
  private ConstantPool constantPool;

  /** 访问修饰符 */
  private short accessFlag;

  /** 类索引 */
  private short thisClassIndex;

  /** 父类索引 */
  private short superClassIndex;

  /** 实现的接口数组 */
  private InterfaceArrayEntity interfaceArray;

  /** 字段表信息,类属性成员 */
  private FieldTable fieldTable;

  /** 方法表信息 */
  private FunctionTable functionTable;

  /** 与源文件的关联信息 */
  private AttributeBase sourceFile;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("JavaClassBean{");
    sb.append("magicNumber=").append(Integer.toHexString(magicNumber)).append(",");
    sb.append("minorVersion=").append(Integer.toHexString(minorVersion)).append(",");
    sb.append("majorVersion=").append(majorVersion).append(",");
    sb.append("constantPool=").append(constantPool).append(",");
    sb.append("accessFlag=(hex-->")
        .append(Integer.toHexString(accessFlag))
        .append("),")
        .append(accessFlag)
        .append(",")
        .append("\n");
    sb.append(Arrays.toString(AccessFlagsEnum.accessFlags(accessFlag)));
    sb.append("thisClassIndex=").append(this.thisClassIndex).append(",");
    sb.append("superClassIndex=").append(this.superClassIndex).append(",");
    sb.append("interfaceArray=").append(this.interfaceArray).append(",");
    sb.append("fieldTable=").append(this.fieldTable).append(",");
    sb.append("functionTable=").append(this.functionTable).append(",");
    sb.append('}');
    return sb.toString();
  }
}
