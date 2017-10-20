package org.openutilities.proc.guiding;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.openutilities.proc.core.config.Configuration;
import org.openutilities.proc.core.streaming.KafkaStructuredStreamSubscriber;

import java.util.Arrays;

public class GuidingJob
{
    public static final String KAFKA_TOPIC_NAME = "kafka.topic.name";

    public static void main(String[] args) throws Exception
    {
        GuidingJob guidingJob = new GuidingJob();
        String topic = args.length > 0? args[0] : Configuration.getPropertyAsString(KAFKA_TOPIC_NAME);
        guidingJob.startGuiding(topic);
    }

    /**
     * Start streaming Kafka messages and guide the readings.
     * @param topic name
     * @throws Exception
     */
    private void startGuiding(String topic) throws Exception
    {
        Dataset<Row> readingsFileDs = KafkaStructuredStreamSubscriber.subscribe(topic);

        Dataset<String> readingsCDR = readingsFileDs.selectExpr("CAST(value AS STRING)")
                .as(Encoders.STRING())
                .flatMap((FlatMapFunction<String, String>) x -> Arrays.asList(x.split("\n")).iterator(), Encoders.STRING());

        StreamingQuery sq = readingsCDR.writeStream()
                //.outputMode("complete")
                .format("console")
                .start();
        sq.awaitTermination();

    }
}
