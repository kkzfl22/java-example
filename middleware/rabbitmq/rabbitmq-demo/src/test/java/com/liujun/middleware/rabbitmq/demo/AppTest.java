package com.liujun.middleware.rabbitmq.demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/** Unit test for simple App. */
public class AppTest {

  @Test
  public void runTTest() {
    int[] a = {1, 5, 6, 9, 20};
    int[] b = {3, 7, 8, 10, 15};
    int[] c = new int[a.length + b.length];
    int x = 0;
    int y = 0;
    int h = 0;
    while (x < a.length && y < b.length) {
      if (a[x] < b[y]) {
        c[h] = a[x];
        h++;
        x++;
      } else if (a[x] > b[y]) {
        c[h] = b[y];
        h++;
        y++;
      }
    }

    for (int elementt : c) {
      System.out.println(elementt);
    }
  }

  public void testRun()
  {
    List<int[]> value = new ArrayList<>();
    value.add(new int[]{10,30,50,66,7,80});
    value.add(new int[]{11,21,31,41,51,61,71,81,91});
    value.add(new int[]{2,12,22,32,42,52,62,72,82,92,102});
    value.add(new int[]{3,13,23,33,43,53,63,73,83});
  }
}
