package com.nq.activity;

import java.util.List;
import javax.annotation.Resource;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Alfred.Ning
 * @since 2024年01月09日 06:44:00
 */
public class ProcessEngineTest {

  @Resource
  private TaskService taskService;

  @DisplayName("引擎测试")
  @Test
  public void test1() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    System.out.println(processEngine);
  }

  @Test
  public void test2() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

  }

  @DisplayName("部署流程")
  @Test
  public void test3() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    RepositoryService repositoryService = processEngine.getRepositoryService();
    Deployment deployment = repositoryService.createDeployment().addClasspathResource("flow/test01.bpmn20.xml").deploy();
    System.out.println(deployment.getId());
    System.out.println(deployment.getName());
  }


  @DisplayName("发器流程")
  @Test
  public void test4() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    RuntimeService runtimeService = processEngine.getRuntimeService();
    // 流程ID启动
    ProcessInstance processInstance = runtimeService.startProcessInstanceById("test01:1:3");
    System.out.println(processInstance.getId());
    System.out.println(processInstance.getDeploymentId());
    System.out.println(processInstance.getDescription());
  }

  @DisplayName("待办任务查询")
  @Test
  public void test5() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    TaskService taskService = processEngine.getTaskService();
    List<Task> list = taskService.createTaskQuery().taskAssignee("lisi").list();
    for (Task task : list) {
      System.out.println(task.getName());
      System.out.println(task.getId());
    }
  }

  @DisplayName("任务审批")
  @Test
  public void test6() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    TaskService taskService = processEngine.getTaskService();
    taskService.complete("2505");
  }
}
