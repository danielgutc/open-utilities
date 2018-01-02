package org.openutilities.core.appservices.workflow;

/**
 * Represents an observer workflow engine.
 */
public abstract class WorkflowEngine
{
    /**
     * Observe actions notifications node.
     *
     * @param execution producing the notification
     */
    public abstract void notify(SimpleWorkflowExecution execution);
}
