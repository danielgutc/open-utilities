package org.openutilities.processing.guiding;

import com.datastax.spark.connector.japi.CassandraStreamingJavaUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.openutilities.core.domain.Channel;
import org.openutilities.core.domain.Meter;
import org.openutilities.core.domain.Reading;
import org.openutilities.core.domain.builder.ReadingBuilder;
import org.openutilities.processing.core.cache.CacheService;
import org.openutilities.processing.core.config.Configuration;
import org.openutilities.processing.core.session.SparkSessionFactory;
import org.openutilities.processing.core.streaming.KafkaJavaDirectStreamBuilder;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * Guiding implementation parses input messages with readings and perform syntactic and semantic validation.
 */
public class GuidingJob implements Serializable
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

        SparkConf conf = new SparkConf()
                .setAppName(Configuration.getPropertyAsString("application.name"))
                .set("spark.dynamicAllocation.enabled", "false")
                .set("spark.cassandra.connection.host", Configuration.getPropertyAsString("spark.cassandra.connection.host"))
                .set("spark.cassandra.connection.port", Configuration.getPropertyAsString("spark.cassandra.connection.port"));

        JavaInputDStream<ConsumerRecord<String, String>> stream = KafkaJavaDirectStreamBuilder.createDirectStream(conf);

        JavaDStream<Reading> readingJavaDStream =
                stream.map(r -> r.value())
                        .flatMap((FlatMapFunction<String, String>) l -> Arrays.asList(l.split("\n")).iterator())
                        .map(s ->
                        {
                            String[] fields = s.split(",");
                            return ReadingBuilder.aReading().meterSerial(fields[0])
                                    .meterChannel(fields[1])
                                    .date(dateFormat.parse(fields[3]))
                                    .value(new BigDecimal(fields[2]))
                                    .build();
                        })
                        .map(this::guideReading);

        readingJavaDStream.count().print();

        CassandraStreamingJavaUtil.javaFunctions(readingJavaDStream)
                .writerBuilder("mdm", "readings", mapToRow(Reading.class,
                        Pair.of("meterChannel", "channel"),
                        Pair.of("date", "read_dt"),
                        Pair.of("channelId", "channel_id"),
                        Pair.of("typeId", "reading_type_id"),
                        Pair.of("meterId", "meter_id"),
                        Pair.of("channelId", "channel_id"))
                ).saveToCassandra();

        readingJavaDStream.context().start();
        readingJavaDStream.context().awaitTermination();
    }

    /**
     * Encapsulates the syntactic and semantic validation.
     *
     * @param reading to guide
     * @return guided reading
     */
    private Reading guideReading(Reading reading)
    {
        Meter meter = CacheService.getInstance().getObjectFromCache("meters", reading.getMeterSerial());
        Channel channel = (Channel) meter.getChannels()
                .parallelStream()
                .filter(r -> ((Channel)r.getToResource()).getSpecId() == Long.parseLong(reading.getMeterChannel()))
                .findFirst().get().getToResource();

        reading.setMeterId(meter.getId());
        reading.setChannelId(channel.getId());

        return reading;
    }
}
