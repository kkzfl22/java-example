package com.liujun.algorithm.ahoCorasick;

import com.liujun.algorithm.ahoCorasick.pojo.MatcherBusi;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * ac自动机算法，只用于数字的匹配操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/08
 */
public abstract class AhoCorasick {

  /** ac自动机的大小 */
  private final int AC_SIZE = getAcSize();

  /** 模式 */
  protected final Pattern pattern;

  public enum Pattern {
    /** 默认 */
    DEFAULT,
    /** 不区分大小写模式 */
    IGNORE_CASE;
  }

  public AhoCorasick() {
    this.pattern = Pattern.DEFAULT;
  }

  public AhoCorasick(Pattern pattern) {
    this.pattern = pattern;
  }

  /**
   * 获取当前ac自动机的字符集大小
   *
   * @return
   */
  protected abstract int getAcSize();

  /** 根节点 */
  private AcNode root = new AcNode('/');

  /**
   * 批量添加多模式串
   *
   * @param arrays 多模式串集合
   */
  public void insert(List<String> arrays) {

    for (String key : arrays) {
      this.insert(key);
    }
  }

  /**
   * 在tree树中插入字符
   *
   * @param src
   */
  public void insert(String src) {

    AcNode tmpRoot = root;

    char[] srcArrays = src.toCharArray();

    int index = 0;

    for (int i = 0; i < srcArrays.length; i++) {
      // 如果存在特殊字符串中，优先使用特殊字符
      index = getIndex(srcArrays[i]);

      // 如果当前子未存储数据,则直接存储
      if (tmpRoot.childred[index] == null) {
        tmpRoot.childred[index] = new AcNode(srcArrays[i]);
      }
      // 当前节点垮了指向子节点
      tmpRoot = tmpRoot.childred[index];
    }
    // 最后一个节点，需要标识为结束节点
    tmpRoot.isEndingChar = true;
    tmpRoot.length = srcArrays.length;
    tmpRoot.srcData = src;
  }

  /** 构建失败指针 */
  public void builderFailurePointer() {
    Queue<AcNode> queue = new LinkedList<>();

    root.fail = null;
    // 1,首先放入root节点
    queue.add(root);

    while (!queue.isEmpty()) {
      AcNode qItemTmp = queue.remove();

      // 进行遍历当前节点下的子节点，进行失败指针的指向
      for (int i = 0; i < AC_SIZE; i++) {
        AcNode qchildTmp = qItemTmp.childred[i];
        // 如果当前节点未存储，则跳过
        if (qchildTmp == null) {
          continue;
        }

        // 存储了数据，则检查是否为root节点
        if (qItemTmp == root) {
          qchildTmp.fail = root;
        }
        // 其他节点则需要进行遍历指向
        else {
          // 取得当前子节点的失败指针
          AcNode qFailTemp = qItemTmp.fail;

          while (qFailTemp != null) {
            // 1,在上一级失败指针中查找，看能否找到指向当前节点的指针
            int index = this.getIndex(qchildTmp.data);
            AcNode topFailTmp = qFailTemp.childred[index];

            if (topFailTmp != null) {
              qchildTmp.fail = topFailTmp;
              break;
            }
            // 如果为空，则再从上一级节点查找,一直查找到根节点为止
            qFailTemp = qFailTemp.fail;
          }

          // 如果当前未找到，则直接指向root节点
          if (qFailTemp == null) {
            qchildTmp.fail = root;
          }
        }

        queue.add(qchildTmp);
      }
    }
  }

  /**
   * 获取索引节点
   *
   * @param srcArray
   * @return
   */
  public abstract int getIndex(char srcArray);

  /**
   * 添加模式串，并构建失败指针
   *
   * @param list
   */
  public void buildFailure(List<String> list) {

    for (String acMode : list) {
      this.insert(acMode);
    }

    // 构建失败指针
    this.builderFailurePointer();
  }

