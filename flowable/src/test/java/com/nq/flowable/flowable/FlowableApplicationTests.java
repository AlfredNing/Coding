package com.nq.flowable.flowable;

import javax.annotation.Resource;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlowableApplicationTests {
  @Resource
  private ProcessEngine processEngine;

  @Resource
  private RepositoryService repositoryService;


  @Test
  void contextLoads() {
    System.out.println(processEngine);
  }


  /**
   * 手动部署
   */
  @Test
  void testHandle(){
    Deployment deploy = repositoryService.createDeployment()
        .addClasspathResource("请假流程.bpmn20.xml")
        .name("请假流程手动部署")
        .deploy();

    System.out.println(deploy.getId());
    System.out.println(deploy.getName());

  }

}
