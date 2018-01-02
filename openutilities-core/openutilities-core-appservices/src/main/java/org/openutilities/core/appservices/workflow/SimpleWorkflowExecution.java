package org.openutilities.core.appservices.workflow;

import java.io.Serializable;

/**
 * Encapsulates a workflow execution
 */
public class SimpleWorkflowExecution implements Serializable
{
    private WorkflowEngine observerWorkflowEngine; // Workflow engine observing the execution
    private Step workflow; // Tree fashion workflow definition
    private Step executionStep; // Pointer to relevant (last or next) execution in the workflow
    private ContextParameters parameters; // Global shared context

    /**
     * Observe actions notifications node.
     *
     * @param step producing the notification
     */
    public void notify(Step step)
    {
        executionStep = step;
        observerWorkflowEngine.notify(this);
    }

    /**
     * Execute current step
     */
    public void execute()
    {
        executionStep.setObserverExecution(this);
        executionStep.execute();
    }

    //<editor-fold desc="Getters/Setters">
    public WorkflowEngine getObserverWorkflowEngine()
    {
        return observerWorkflowEngine;
    }

    public void setObserverWorkflowEngine(WorkflowEngine observerWorkflowEngine)
    {
        this.observerWorkflowEngine = observerWorkflowEngine;
    }

    public Step getWorkflow()
    {
        return workflow;
    }

    public void setWorkflow(Step workflow)
    {
        this.workflow = workflow;
    }

    public Step getExecutionStep()
    {
        return executionStep;
    }

    public void setExecutionStep(Step executionStep)
    {
        this.executionStep = executionStep;
    }

    public ContextParameters getParameters()
    {
        return parameters;
    }

    public void setParameters(ContextParameters parameters)
    {
        this.parameters = parameters;
    }
    //</editor-fold>
}
