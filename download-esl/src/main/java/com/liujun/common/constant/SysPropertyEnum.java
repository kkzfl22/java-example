package com.liujun.common.constant;

/**
 * 属性文件的枚举对象
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/01
 */
public enum SysPropertyEnum {

  /** 文件操作的基础路径 */
  FILE_PROCESS_PATH("file.download.path"),

  /** 网页连接的最大连接 */
  HTTP_POOL_MAXTOTAL("http.pool.maxtotal"),

  /** 设置设置每个路由上的默认连接个数 */
  HTTP_MAX_DEFAULT_PERROUTE("http.max.default.perroute"),

  /** 网页响应的socket超时 */
  HTTP_SOCKETTIMEOUT("http.sockettimout"),

  /** 网页连接响应超时 */
  HTTP_CONN_TIMEOUT("http.conn.timeout"),

  /** 网页请求的超时设置 */
  HTTP_CONN_REQ_TIMEOUT("http.conn.req.timeout"),

  /** 存储源文件的后缀名 */
  HTML_SRC_SUFFIX_NAME("html.src.suffix.name"),

  /** 存储转码后的文件后缀名 */
  HTML_PROC_SUFFIX_NAME("html.proc.suffix.name"),

  /** 网页下载配制 */
  HTML_DOWN_RETRY_CFG("file.retry.num"),
  ;

  private String key;

  SysPropertyEnum(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
