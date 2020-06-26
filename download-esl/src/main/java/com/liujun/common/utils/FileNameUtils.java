package com.liujun.common.utils;

import com.liujun.algorithm.ahoCorasick.AhoCorasickChar;
import com.liujun.algorithm.ahoCorasick.pojo.MatcherBusi;
import com.liujun.common.constant.SymbolMsg;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名的处理
 *
 * @author liujun
 * @version 0.0.1
 */
public class FileNameUtils {

  /** ac自动机的匹配实例信息开始标签 */
  private static final AhoCorasickChar AC_MATCH_NAME_CHAR = new AhoCorasickChar();

  /** 最大的特殊字符 */
  private static final int MAX_RUN_NUM = 240;

  static {
    // 将字符匹配加入AC自动机
    AC_MATCH_NAME_CHAR.buildFailure(getSpecialChar());
    // 构建失败指针
    AC_MATCH_NAME_CHAR.builderFailurePointer();
  }

  /**
   * 进行文件名称的处理
   *
   * @param fileName 文件名
   * @return 针对特殊字符的处理
   */
  public static String fileNameProcess(String fileName) {
    if (StringUtils.isEmpty(fileName)) {
      return SymbolMsg.EMPTY;
    }

    char[] nameData = fileName.toCharArray();

    // 1,将特殊字符进行查询
    nameData = specialProcess(nameData);

    // 2，当大于指定最大值时字符时进行截取
    if (nameData.length > MAX_RUN_NUM) {
      return new String(nameData, 0, MAX_RUN_NUM);
    }

    return new String(nameData);
  }

  /**
   * 名称的特殊处理
   *
   * @param nameData
   * @return
   */
  private static char[] specialProcess(char[] nameData) {

    int runIndex = 0;

    int startIndex = 0;

    while (runIndex < MAX_RUN_NUM) {
      MatcherBusi matchData = AC_MATCH_NAME_CHAR.matcherOne(nameData, startIndex);
      if (matchData.getMatcherIndex() != -1) {

        // 进行清除特殊字符
        nameData = cleanSpecial(nameData, matchData);

        // 指定新的开始位置
        startIndex = matchData.getMatcherIndex();
      } else {
        break;
      }

      runIndex++;
    }

    return nameData;
  }

  /**
   * 清除特殊字符
   *
   * @param srcData
   * @param matchData
   * @return
   */
  private static char[] cleanSpecial(char[] srcData, MatcherBusi matchData) {
    int length = srcData.length - matchData.getMatcherKey().length();
    char[] newChars = new char[length];
    // 1，如果当前查询不为0，则说前部分需要搬移
    if (matchData.getMatcherIndex() > 0) {
      System.arraycopy(srcData, 0, newChars, 0, matchData.getMatcherIndex());
    }
    int afterLength =
        srcData.length - matchData.getMatcherIndex() - matchData.getMatcherKey().length();
    int afterStart = matchData.getMatcherIndex() + matchData.getMatcherKey().length();
    System.arraycopy(srcData, afterStart, newChars, matchData.getMatcherIndex(), afterLength);

    return newChars;
  }

  /**
   * 特殊字符
   *
   * @return
   */
  private static List<String> getSpecialChar() {
    List<String> speciallyName = new ArrayList<>();
    speciallyName.add("<");
    speciallyName.add(">");
    speciallyName.add("/");
    speciallyName.add("\\");
    speciallyName.add("|");
    speciallyName.add(":");
    speciallyName.add("*");
    speciallyName.add("?");
    speciallyName.add("\"");
    return speciallyName;
  }
}
