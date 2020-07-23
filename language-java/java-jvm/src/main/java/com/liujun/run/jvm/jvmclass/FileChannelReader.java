package com.liujun.run.jvm.jvmclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liujun
 * @version 0.0.1
 */
public class FileChannelReader {

  private FileInputStream inputStream;

  private FileChannel channel;

  private ByteBuffer buffer;

  /**
   * 获取实例对象
   *
   * @param path 路径信息
   * @return 文件操作
   */
  public static FileChannelReader getInstance(String path) {
    FileChannelReader channelInstance = new FileChannelReader();
    channelInstance.open(path);
    return channelInstance;
  }

  /**
   * 打开文件通道
   *
   * @param path
   * @return
   */
  private FileChannel open(String path) {
    try {
      URL fileUrl = FileChannelReader.class.getClassLoader().getResource(path);

      inputStream = new FileInputStream(fileUrl.getPath());
      channel = inputStream.getChannel();
      int size = (int) channel.size();
      buffer = ByteBuffer.allocate(size);
      // 首次的数据读取
      channel.read(buffer);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return channel;
  }

  public ByteBuffer getBuffer() {
    // 模式转换，将写模式转换为读模式
    buffer.flip();
    return buffer;
  }

  private void close() {
    if (null != channel) {
      try {
        channel.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    if (null != inputStream) {
      try {
        inputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
