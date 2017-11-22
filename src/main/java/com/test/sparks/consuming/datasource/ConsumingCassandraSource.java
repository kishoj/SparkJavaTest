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
import com.test.sparks.common.SparkKeyword;

public class ConsumingCassandraSource {

	public static void main(String[] args) {
		
		SparkSession spark = SparkSession
				  .builder()
				  .appName("Spark Cassandra App")
				  .master(SparkKeyword.SPARK_LOCAL)
				  .config("spark.cassandra.connection.host", "localhost")
				  .config("spark.cassandra.connection.port", "9042")
				  .config("spark.cores.max", "4")
				  .config("spark.driver.allowMultipleContexts", "true")
				  .config("developer", "Kishoj Bajracharya")				  
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
