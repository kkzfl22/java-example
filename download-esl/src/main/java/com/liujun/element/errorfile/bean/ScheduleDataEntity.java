package com.liujun.element.errorfile.bean;

import lombok.Data;

/**
 * 定时调度的实体
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class ScheduleDataEntity implements Comparable<ScheduleDataEntity> {

  /** 下次运行的时间 */
  private long runTime;

  /** 间隔时间,以毫秒为单位 */
  private int rateTime;

  /** 需要运行的对象 */
  private Runnable runObject;

  @Override
  public int compareTo(ScheduleDataEntity o) {

    // 使用小顶堆，则按从小到大
    if (this.runTime > o.runTime) {
      return 1;
    } else if (this.runTime < o.runTime) {
      return -1;
    }
    return 0;
  }
}
