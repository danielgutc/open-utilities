package org.openutilities.core.appservices.workflow;

import java.io.Serializable;

/**
 * Represents an observable action
 */
public abstract class Action implements Serializable
{
    private ActionStatus state;
    private Exception exception;

    private Step observerStep; //Observer

    public void execute(ContextParameters parameters)
    {
        try
        {
            this.state = ActionStatus.RUNNING;
            call(parameters);
            this.state = ActionStatus.FINISHED;
        }
        catch (Exception ex)
        {
            this.state = ActionStatus.ERROR;
            this.exception = ex;
        }

        observerStep.notify(this);
    }

    public abstract void call(ContextParameters parameters);
}
