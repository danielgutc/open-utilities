package org.openutilities.proc.guiding;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.openutilities.proc.core.config.Configuration;
import org.openutilities.proc.core.domain.Reading;
import org.openutilities.proc.core.streaming.KafkaStructuredStreamSubscriber;

import java.util.Arrays;

/**
 * Guiding implementation parses input messages with readings and perform syntactic and semantic validation.
 */
public class GuidingJob
{
    public static final String KAFKA_TOPIC_NAME = "kafka.topic.name";

    /**
     * Main Spark job entry.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        GuidingJob guidingJob = new GuidingJob();
        String topic = args.length > 0 ? args[0] : Configuration.getPropertyAsString(KAFKA_TOPIC_NAME);
        guidingJob.startGuiding(topic);
    }

    /**
     * Start streaming Kafka messages and guide the readings.
     * @param topic name
     * @throws Exception when any
     */
    private void startGuiding(String topic) throws Exception
    {
        Dataset<Row> readingsFileDs = KafkaStructuredStreamSubscriber.subscribe(topic);

        Dataset<Reading> readings = parseReadings(readingsFileDs);

        StreamingQuery sq = readings.writeStream()
                //.outputMode("complete")
                .format("console")
                .start();
        sq.awaitTermination();

    }

    /**
     * Parse readings message.
     * @param stream representing the message
     * @return the Dataset representing the readings
     */
    private Dataset<Reading> parseReadings(Dataset<Row> stream)
    {
        return stream.selectExpr("CAST(value AS STRING)")
                .as(Encoders.STRING())
                .flatMap((FlatMapFunction<String, String>) x ->
                        Arrays.asList(x.split("\n")).iterator(), Encoders.STRING())
                .map((MapFunction<String, Reading>)  l ->
                {
                    String[] readingSplit = l.split(",");
                    return Reading.builder()
                            .meterSerial(readingSplit[0])
                            .meterChannel(readingSplit[1])
                            .build();
                }, Encoders.bean(Reading.class));
    }

    /**
     * Encapsulates the syntactic and semantic validation.
     * @param readings to guide
     */
    private void guideReadings(Dataset<String> readings)
    {
        // TODO
    }

    /**
     * Persists the readings in database
     * @param readings
     */
    private void storeReadings(Dataset<String> readings)
    {

    }
}
