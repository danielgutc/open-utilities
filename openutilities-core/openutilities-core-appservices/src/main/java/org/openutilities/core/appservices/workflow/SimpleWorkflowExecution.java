package org.openutilities.core.appservices.workflow;

import java.io.Serializable;

public class SimpleWorkflowExecution implements Serializable
{
    private Step workflow; // Tree fashion workflow definition
    private Step executionStep; // Pointer to last action being executed in the workflow
    private ContextParameters parameters; // Global shared context

    /**
     * Observe actions notifications node.
     *
     * @param step producing the notification
     */
    public void notify(Step step)
    {
    }
}
