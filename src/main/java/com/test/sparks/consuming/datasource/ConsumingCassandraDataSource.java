package com.test.sparks.consuming.datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.spark_project.guava.collect.ImmutableMap;

import com.datastax.driver.core.Session;
import com.datastax.spark.connector.cql.CassandraConnector;
import com.test.sparks.common.DataConverter;
import com.test.sparks.common.Person;
import com.test.sparks.common.SparkAppName;
import com.test.sparks.common.SparkKeyword;

public class ConsumingCassandraDataSource implements SparkKeyword {

	public static void main(String[] args) {
		
		SparkSession spark = SparkSession
				  .builder()
				  .appName(SparkAppName.CASSANDRA_CONSUMER.getValue())
				  .master(SPARK_LOCAL)
				  .config(CASSANDRA_HOST, "localhost")
				  .config(CASSANDRA_PORT, "9042")
				  .config(SPARK_CORE_MAX, "4")
				  .config(SPARK_MULTIPLE_CONTEXT, true)
				  .config(DEVELOPER, "Kishoj Bajracharya")	
				  .getOrCreate();
        
        List<Person> persons = new ArrayList<>();
        CassandraConnector connector = CassandraConnector.apply(spark.sparkContext().conf());
		Session session = connector.openSession();		
		session.execute("SELECT * FROM cassandratest.users").all().forEach(row -> {
			System.out.println(row.toString());
			
			Person person = new Person();
			person.setEmail(row.getString(0));
			person.setFirstName(row.getString(1));
			person.setLastName(row.getString(2));
			
			System.out.println(person.toString());
			
			persons.add(person);
		});
		session.close();
		
		DataConverter converter = new DataConverter(spark);
		Dataset<Row> dataset = converter.convert(persons, Person.class);
		dataset.show();

        
        spark.read().format("org.apache.spark.sql.cassandra")
				.options(ImmutableMap.of("table", "users", "keyspace", "cassandratest"))
				.load().show();

		spark.read().format("org.apache.spark.sql.cassandra")
			.options(
					new HashMap<String, String>() {
						private static final long serialVersionUID = -7482064824813438364L;
			
						{
							put("keyspace", "cassandratest");
							put("table", "users");
						}
					}
			 )
			 .load()
			 .show();
			
		spark.stop();
	}

}
