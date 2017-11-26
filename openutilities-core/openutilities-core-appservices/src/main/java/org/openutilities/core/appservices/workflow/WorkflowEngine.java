package org.openutilities.core.appservices.workflow;

/**
 * Represents an observer workflow engine.
 */
public abstract class WorkflowEngine
{
    /**
     * Observe actions notifications node.
     *
     * @param step producing the notification
     */
    public abstract void notify(Step step);
}
