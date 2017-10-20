package org.openutilities.proc.core.session;

import org.apache.spark.sql.SparkSession;
import org.openutilities.proc.core.config.Configuration;

/**
 * Spark Session Factory using job properties.
 */
public class SparkSessionFactory
{
    /**
     * Create a Spark Session using default job properties.
     * @return the Spark session
     */
    public static SparkSession getSparkSession()
    {
        return SparkSession
                .builder()
                .appName(Configuration.getPropertyAsString("application.name"))
                .config("spark.cassandra.connection.host", Configuration.getPropertyAsString("spark.cassandra.connection.host"))
                .config("spark.cassandra.connection.port", Configuration.getPropertyAsLong("spark.cassandra.connection.port"))
                .config("spark.executor.memory", Configuration.getPropertyAsString("spark.executor.memory"))
                .master("local[*]")
                .getOrCreate();
    }
}
