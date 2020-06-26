package com.liujun.algorithm.ahoCorasick;

import com.liujun.algorithm.ahoCorasick.pojo.MatcherBusi;
import com.liujun.constant.TestFileName;
import com.liujun.utils.TestFileUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试AC自动机算法，现在的算法，存在bug，当存在有大小写的字符匹配时，会出现不能匹配的情况
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestAhoCorasickChar {

  /** ac自动机的匹配实例信息开始标签 ,并且当前为不区分大小写的匹配 */
  private static final AhoCorasickChar AC_EXCLUSION_INSTANCE =
      new AhoCorasickChar(AhoCorasick.Pattern.IGNORE_CASE);

  /** href替换后的字符 */
  private static final Map<String, String> REPLACE_DATA_MAP = getReplateMap();

  static {
    // 将多字符匹配的字符器放入ac自动机进行构建
    AC_EXCLUSION_INSTANCE.insert(getReplaceKey());
    // 进行指针的构建
    AC_EXCLUSION_INSTANCE.builderFailurePointer();
  }

  public static Map<String, String> getReplateMap() {
    Map<String, String> dataReplate = new HashMap<>();
    dataReplate.put(">Vocabulary</a>", "></a>");
    dataReplate.put(">Cloze</a>", "></a>");
    dataReplate.put(">Sentences</a>", "></a>");
    dataReplate.put(">Dictation</a>", "></a>");
    dataReplate.put(">MENU</a>", "></a>");
    dataReplate.put("<<script", "<script");
    return dataReplate;
  }

  public static List<String> getReplaceKey() {
    List<String> dataList = new ArrayList<>(REPLACE_DATA_MAP.size());

    for (String dataKey : REPLACE_DATA_MAP.keySet()) {
      dataList.add(dataKey);
    }

    return dataList;
  }

  @Test
  public void matchData() {
    String dataValue = TestFileUtils.getFileContext(TestFileName.TO_THE_ZOO.getFileName());

    char[] dataArrays = dataValue.toCharArray();
    boolean find = true;
    int index = 0;
    while (true) {
      MatcherBusi matchData = AC_EXCLUSION_INSTANCE.matcherIgnoreCaseOne(dataArrays, index);

      if (matchData.getMatcherIndex() != -1) {
        System.out.println(matchData);
        index = matchData.getMatcherIndex() + matchData.getMatcherKey().length();
      } else {
        break;
      }
    }
  }
}
