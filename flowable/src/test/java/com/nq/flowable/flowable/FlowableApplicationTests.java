package com.nq.flowable.flowable;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlowableApplicationTests {

  @Resource
  private ProcessEngine processEngine;

  @Resource
  private RepositoryService repositoryService;

  @Resource
  private RuntimeService runtimeService;

  @Resource
  private TaskService taskService;


  @Test
  void contextLoads() {
    System.out.println(processEngine);
  }


  /**
   * 手动部署
   */
  @Test
  void testHandle() {
    Deployment deploy = repositoryService.createDeployment()
        .addClasspathResource("请假流程.bpmn20.xml")
        .name("请假流程手动部署")
        .deploy();

    System.out.println(deploy.getId());
    System.out.println(deploy.getName());

  }

  @DisplayName("启动流程")
  @Test
  public void testStartProcess() {
    Map<String, Object> map = new HashMap<>();
    map.put("assignee0", "zhangsan");
    map.put("assignee1", "lisi");
    runtimeService.startProcessInstanceById("请假流程:2:c22d438e-7461-11ee-8524-0871908e2604", map);
  }

  @DisplayName("完成流程")
  @Test
  public void testCompleteTask() {
    Task task = taskService.createTaskQuery()
        .processDefinitionId("请假流程:2:c22d438e-7461-11ee-8524-0871908e2604")
        .taskAssignee("zhangsan")
        .singleResult();

    if (task != null) {
      taskService.complete(task.getId());
      System.out.println("complete");
    }
  }
}
