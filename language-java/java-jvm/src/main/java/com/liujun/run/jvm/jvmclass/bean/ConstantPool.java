package com.liujun.run.jvm.jvmclass.bean;

import com.liujun.run.jvm.jvmclass.bean.constanttype.ClassEntity;
import com.liujun.run.jvm.jvmclass.bean.constanttype.ConstantBase;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;

/**
 * 常量池
 *
 * <p>常量池紧接着主次版本号之后
 *
 * <p>常量池可以比喻为Class文件里的资源仓库.
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class ConstantPool {

  /** 常量池的容量计数 */
  private final short constantPoolCount;

  /** 常量池中类的信息 */
  private final ConstantBase[] constantPool;

  /**
   * 构建常量池的大小信息
   *
   * @param constantPoolCount
   */
  public ConstantPool(short constantPoolCount) {
    this.constantPoolCount = constantPoolCount;
    this.constantPool = new ConstantBase[constantPoolCount];
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ConstantPool{");
    sb.append("constantPoolCount=hex(")
        .append(Integer.toHexString(constantPoolCount))
        .append(")-->")
        .append(constantPoolCount)
        .append(",")
        .append("constantPool out ")
        .append("\n");

    for (int i = 0; i < constantPool.length; i++) {
      ConstantBase baseInfo = constantPool[i];
      sb.append(i).append("-").append(baseInfo).append(",").append("\n");
    }

    sb.append(",");
    sb.append('}');
    return sb.toString();
  }
}
