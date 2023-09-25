package com.alfred.flowable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Alfred.Ning
 * @since 2023年09月25日 07:42:00
 */
public class Test01 {


  /**
   * 获取流程引擎
   */
  public void testGetProcessEngine() {
    ProcessEngineConfiguration processEngineConfiguration = new StandaloneProcessEngineConfiguration();
    String jdbcUrl = "jdbc:mysql://localhost:3307/my_dev?characterEncoding=utf8&nullCatalogMeanCurrent=true";
    processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
    processEngineConfiguration.setJdbcUrl(jdbcUrl);
    processEngineConfiguration.setJdbcUsername("root");
    processEngineConfiguration.setJdbcPassword("ningqiang");
    // 如果数据表结构不存在就新建
    processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

    ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
    System.out.println(processEngine);

  }

  ProcessEngineConfiguration configuration;

  @Before
  public void before() {
    configuration = new StandaloneProcessEngineConfiguration();
    String jdbcUrl = "jdbc:mysql://localhost:3307/my_dev?characterEncoding=utf8&nullCatalogMeanCurrent=true";
    configuration.setJdbcDriver("com.mysql.jdbc.Driver");
    configuration.setJdbcUrl(jdbcUrl);
    configuration.setJdbcUsername("root");
    configuration.setJdbcPassword("ningqiang");
    // 如果数据表结构不存在就新建
    configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

  }

  // 部署流程
  @Test
  public void testDeploy() {
    ProcessEngine engine = configuration.buildProcessEngine();
    RepositoryService repositoryService = engine.getRepositoryService();
    Deployment deploy = repositoryService.createDeployment()
        .addClasspathResource("holiday-request.bpmn20.xml")
        .name("请求流程")
        .deploy();
    System.out.println("id: " + deploy.getId());
    System.out.println("name: " + deploy.getName());
  }

  // 查询部署信息
  @Test
  public void testQueryDeploy() {
    ProcessEngine engine = configuration.buildProcessEngine();
    RepositoryService repositoryService = engine.getRepositoryService();
    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
        .deploymentId("1")
        .singleResult();
    System.out.println(processDefinition.getId());
    System.out.println(processDefinition.getName());
    System.out.println(processDefinition.getDescription());
    System.out.println(processDefinition.getDeploymentId());
  }

  // 启动流程
  @Test
  public void testRunProcess() {
    ProcessEngine engine = configuration.buildProcessEngine();
    RuntimeService runtimeService = engine.getRuntimeService();

    // 流程变量
    Map<String, Object> variables = new HashMap<>();
    variables.put("employee", "张三");
    variables.put("nrOfHolidays", 3);
    variables.put("description", "累了");
    ProcessInstance holidayRequest = runtimeService.startProcessInstanceByKey("holidayRequest", variables);
    System.out.println(holidayRequest.getProcessDefinitionId());
    System.out.println(holidayRequest.getActivityId());
    System.out.println(holidayRequest.getId()); // 2501

  }

  // 删除流程定义
  @Test
  public void testDeleteDeployment() {
    ProcessEngine engine = configuration.buildProcessEngine();
    RepositoryService repositoryService = engine.getRepositoryService();
    repositoryService.deleteDeployment("5001", true); // 级联删除
  }

  // 查询任务信息
  @Test
  public void testQueryTask() {
    ProcessEngine engine = configuration.buildProcessEngine();
    TaskService taskService = engine.getTaskService();
    List<Task> list = taskService.createTaskQuery()
        .processDefinitionKey("holidayRequest") // 指定查询流程编号
        .taskAssignee("zhangsan") // 查询任务处理人
        .list();
    for (Task task : list) {
      System.out.println(task.getTaskDefinitionId());
      System.out.println(task.getName());
      System.out.println(task.getAssignee());
      System.out.println(task.getDescription());
      System.out.println(task.getId());
    }
  }

  // 处理任务
  @Test
  public void testCompleteTask() {
    ProcessEngine processEngine = configuration.buildProcessEngine();
    TaskService taskService = processEngine.getTaskService();
    Task task = taskService.createTaskQuery()
        .processDefinitionKey("holidayRequest")
        .taskAssignee("zhangsan")
        .singleResult();
    Map<String, Object> variables = new HashMap<>();
    variables.put("approved", false);
    taskService.complete(task.getId(), variables);
  }
}
