package com.liujun.run.jvm.problem.cpu;

import com.liujun.run.jvm.problem.ProblemRunInf;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 高CPU问题的查看
 *
 * @author liujun
 * @version 0.0.1
 */
public class ProbleamHightCPU implements ProblemRunInf {

  public static final ProbleamHightCPU INSTANCE = new ProbleamHightCPU();

  private static final int MAX_POOL = 4;

  private ExecutorService pools = Executors.newFixedThreadPool(MAX_POOL);

  @Override
  public void runInvoke() {
    for (int i = 0; i < MAX_POOL; i++) {
      pools.submit(new RunWork());
    }
  }

  private AtomicInteger countData = new AtomicInteger(0);

  public class RunWork implements Runnable {

    @Override
    public void run() {
      while (true) {
        countData.incrementAndGet();
        countData.compareAndSet(Integer.MAX_VALUE - 100, 0);

        if (countData.get() % 10000 == 0) {
          int nextData = ThreadLocalRandom.current().nextInt(1, 30);
          try {
            Thread.sleep(nextData);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
}
