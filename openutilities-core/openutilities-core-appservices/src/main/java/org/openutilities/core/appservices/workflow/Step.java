package org.openutilities.core.appservices.workflow;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a step in a workflow, wrapping the action to be executed.
 *
 * @param <T> is an action class
 */
public class Step<T extends Action> implements Serializable
{
    private SimpleWorkflowExecution parentExecution;
    private String code;
    private T action;
    private Map<ActionStatus, List<Step>> nextActions = new HashMap<>();

    /**
     * Observe actions notifications node.
     *
     * @param action producing the notification
     */
    public void notify(Action action)
    {
        parentExecution.notify(this);
    }

    public void execute()
    {
        action.execute(parentExecution.getParameters());
    }

    //<editor-fold desc="Getters/Setters">
    public SimpleWorkflowExecution getObserverExecution()
    {
        return parentExecution;
    }

    public void setObserverExecution(SimpleWorkflowExecution observerExecution)
    {
        this.parentExecution = observerExecution;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public T getAction()
    {
        return action;
    }

    public void setAction(T action)
    {
        this.action = action;
        this.action.setObserverStep(this);
    }

    public Map<ActionStatus, List<Step>> getNextActions()
    {
        return nextActions;
    }
    //</editor-fold>
}
