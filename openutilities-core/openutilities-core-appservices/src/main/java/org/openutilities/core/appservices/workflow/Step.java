package org.openutilities.core.appservices.workflow;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Represents a step in a workflow, wrapping the actions
 * @param <T> is an action class
 */
public class Step<T extends Action> implements Serializable
{
    private SimpleWorkflowExecution parentExecution;
    private String code;
    private T action;
    private Map<ActionStatus,List<Step>> nextActions;

    /**
     * Observe actions notifications node.
     *
     * @param action producing the notification
     */
    public void notify(Action action)
    {
        parentExecution.notify(this);
    }
}
