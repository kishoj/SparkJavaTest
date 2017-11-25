package com.test.sparks.consuming.datasource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import com.test.sparks.common.SparkAppName;
import com.test.sparks.common.SparkKeyword;

import kafka.serializer.StringDecoder;


public class ConsumingKafkaDataSource implements SparkKeyword {
	
	public static void main(String[] args) throws InterruptedException {
		SparkConf conf = new SparkConf()
                .setAppName(SparkAppName.KAFKA_CONSUMER.getValue())
                .setMaster(SPARK_LOCAL);
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaStreamingContext ssc = new JavaStreamingContext(sc, new Duration(2000));

        // TODO: processing pipeline

        ssc.start();
        ssc.awaitTermination();
        
        Map<String, String> kafkaParams = new HashMap<>();
        kafkaParams.put("metadata.broker.list", "localhost:9092");
        Set<String> topics = Collections.singleton("mytopic");

        JavaPairInputDStream<String, String> directKafkaStream = KafkaUtils.createDirectStream(ssc,
                String.class, String.class, StringDecoder.class, StringDecoder.class, kafkaParams, topics);


        directKafkaStream.foreachRDD(rdd -> {
            System.out.println("--- New RDD with " + rdd.partitions().size()
                    + " partitions and " + rdd.count() + " records");
            rdd.foreach(record -> System.out.println(record._2));
        });

	}
}
