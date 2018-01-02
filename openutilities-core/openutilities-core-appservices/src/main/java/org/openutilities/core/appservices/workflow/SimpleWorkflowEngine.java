package org.openutilities.core.appservices.workflow;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.openutilities.core.appservices.util.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Distributed and responsive workflow engine implementation.
 */
@Component
public class SimpleWorkflowEngine extends WorkflowEngine
{
    private static Logger logger = LoggerFactory.getLogger(SimpleWorkflowEngine.class);
    public static final String APPSERVICES_EXECUTIONS_TOPIC = "appservices_executions";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Initialize and start workflow execution.
     *
     * @param execution representing the workflow
     */
    public void startExecution(SimpleWorkflowExecution execution)
    {
        execution.setExecutionStep(execution.getWorkflow());
        enqueueWorkflowExecution(execution);
    }

    /**
     * Observe actions notifications. Determines what are the next actions to be executed and enqueue each one to be
     * executed in different nodes.
     *
     * @param execution producing the notification
     */
    @Override
    public void notify(SimpleWorkflowExecution execution)
    {
        // TODO Implement logic to determine next action(s) to be executed. Each next action must be enqueue with different pointer to be executed
        List<Step> nextActions = (List<Step>) execution.getExecutionStep().getNextActions().get(ActionStatus.FINISHED);
        if (nextActions != null)
        {
            execution.setExecutionStep(nextActions.get(0)); // Just take the first next action
            enqueueWorkflowExecution(execution);
        }
        else
        {
            // TODO persist the execution branch status
            logger.info("Persisting status of finished workflow");
        }
    }

    /**
     * Store the workflow execution context in executions queue.
     */
    private void enqueueWorkflowExecution(SimpleWorkflowExecution execution)
    {
        //Detach the workflow engine if there is any observing the workflow
        execution.setObserverWorkflowEngine(null);

        this.kafkaTemplate.send(APPSERVICES_EXECUTIONS_TOPIC, SerializationUtils.toByteArray(execution));
    }

    /**
     * Subscribes to workflow executions queue and trigger actions.
     */
    @KafkaListener(topics = APPSERVICES_EXECUTIONS_TOPIC, groupId = "appServices")
    private void dequeueWorkflowExecution(ConsumerRecord<String, byte[]> cr)
    {
        try
        {
            SimpleWorkflowExecution execution = SerializationUtils.fromByteArray(cr.value());
            execution.setObserverWorkflowEngine(this);

            executeStep(execution);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * Execute next step(s) in the workflow.
     */
    private void executeStep(SimpleWorkflowExecution execution)
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> execution.execute());
    }
}
