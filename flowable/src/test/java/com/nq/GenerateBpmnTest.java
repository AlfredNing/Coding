package com.nq;

import org.flowable.bpmn.BpmnAutoLayout;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author wuguang
 * @description
 * @date 2024/4/8 19:46
 */
public class GenerateBpmnTest {

    @Test
    public void test01() {
        BpmnModel bpmnModel = new BpmnModel();
        Process process = new Process();
        bpmnModel.addProcess(process);
        process.setId("simpleProcess");
        process.setName("Simple Process");

        StartEvent startEvent = new StartEvent();
        startEvent.setId("start");
        process.addFlowElement(startEvent);

        UserTask userTask = new UserTask();
        userTask.setName("Work on it");
        userTask.setId("work");
        ArrayList<FlowableListener> listeners= new ArrayList<>();
        FlowableListener flowableListener = new FlowableListener();

        flowableListener.setEvent("ACTIVITY_START");
        flowableListener.setInstance("com.nq.listener.MyExecutionListener");
        listeners.add(flowableListener);
        userTask.setExecutionListeners(listeners);
        process.addFlowElement(userTask);

        EndEvent endEvent = new EndEvent();
        endEvent.setId("end");
        process.addFlowElement(endEvent);

        SequenceFlow startEventSequenceFlow = new SequenceFlow();
        startEventSequenceFlow.setSourceRef(startEvent.getId());
        startEventSequenceFlow.setTargetRef(userTask.getId());

        SequenceFlow endSequenceFlow = new SequenceFlow();
        endSequenceFlow.setSourceRef(userTask.getId());
        endSequenceFlow.setTargetRef(endEvent.getId());

        process.addFlowElement(startEventSequenceFlow);
        process.addFlowElement(endSequenceFlow);

        ParallelGateway parallelGateway = new ParallelGateway();
        parallelGateway.setId("par11");
        // 自动布局
        //new BpmnAutoLayout(bpmnModel).execute();

        //生成xml
        BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
        byte[] bytes = bpmnXMLConverter.convertToXML(bpmnModel);
        System.out.println(new String(bytes));

    }


}
