package com.liujun.download.hrefqueue;

import org.junit.Assert;
import org.junit.Test;

/**
 * 网页链接管理
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestHtmlHrefQueueManager {

  @Test
  public void load() {
    HtmlHrefQueueManager.INSTANCE.save();
    HtmlHrefQueueManager.INSTANCE.load();
    HtmlHrefQueue queue = HtmlHrefQueueManager.INSTANCE.getHrefQueue();
    Assert.assertNotNull(queue);
  }
}
