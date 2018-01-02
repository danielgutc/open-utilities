package org.openutilities.core.appservices.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DummyAction extends Action
{
    private static Logger logger = LoggerFactory.getLogger(DummyAction.class);

    @Override
    public void call(ContextParameters parameters)
    {
        logger.info("Dummy action executed");
    }
}
