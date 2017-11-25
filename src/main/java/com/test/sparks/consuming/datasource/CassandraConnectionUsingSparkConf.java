package com.test.sparks.consuming.datasource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import com.datastax.spark.connector.japi.CassandraRow;
import com.datastax.spark.connector.japi.rdd.CassandraTableScanJavaRDD;
import com.test.sparks.common.Person;
import com.test.sparks.common.SparkKeyword;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.*;

public class CassandraConnectionUsingSparkConf implements SparkKeyword {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf()
					.setMaster(SPARK_LOCAL)
					.setAppName("Java")
					.set(CASSANDRA_HOST, "127.0.0.1")
					.set(CASSANDRA_PORT, "9042")
					.set(SPARK_MULTIPLE_CONTEXT, "true");
		
		JavaSparkContext sc = new JavaSparkContext(conf);		
		JavaStreamingContext ssc = new JavaStreamingContext(conf, Durations.seconds(1L));		
		
		
		List<Person> personsToWrite = Arrays.asList(new Person("test@gmail.com", "test", "test"));
		JavaRDD<Person> personsRDDs = sc.parallelize(personsToWrite);
		
		Map<String, String> fieldToColumnMapping = new HashMap<>();
		fieldToColumnMapping.put("email", "email");
		fieldToColumnMapping.put("firstName", "first_name");
		fieldToColumnMapping.put("lastName", "last_name");
		javaFunctions(personsRDDs).writerBuilder("cassandratest", "users", mapToRow(Person.class, fieldToColumnMapping)).saveToCassandra();
		
		CassandraTableScanJavaRDD<CassandraRow> users = javaFunctions(sc.sc()).cassandraTable("cassandratest", "users");
		users.foreach(user -> {
			System.out.println(user.toString());
		});
			
		CassandraTableScanJavaRDD<Person> entities = javaFunctions(sc.sc()).cassandraTable("cassandratest", "users", mapRowTo(Person.class));
		entities.foreach(entity -> {
			System.out.println(entity.toString());
		});		
		
	}
}
