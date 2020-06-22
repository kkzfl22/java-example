package com.liujun.element.html.bean;

import com.liujun.common.constant.SymbolMsg;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 网页链接文本相关的信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
@ToString
public class HrefData implements Serializable {

  /** 链接的文本 */
  private String hrefText;

  /** 链接的URL */
  private String hrefUrl;

  /** 相对路径 */
  private List<String> relativePath = new ArrayList<>();

  /** 文件名称 */
  private String fileName;

  public String getRelativePathOut() {
    StringBuilder data = new StringBuilder();

    for (String item : relativePath) {
      data.append(SymbolMsg.PATH).append(item);
    }

    return data.toString();
  }
}
