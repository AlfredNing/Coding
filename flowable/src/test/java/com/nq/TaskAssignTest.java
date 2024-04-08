package com.nq;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author wuguang
 * @description 任务分配
 * @date 2024/3/27 11:08
 */
public class TaskAssignTest {

    ProcessEngineConfiguration cfg;

    @Before
    public void testProcessConfig() {
        cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://localhost:3306/flowable?serverTimezone=UTC&nullCatalogMeansCurrent=true")
                .setJdbcUsername("root")
                .setJdbcPassword("ningqiang")
                .setJdbcDriver("com.mysql.cj.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
    }


    @Test
    public void testDeploy() {
        ProcessEngine processEngine = cfg.buildProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 流程部署
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("holiday-request.bpmn21.xml")
                .name("请假流程-流程变量")
                .deploy();
        String deploymentId = deploy.getId();
        System.out.println("流程部署ID》》》" + deploymentId);

    }

    @Test
    public void testStart() {
        ProcessEngine processEngine = cfg.buildProcessEngine();
        // 启动流程实例
        HashMap<String, Object> map = new HashMap<>();
        map.put("assign1","lisi");
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceById("17501", map);
        System.out.println("processInstance.getId()" + processInstance.getId());
        System.out.println("processInstance.getName()" + processInstance.getName());
        System.out.println("processInstance.getDescription()" + processInstance.getDescription());
    }

    @Test
    public void testStart02() {
        ProcessEngine processEngine = cfg.buildProcessEngine();
        // 启动流程实例
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceById("holidayRequest:3:7503");
        System.out.println("processInstance.getId()" + processInstance.getId());
        System.out.println("processInstance.getName()" + processInstance.getName());
        System.out.println("processInstance.getDescription()" + processInstance.getDescription());
    }


}
