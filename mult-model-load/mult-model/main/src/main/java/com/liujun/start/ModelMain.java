package com.liujun.start;

import com.liujun.depend.service.StartService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动加载
 *
 * @author liujun
 * @version 0.0.1
 */
@SpringBootApplication(scanBasePackages = {"com.liujun"})
public class ModelMain {

  public static void main(String[] args) {
    SpringApplication.run(ModelMain.class, args);

    StartService.run();
  }
}
