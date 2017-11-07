package com.test.sparks.SparkJavaTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.spark_project.guava.collect.ImmutableMap;

import com.datastax.driver.core.Session;
import com.datastax.spark.connector.cql.CassandraConnector;

import com.test.sparks.common.Person;

public class ConsumingCassandraSource {

	public static void main(String[] args) {
		
		SparkSession spark = SparkSession.builder()
				.appName("SparkCassandraApp")
				.config("spark.cassandra.connection.host", "localhost")
				.config("spark.cassandra.connection.port", "9042")
				.config("spark.driver.allowMultipleContexts", "true")
				.config("spark.cores.max", "4")
				.master("local")
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
		
        
        spark.createDataFrame(new JavaSparkContext(spark.sparkContext().conf())
        	 .parallelize(persons), Person.class)
        		.show();
        
        Dataset<Row> dataset = spark.read().format("org.apache.spark.sql.cassandra")
				.options(ImmutableMap.of("table", "users", "keyspace", "cassandratest"))
				.load();
        dataset.show();
    	

		spark.read().format("org.apache.spark.sql.cassandra").options(new HashMap<String, String>() {
			private static final long serialVersionUID = -7482064824813438364L;

			{
				put("keyspace", "cassandratest");
				put("table", "users");
			}
		}).load().show();

        
        /*JavaRDD<Person> rdd = scc.parallelize(persons);
        System.out.println("Total rdd rows: " + rdd.collect().size());
        Dataset<Row> peopleDF = 
        		spark.createDataFrame(rdd, Person.class);
        peopleDF.show();*/
        /*javaFunctions(rdd)
        .writerBuilder("chat", "dictionary", 
                mapToRow(Dictionary.class))
        .saveToCassandra();*/

		
		
		
		//List<com.datastax.driver.core.Row> results = session.execute("SELECT * FROM cassandratest.users").all();
		/*JavaSparkContext con =  new JavaSparkContext();
		JavaRDD<com.datastax.driver.core.Row> rows = con.parallelize(results);
		Dataset<Row> peopleDF = spark.createDataFrame(rows, Person.class);
		peopleDF.show();*/
		
		/*session.execute("SELECT * FROM cassandratest.users").all().forEach(row -> {
			System.out.println(row.toString());
		});*/
		
		/*Dataset<Row> dataset = spark.read().format("org.apache.spark.sql.cassandra")
				.options(ImmutableMap.of("table", "users", "keyspace", "cassandratest"))
				.load();
		 */		
		
		spark.stop();

	}

}
