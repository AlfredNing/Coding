package com.nq;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.junit.Before;

/**
 * @author wuguang
 * @description
 * @date 2024/5/24 16:28
 */
public class BaseTest {
    ProcessEngineConfiguration cfg;
    ProcessEngine processEngine;

    @Before
    public void testProcessConfig() {
        cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://localhost:3306/flowable?serverTimezone=UTC&nullCatalogMeansCurrent=true")
                .setJdbcUsername("root")
                .setJdbcPassword("ningqiang")
                .setJdbcDriver("com.mysql.cj.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        processEngine = cfg.buildProcessEngine();
    }

    public String deploymentXml(String xmlFileName, String deploymentName) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 流程部署
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource(xmlFileName)
                .name(deploymentName)
                .deploy();
        return deploy.getId();
    }
}
