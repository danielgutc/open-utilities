package org.openutilities.proc.guiding;

import com.datastax.spark.connector.japi.CassandraStreamingJavaUtil;
import com.datastax.spark.connector.writer.RowWriterFactory;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.openutilities.proc.core.config.Configuration;
import org.openutilities.proc.core.domain.Reading;

import com.datastax.spark.connector.streaming.*;
import static com.datastax.spark.connector.japi.CassandraJavaUtil.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Guiding implementation parses input messages with readings and perform syntactic and semantic validation.
 */
public class GuidingJob
{
    public static final String KAFKA_TOPIC_NAME = "kafka.topic.name";

    /**
     * Main Spark job entry.
     *
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
     *
     * @param topic name
     * @throws Exception when any
     */
    private void startGuiding(String topic) throws Exception
    {
        SparkConf conf = new SparkConf()
                .setAppName("mdm-readings-syntactic-validation")
                .set("spark.cassandra.connection.host", "spark-vm")
                .set("spark.cassandra.connection.port", "9042")
                .setMaster("local[*]");

        JavaStreamingContext jsc = new JavaStreamingContext(conf, new Duration(1000));

        // Connect to Kafka
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "192.168.247.100:9092");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "group_1");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", true);

        Collection<String> topics = Arrays.asList(topic);

        JavaInputDStream<ConsumerRecord<String, String>> stream =
                KafkaUtils.createDirectStream(
                        jsc,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)
                );

        JavaDStream<Reading> readingJavaDStream =
                stream.map(cr -> cr.value())
                        .map(s -> {
                            String[] values = s.split(",");
                            return Reading.builder().meterSerial(values[0]).meterChannel(values[2]).date(ZonedDateTime.now()).value(new BigDecimal(values[6])).build();
                        });

        CassandraStreamingJavaUtil.javaFunctions(readingJavaDStream)
                .writerBuilder("mdm", "readings", mapToRow(Reading.class,
                        Pair.of("meterChannel", "channel"),
                        Pair.of("date","read_dt"),
                        Pair.of("channelId", "channel_id"),
                        Pair.of("typeId", "reading_type_id"))
                ).saveToCassandra();

        jsc.start();
        jsc.awaitTermination();

        //stream.foreachRDD(rdd -> javaFunctions(rdd).writerBuilder("mdm", "readings", mapRowTo(Reading.class)).saveToCassandra();
        //stream.map(r -> r).dstream().saveToCassandra("mdm", "readings");

/*        Dataset<Row> messages = KafkaStructuredStreamSubscriber.subscribe(topic);

        Dataset<String> readings = messages.selectExpr("CAST(value AS STRING)")
                .as(Encoders.STRING())
                .flatMap((FlatMapFunction<String, String>) x ->
                        Arrays.asList(x.split("\n")).iterator(), Encoders.STRING());



        StreamingQuery sq = readings
                .writeStream()
                .format("org.apache.spark.sql.cassandra")
                .options(new HashMap<String, String>()
                {
                    {
                        put("keyspace", "mdm");
                        put("table", "readings");
                    }
                }).start();
        sq.awaitTermination();*/
        
/*
                .map((MapFunction<String, Reading>) l ->
                {

                    String[] readingSplit = l.split(",");
                    return Reading.builder()
                            .meterSerial(readingSplit[0])
                            .meterChannel(readingSplit[1])
                            .build();
                }, Encoders.bean(Reading.class));*/

        /*StreamingQuery sq = readings.writeStream()
                //.outputMode("complete")
                .format("console")
                .start();
        sq.awaitTermination();*/

    }

    /**
     * Parse readings message.
     *
     * @param stream representing the message
     * @return the Dataset representing the readings
     */
    private Dataset<String> parseReadings(Dataset<Row> stream)
    {
        return stream.selectExpr("CAST(value AS STRING)")
                .as(Encoders.STRING())
                .flatMap((FlatMapFunction<String, String>) x ->
                        Arrays.asList(x.split("\n")).iterator(), Encoders.STRING())
                .map((MapFunction<String, String>) l ->
                {
                    String[] readingSplit = l.split(",");
                    return Reading.builder()
                            .meterSerial(readingSplit[0])
                            .meterChannel(readingSplit[1])
                            .build().toString();
                }, Encoders.STRING());

    }

    /**
     * Encapsulates the syntactic and semantic validation.
     *
     * @param readings to guide
     */
    private void guideReadings(Dataset<String> readings)
    {
        // TODO
    }

    /**
     * Persists the readings in database
     *
     * @param readings
     */
    private void storeReadings(Dataset<String> readings)
    {

    }
}
