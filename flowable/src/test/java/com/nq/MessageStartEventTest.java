package com.nq;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author wuguang
 * @description 消息启动测试
 * @date 2024/5/24 16:26
 */
public class MessageStartEventTest extends BaseTest {

    /**
     * 部署启动事件xml
     */
    @Test
    public void deploy() {
        String id = deploymentXml("event/message-start-event01.bpmn20.xml", "messageStartEvent01");
        System.out.println(id);
    }

    /**
     * 发送消息启动
     */
    @Test
    public void run() throws InterruptedException {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByMessage("msg01");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
