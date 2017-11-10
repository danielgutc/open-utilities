package org.openutilities.processing.core.job;

/**
 * Interface definition for Processing jobs.
 */
public interface ProcessingJob
{
    /**
     * Processing job entry method.
     * @param parameters used to run the job
     */
    void execute(JobParameters parameters);
}
