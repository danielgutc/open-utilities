package org.openutilities.core.appservices.workflow.integration;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openutilities.core.appservices.App;
import org.openutilities.core.appservices.workflow.*;
import org.openutilities.core.appservices.config.KafkaConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {App.class, org.openutilities.core.appservices.workflow.integration.KafkaConfiguration.class})
@EmbeddedKafka(partitions = 1, topics = {"appservices_executions"})
public class SimpleWorkflowEngineTest
{
    @Autowired
    private SimpleWorkflowEngine simpleWorkflowEngine;

    @Test
    @Ignore //Embedded Kafka broker takes too long to be available
    public void executeWorkflow()
    {
        SimpleWorkflowExecution execution = new SimpleWorkflowExecution();
        Action action1 = new DummyAction();
        Step step1 = new Step();
        step1.setAction(action1);
        Action action2 = new DummyAction();
        Step step2 = new Step();
        step2.setAction(action2);
        step1.getNextActions().put(ActionStatus.FINISHED, Arrays.asList(step2));
        execution.setWorkflow(step1);

        simpleWorkflowEngine.startExecution(execution);
    }
}
