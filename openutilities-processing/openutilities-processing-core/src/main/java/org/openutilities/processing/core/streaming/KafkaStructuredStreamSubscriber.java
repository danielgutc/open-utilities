package org.openutilities.processing.core.streaming;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.openutilities.processing.core.config.Configuration;
import org.openutilities.processing.core.session.SparkSessionFactory;

/**
 * Encapsulates an structured streams to Kafka.
 */
public class KafkaStructuredStreamSubscriber
{
    private KafkaStructuredStreamSubscriber(){}

    /**
     * Subscribes to a Kafka topic.
     * @param topicName is the topic to subscribe
     * @return a {@Link Dataset} representing the stream
     */
    public static Dataset<Row> subscribe(String topicName)
    {
        return SparkSessionFactory.getSparkSession()
                .readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", Configuration.getPropertyAsString("kafka.bootstrap.servers"))
                .option("subscribe", topicName)
                .load();
    }
}
