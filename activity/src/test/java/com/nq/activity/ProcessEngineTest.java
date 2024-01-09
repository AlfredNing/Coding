package com.nq.activity;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Alfred.Ning
 * @since 2024年01月09日 06:44:00
 */
public class ProcessEngineTest {
  @Test
  public void test1(){
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    System.out.println(processEngine);
  }
}
