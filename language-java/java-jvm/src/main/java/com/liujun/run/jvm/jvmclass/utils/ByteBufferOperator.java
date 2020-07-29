package com.liujun.run.jvm.jvmclass.utils;

import java.nio.ByteBuffer;

/**
 * 进行bytebuffer中相关数据读取操作
 *
 * @author liujun
 * @version 0.0.1
 */
public class ByteBufferOperator {

  public static final int FLAG = 0xff;

  private ByteBuffer buffer;

  /**
   * 获取操作实例
   *
   * @param buffer buffer缓冲区
   * @return 操作实例对象
   */
  public static ByteBufferOperator getInstance(ByteBuffer buffer) {
    ByteBufferOperator bufferInstance = new ByteBufferOperator();
    bufferInstance.buffer = buffer;

    return bufferInstance;
  }

  /**
   * 获取u1的数据
   *
   * @return
   */
  public byte readU1() {
    return (byte) (buffer.get() & 0xff);
  }

  /**
   * 读取两字节的数据信息
   *
   * @return 转换后的short信息
   */
  public short readShort() {
    return buffer.getShort();
  }

  public short readU2() {
    byte[] u2Bytes = new byte[2];
    buffer.get(u2Bytes);

    int outU2 = u2Bytes[0] & 0xff;
    outU2 |= (u2Bytes[1] & 0xff) << 8;

    return (short) outU2;
  }

  /**
   * 按顺序读取4个字节的整数,组合成一个int类型数据
   *
   * @return
   */
  public int readInt() {
    return buffer.getInt();
  }

  /**
   * 获取u4的数据
   *
   * @return
   */
  public int readU4() {
    final byte[] b = new byte[4];
    buffer.get(b);
    int position = 0;
    int l = (b[position++] & 0xff);
    l |= (long) (b[position++] & 0xff) << 8;
    l |= (long) (b[position++] & 0xff) << 16;
    l |= (long) (b[position++] & 0xff) << 24;
    return l;
  }

  /**
   * 获取u4的数据
   *
   * @return
   */
  public long readU8() {
    final byte[] b = new byte[8];
    buffer.get(b);
    int position = 0;
    long l = (b[position++] & 0xff);
    l |= (long) (b[position++] & 0xff) << 8;
    l |= (long) (b[position++] & 0xff) << 16;
    l |= (long) (b[position++] & 0xff) << 24;
    l |= (long) (b[position++] & 0xff) << 32;
    l |= (long) (b[position++] & 0xff) << 40;
    l |= (long) (b[position++] & 0xff) << 48;
    l |= (long) (b[position++] & 0xff) << 56;
    return l;
  }

  /**
   * 读取指定长度的数据字节
   *
   * @param length 长度
   * @return 读取的数据信息
   */
  public byte[] readLength(int length) {
    final byte[] dataBuffer = new byte[length];
    buffer.get(dataBuffer);

    return dataBuffer;
  }


}
