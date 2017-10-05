package org.openutilities.proc.core.job;

import java.util.Map;

/**
 * Helper class to build job parameters.
 */
public class JobParametersBuilder
{
    /**
     * Builds a {@link JobParameters} merging default parameters and the ones sent when the job execution is triggered.
     * @param jobName is the job name used to get the default execution parameters
     * @param override contains the parameters that overrides default ones
     * @return a {@link JobParameters}
     */
    public static JobParameters build(String jobName, Map<String, Object> override)
    {
        return null;
    }
}
