package com.nq;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentQuery;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 手动部署
 */
public class AppTest {

  ProcessEngineConfiguration cfg;

  @Before
  public void testProcessConfig() {
    cfg = new StandaloneProcessEngineConfiguration()
        .setJdbcUrl("jdbc:mysql://localhost:3307/flowable?serverTimezone=UTC&nullCatalogMeansCurrent=true")
        .setJdbcUsername("root")
        .setJdbcPassword("ningqiang")
        .setJdbcDriver("com.mysql.cj.jdbc.Driver")
        .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
  }

  /**
   * 部署流程
   */
  @Test
  public void testDeploy() {
    ProcessEngine processEngine = cfg.buildProcessEngine();
    RepositoryService repositoryService = processEngine.getRepositoryService();
    Deployment deployment = repositoryService.createDeployment()
        .addClasspathResource("holiday-request.bpmn20.xml")
        .name("请假流程")
        .key("holiday-request")
        .deploy();

    System.out.println(deployment.getId());
    System.out.println(deployment.getName());

  }

  /**
   * 查询流程定义
   */
  @Test
  public void testQueryDeploy() {
    ProcessEngine processEngine = cfg.buildProcessEngine();
    RepositoryService repositoryService = processEngine.getRepositoryService();
    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
        .deploymentId("2501")
        .singleResult();
    System.out.println(processDefinition.getId());
    System.out.println(processDefinition.getName());
    System.out.println(processDefinition.getDeploymentId());
    System.out.println(processDefinition.getDescription());
  }

  /**
   * 删除流程定义
   */
  @Test
  public void testDeleteDeploy() {
    ProcessEngine processEngine = cfg.buildProcessEngine();
    RepositoryService repositoryService = processEngine.getRepositoryService();
    // true:级联删除 流程启动 也可以删除 相关任务也进行删除


//    DeploymentQuery holidayRequest = repositoryService.createDeploymentQuery()
//        .deploymentName("请假流程");
//    for (Deployment deployment : holidayRequest.list()) {
//      repositoryService.deleteDeployment(deployment.getId());
//    }
    repositoryService.deleteDeployment("2501", true);
  }

  /**
   * 启动流程实例
   */
  @Test
  public void testStartProcess() {
    ProcessEngine processEngine = cfg.buildProcessEngine();
    // 7501
    RuntimeService runtimeService = processEngine.getRuntimeService();
    Map<String, Object> variable = new HashMap<>();
    variable.put("employee", "张三");
    variable.put("nrOfHolidays", 3);
    variable.put("description", "累了");
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holidayRequest", variable);
    System.out.println(processInstance.getProcessDefinitionId());
    System.out.println(processInstance.getId());
    System.out.println(processInstance.getName());
    System.out.println(processInstance.getActivityId());
  }

  /**
   * 查看任务
   */
  @Test
  public void testTaskQuery() {
    ProcessEngine processEngine = cfg.buildProcessEngine();
    TaskService taskService = processEngine.getTaskService();
    List<Task> list = taskService.createTaskQuery()
        .processDefinitionKey("holidayRequest")
        .taskAssignee("lisi")
        .list();

  }

  /**
   * 完成任务
   */
  @Test
  public void testCompleteTask() {
    ProcessEngine processEngine = cfg.buildProcessEngine();
    TaskService taskService = processEngine.getTaskService();
    Task task = taskService.createTaskQuery()
        .processDefinitionKey("holidayRequest")
        .taskAssignee("lisi")
        .singleResult();
    // 添加流程变量
    Map<String,Object> variables = new HashMap<>();
    variables.put("approved",false); // 拒绝请假
    taskService.complete(task.getId(),variables);
  }

  /**
   * 查看历史人物
   */
  @Test
  public void testTaskHistory() {
    ProcessEngine processEngine = cfg.buildProcessEngine();
    List<HistoricActivityInstance> list = processEngine.getHistoryService()
        .createHistoricActivityInstanceQuery()
        .processDefinitionId("holidayRequest:1:3")
        .finished()
        .orderByHistoricActivityInstanceEndTime()
        .asc()
        .list();
    for (HistoricActivityInstance historicActivityInstance : list) {
      System.out.println(historicActivityInstance.getId());
      System.out.println(historicActivityInstance.getAssignee());
      System.out.println(historicActivityInstance.getActivityName());
    }

  }
}
