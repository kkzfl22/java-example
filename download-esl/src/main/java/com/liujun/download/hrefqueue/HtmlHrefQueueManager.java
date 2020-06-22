package com.liujun.download.hrefqueue;

import com.liujun.common.constant.PathCfg;
import com.liujun.common.utils.CommonIOUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * 队列管理
 *
 * @author liujun
 * @version 0.0.1
 */
@Slf4j
public class HtmlHrefQueueManager {

  /** 实例对象 */
  private HtmlHrefQueue hrefQueue;

  /** 基础路径 */
  private static final String BASE_PATH = PathCfg.BASEPATH + PathCfg.COLLECT_PATH;

  /** 网页链接文件 */
  private static final String FILE_NAME = "html_href_queue.href";

  public static final HtmlHrefQueueManager INSTANCE = new HtmlHrefQueueManager();

  /** 在构建方法中，进行数据加载 */
  public HtmlHrefQueueManager() {
    this.load();
  }

  public HtmlHrefQueue getHrefQueue() {
    return hrefQueue;
  }

  /** 数据加载操作 */
  public void load() {

    String loadFile = BASE_PATH + FILE_NAME;

    // 当文件不存在，则跳过
    File checkFile = new File(loadFile);
    if (!checkFile.exists()) {
      // 当文件不存在时，则进行初始化
      queueInit();
      return;
    }

    InputStream inputStream = null;
    BufferedInputStream bufferedInputStream = null;
    ObjectInputStream objectInput = null;

    try {
      inputStream = new FileInputStream(loadFile);
      // 构建一个带8K缓冲区的流
      bufferedInputStream = new BufferedInputStream(inputStream);
      objectInput = new ObjectInputStream(bufferedInputStream);
      hrefQueue = (HtmlHrefQueue) objectInput.readObject();
    } catch (IOException e) {
      e.printStackTrace();
      log.error("html href queue load IOException:", e);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      log.error("html href queue load ClassNotFoundException:", e);
    } finally {
      CommonIOUtils.close(objectInput);
      CommonIOUtils.close(bufferedInputStream);
      CommonIOUtils.close(inputStream);
    }
  }

  private void existsAndMake(String filePath) {
    File fileExists = new File(filePath);

    // 进行目录创建操作
    if (!fileExists.exists()) {
      fileExists.mkdirs();
    }
  }

  private void queueInit() {
    if (null == hrefQueue) {
      hrefQueue = new HtmlHrefQueue();
    }
  }

  /** 进行队列数据的保存操作 */
  public void save() {
    OutputStream output = null;
    BufferedOutputStream bufferedOutput = null;
    ObjectOutputStream objectOutput = null;

    try {
      queueInit();
      // 基础的路径创建操作
      existsAndMake(BASE_PATH);
      output = new FileOutputStream(BASE_PATH + FILE_NAME);
      bufferedOutput = new BufferedOutputStream(output);
      objectOutput = new ObjectOutputStream(bufferedOutput);
      objectOutput.writeObject(hrefQueue);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      log.error("html href queue save FileNotFoundException:", e);
    } catch (IOException e) {
      e.printStackTrace();
      log.error("html href queue save IOException:", e);
    } finally {
      CommonIOUtils.close(objectOutput);
      CommonIOUtils.close(bufferedOutput);
      CommonIOUtils.close(output);
    }
  }
}
