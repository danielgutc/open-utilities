package org.openutilities.processing.core.streaming;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.openutilities.processing.core.config.Configuration;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Java direct Java stream builder.
 */
public class KafkaJavaDirectStreamBuilder
{
    public static <T> JavaInputDStream<ConsumerRecord<String, T>> createDirectStream(SparkConf conf)
    {
        JavaStreamingContext jsc = new JavaStreamingContext(conf, new Duration(Configuration.getPropertyAsLong("spark.streaming.batch.duration")));

        // Connect to Kafka
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", Configuration.getPropertyAsString("kafka.bootstrap.servers"));
        kafkaParams.put("zookeeper.connect", "192.168.65.90:2181");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "group_1");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", true);

        Collection<String> topics = Arrays.asList(Configuration.getPropertyAsString("kafka.topic.name"));

        return KafkaUtils.createDirectStream(
                        jsc,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.<String, T> Subscribe(topics, kafkaParams)
                );
    }
}
