package com.test.sparks.consuming.datasource;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.test.sparks.common.SparkKeyword;
import com.test.sparks.common.datasource.SparkDataSource;

public class ConsumingDataSourceTestApp {
	
	public static void main(String[] args) {
		
		SparkSession sparkSession = SparkSession
				  .builder()
				  .appName("Multiple Source Java App")
				  .master(SparkKeyword.SPARK_LOCAL)
				   // For Cassandra
				  .config(SparkKeyword.CASSANDRA_HOST, "localhost")
				  .config(SparkKeyword.CASSANDRA_PORT, "9042")
				  .config(SparkKeyword.SPARK_CORE_MAX, "4")
				  .config(SparkKeyword.SPARK_MULTIPLE_CONTEXT, true)
				   // For MongoDB 
				  .config(SparkKeyword.SPARK_MONGO_INPUT_URI, "mongodb://127.0.0.1/mongodbtest.professions")
				  .config(SparkKeyword.SPARK_MONGO_OUTPUT_URI, "mongodb://127.0.0.1/mongodbtest.professions")
				  // For Neo4j
				  .config("spark.neo4j.bolt.user", "neo4j")
				  .config("spark.neo4j.bolt.password", "pass")
				  .config("spark.neo4j.bolt.url", "bolt://localhost:7687")
				  // For CouchDB
				  .config("cloudant.protocol", "http")
				  .config("cloudant.host","127.0.0.1:5984")
				  .config("cloudant.username", "admin")
				  .config("cloudant.password","pass")
				  .config("couchdb.database", "favorites")
				  
				  .config("developer", "Kishoj Bajracharya")				  
				  .getOrCreate();
		
		Dataset<Row> jsonDF =  SparkDataSource.JSON.getDataSet(sparkSession);		
		jsonDF.createOrReplaceTempView("records");		
		sparkSession.sql("SELECT * FROM records").show();
		
		SparkDataSource.POSTGRESQL.getDataSet(sparkSession).createOrReplaceTempView("users");		
		sparkSession.sql("SELECT * FROM users").show();
		
		SparkDataSource.CSV.getDataSet(sparkSession).createOrReplaceTempView("educations");
		sparkSession.sql("SELECT * FROM educations").show();
		
		SparkDataSource.CASSANDRA.getDataSet(sparkSession).createOrReplaceTempView("user_emails");
		sparkSession.sql("SELECT * FROM user_emails").show();
		
		SparkDataSource.MYSQL.getDataSet(sparkSession).createOrReplaceTempView("phones");
		sparkSession.sql("SELECT * FROM phones").show();
		
		SparkDataSource.MONGODB.getDataSet(sparkSession).createOrReplaceTempView("professions");
		sparkSession.sql("SELECT * FROM professions").show();
		
		SparkDataSource.NEO4J.getDataSet(sparkSession).createOrReplaceTempView("languages");
		sparkSession.sql("SELECT * FROM languages").show();
		
		SparkDataSource.COUCHDB.getDataSet(sparkSession).createOrReplaceTempView("favorites");		
		sparkSession.sql("SELECT * FROM favorites").show();	
		
		// Joining tables in Spark
		String query = "SELECT u.id, u.first_name, u.last_name, r.age, r.city, e.education, e.university, "
						+ " ue.email, p.phone, pr.professions, l.workingLanguage, "
						+ " f.favorite_drink, f.favorite_food FROM "
						+ " users u JOIN records r ON r.firstname = u.first_name " 
						+ " JOIN educations e ON e.firstName = u.first_name "
						+ " JOIN user_emails ue ON ue.first_name = u.first_name "
						+ " JOIN phones p ON p.first_name = u.first_name "
						+ " JOIN professions pr ON pr.firstname = u.first_name "
						+ " JOIN languages l ON l.name = u.first_name "
						+ " JOIN favorites f ON f.first_name = u.first_name ";
		sparkSession.sql(query).show();
		
		System.out.println("Tutorial By: " + sparkSession.conf().get("developer"));
		
		sparkSession.close();
	}
}
