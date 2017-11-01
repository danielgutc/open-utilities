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
import org.openutilities.proc.core.streaming.KafkaJavaDirectStreamBuilder;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalField;
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
        //String topic = args.length > 0 ? args[0] : Configuration.getPropertyAsString(KAFKA_TOPIC_NAME);
        guidingJob.startGuiding();
    }

    /**
     * Start streaming Kafka messages and guide the readings.
     *
     * @throws Exception when any
     */
    private void startGuiding() throws Exception
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss'Z'");
        JavaInputDStream<ConsumerRecord<String, String>> stream = KafkaJavaDirectStreamBuilder.createDirectStream();

        JavaDStream<Reading> readingJavaDStream =
                stream.map(r -> r.value())
                        .flatMap((FlatMapFunction<String, String>) l -> Arrays.asList(l.split("\n")).iterator())
                        .map(s -> {
                            String[] fields = s.split(",");
                            return Reading.builder().meterSerial(fields[0]).meterChannel(fields[1]).date(dateFormat.parse(fields[3])).value(new BigDecimal(fields[2])).build();
                        });

        readingJavaDStream.count().print();

        CassandraStreamingJavaUtil.javaFunctions(readingJavaDStream)
                .writerBuilder("mdm", "readings", mapToRow(Reading.class,
                        Pair.of("meterChannel", "channel"),
                        Pair.of("date", "read_dt"),
                        Pair.of("channelId", "channel_id"),
                        Pair.of("typeId", "reading_type_id"))
                ).saveToCassandra();

        readingJavaDStream.context().start();
        readingJavaDStream.context().awaitTermination();
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
