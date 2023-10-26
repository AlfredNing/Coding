package com.nq.flowable.flowable;

import javax.annotation.Resource;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlowableApplicationTests {
  @Resource
  private ProcessEngine processEngine;

  @Test
  void contextLoads() {
    System.out.println(processEngine);
  }

}