  /**
   * 进行字符的匹配操作,进行多次字符匹配操作
   *
   * @param mainChar 主串信息
   * @param startIndex 开始的位置
   * @return 匹配的对象信息
   */
  public MatcherBusi matcherOne(char[] mainChar, int startIndex) {

    MatcherBusi matcherBusi = new MatcherBusi();

    AcNode pmatch = root;

    // 进行主串遍历
    for (int i = startIndex; i < mainChar.length; i++) {
      int index = this.getIndex(mainChar[i]);

      if (index >= AC_SIZE || index == -1) {
        continue;
      }

      // 失败指针的检查,如果当前字符的下一个字符不能被找到，并且不是根节点
      while (pmatch.childred[index] == null && pmatch != root) {
        pmatch = pmatch.fail;
      }

      // 获取当前字符在失败指针中的位置
      pmatch = pmatch.childred[index];

      // 如果不能被找到，则重新从root节点开始匹配
      if (pmatch == null) {
        pmatch = root;
      }

      AcNode tmpMatch = pmatch;

      while (tmpMatch != root) {
        // 如果当前能够被匹配成功，则返回匹配的字符串信息,并结束
        if (tmpMatch.isEndingChar == true) {
          matcherBusi.setMatcherKey(tmpMatch.srcData);
          matcherBusi.setMatcherIndex(i - tmpMatch.length + 1);
          return matcherBusi;
        }
        tmpMatch = tmpMatch.fail;
      }
    }

    return matcherBusi;
  }

  /**
   * 进行字符的匹配操作,进行多次字符匹配操作,并且忽略大小写
   *
   * @param mainChar 主串信息
   * @param startIndex 开始的位置
   * @return 匹配的对象信息
   */
  public MatcherBusi matcherIgnoreCaseOne(char[] mainChar, int startIndex) {

    MatcherBusi matcherBusi = new MatcherBusi();

    AcNode pmatch = root;

    // 进行主串遍历
    for (int i = startIndex; i < mainChar.length; i++) {
      // 获取字符的asc码索引索引位置，当为大写时，转换为小写
      int index = this.getIndex(mainChar[i]);

      if (index >= AC_SIZE || index <= -1) {
        continue;
      }

      // 失败指针的检查,如果当前字符的下一个字符不能被找到，并且不是根节点
      while (pmatch.childred[index] == null && pmatch != root) {
        pmatch = pmatch.fail;
      }

      // 获取当前字符在失败指针中的位置
      pmatch = pmatch.childred[index];

      // 如果不能被找到，则重新从root节点开始匹配
      if (pmatch == null) {
        pmatch = root;
      }

      AcNode tmpMatch = pmatch;

      while (tmpMatch != root) {
        // 如果当前能够被匹配成功，则返回匹配的字符串信息,并结束
        if (tmpMatch.isEndingChar == true) {
          matcherBusi.setMatcherIndex(i - tmpMatch.length + 1);
          matcherBusi.setMatcherKey(tmpMatch.srcData);
          return matcherBusi;
        }
        tmpMatch = tmpMatch.fail;
      }
    }

    return matcherBusi;
  }

  /**
   * 进行字符的匹配操作,进行多次字符匹配操作,返回索引位置下标
   *
   * <p>使用此方使须采用相同模式串长度，否则返回的对象具有不确定性，可能是任务中间的一个
   *
   * <p>即方法多次调用
   *
   * @param mainChar 匹配的主串信息
   * @param startPostion 开始的索引位置
   */
  public int matcherIndex(char[] mainChar, int startPostion) {

    AcNode pmatch = root;

    // 进行主串遍历
    for (int i = startPostion; i < mainChar.length; i++) {
      int index = this.getIndex(mainChar[i]);

      if (index >= AC_SIZE || index == -1) {
        continue;
      }

      // 失败指针的检查,如果当前字符的下一个字符不能被找到，并且不是根节点
      while (pmatch.childred[index] == null && pmatch != root) {
        pmatch = pmatch.fail;
      }

      // 获取当前字符在失败指针中的位置
      pmatch = pmatch.childred[index];

      // 如果不能被找到，则重新从root节点开始匹配
      if (pmatch == null) {
        pmatch = root;
      }

      AcNode tmpMatch = pmatch;

      while (tmpMatch != root) {
        // 如果当前能够被匹配成功，则返回匹配的字符串信息,并结束
        if (tmpMatch.isEndingChar == true) {
          int matPostion = i - tmpMatch.length + 1;
          return matPostion;
        }
        tmpMatch = tmpMatch.fail;
      }
    }

    return -1;
  }

  /** trie 树的节点信息 */
  public class AcNode {

    /** 存储的数据 */
    public char data;

    /** 存储所有数字，以及一个符号，符号被转义 */
    public AcNode[] childred = new AcNode[AC_SIZE];

    /** 是否为结束字符 */
    public boolean isEndingChar = false;

    /** 当isEndingChar为true时记录下模式串的长度 */
    public int length = -1;

    /** 原始数据 */
    public String srcData;

    /** 失败指针 */
    public AcNode fail;

    public AcNode(char data) {
      this.data = data;
    }
  }
}
