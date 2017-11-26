package org.openutilities.core.appservices.workflow;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import javax.transaction.Transactional;

/**
 * Distributed and responsive workflow engine implementation.
 */
public class SimpleWorkflowEngine extends WorkflowEngine
{
    public static final String APPSERVICES_EXECUTIONS_TOPIC = "appservices_executions";

    @Autowired
    private KafkaTemplate<String, SimpleWorkflowExecution> kafkaTemplate;

    //private SimpleWorkflowExecution workflowExecution;


    /**
     * Initialize and start workflow execution.
     *
     * @param execution representing the workflow
     */
    public void createExecution(SimpleWorkflowExecution execution)
    {

    }

    /**
     * Observe actions notifications and persist them in a topic, ensuring that the next step can be executed by any.
     *
     * @param step producing the notification
     */
    @Override
    public void notify(Step step)
    {

    }

    /**
     * Store the workflow execution context in executions queue.
     */
    private void enqueueWorkflowExecution(SimpleWorkflowExecution execution)
    {
        this.kafkaTemplate.send(APPSERVICES_EXECUTIONS_TOPIC, execution);
    }

    /**
     * Subscribes to workflow executions queue and trigger actions.
     */
    @KafkaListener(topics = APPSERVICES_EXECUTIONS_TOPIC)
    private void dequeueWorkflowExecution(ConsumerRecord<?, ?> cr)
    {
        SimpleWorkflowExecution execution = (SimpleWorkflowExecution) cr.value();

        //TODO logic to determine next action and execute it
    }

    /**
     * Execute next step(s).
     */
    private void executeNext()
    {

    }
}
