package com.liujun.element.errorfile;

import com.liujun.element.errorfile.bean.ScheduleDataEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author liujun
 * @version 0.0.1
 */
@Slf4j
public class ScheduleTaskSave implements Runnable {

  /** 任务调度的队列 */
  private static final PriorityBlockingQueue<ScheduleDataEntity> QUEUE =
      new PriorityBlockingQueue();

  private static AtomicBoolean runFlag = new AtomicBoolean(true);

  private static AtomicBoolean stopFlag = new AtomicBoolean(false);

  /** 实例对象 */
  public static final ScheduleTaskSave INSTANCE = new ScheduleTaskSave();

  /**
   * 注册监控,需要手动设置监控时间
   *
   * @param scheduleData 定时任务对象
   * @return
   */
  public static boolean registerSchedule(ScheduleDataEntity scheduleData) {
    // 将任务加入队列中
    return QUEUE.offer(scheduleData);
  }

  public static void shutdown() {
    System.out.println("schedule task shutdown start ");
    runFlag.set(false);
    int runIndex = 0;
    // 检查当前是否已经停止，未停止则等2秒后再继续
    while (!stopFlag.get()) {
      try {
        Thread.sleep(5000L);
        runIndex++;
      } catch (InterruptedException e) {
        e.printStackTrace();
        log.error("shutdown sleep InterruptedException:", e);
      }
      System.out.println("schedule task shutdown sleep 5000 , num " + runIndex);
    }

    System.out.println("schedule shutdown finish");
  }

  @Override
  public void run() {
    // 检查任务是否做退出操作
    while (runFlag.get()) {
      ScheduleDataEntity scheduleDataEntity = QUEUE.poll();
      if (null != scheduleDataEntity) {
        try {
          // 等待触发
          threadWait(scheduleDataEntity.getRunTime());
          // 任务执行
          scheduleDataEntity.getRunObject().run();
          // 更新更新时间
          ScheduleDataEntity nextData = runNextTime(scheduleDataEntity);
          // 注册加入队列
          registerSchedule(nextData);
        } catch (Exception e) {
          e.printStackTrace();
          log.error("run schedule exception:", e);
        }
      } else {
        try {
          log.info("curr schedule is null run wait ");
          Thread.sleep(2000L);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
    stopFlag.set(true);
  }

  private ScheduleDataEntity runNextTime(ScheduleDataEntity scheduleDataEntity) {
    int rateTime = scheduleDataEntity.getRateTime();
    long nextTime = System.currentTimeMillis() + rateTime;
    scheduleDataEntity.setRunTime(nextTime);
    return scheduleDataEntity;
  }

  /**
   * 执行线程的等待操作
   *
   * @param cycleRunTime 运行的周期操作
   */
  private void threadWait(long cycleRunTime) {

    while (true) {
      // 获得与当前时间的差异
      long curTime = System.currentTimeMillis();
      long runTime = cycleRunTime;

      // 获取时间差
      long timeCountVal = runTime - curTime;

      if (timeCountVal > 0) {
        try {
          Thread.sleep(timeCountVal);
        } catch (InterruptedException e) {
          e.printStackTrace();
          log.error("thread sleep InterruptedException:", e);
        }
      } else {
        break;
      }
    }
  }
}
