package com.test.sparks.SparkJavaTest;

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
				  .config("spark.cassandra.connection.host", "localhost")
				  .config("spark.cassandra.connection.port", "9042")
				  .config("spark.cores.max", "4")
				  .config("spark.driver.allowMultipleContexts", true)
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
		
		// Joining tables in Spark
		String query = "SELECT u.id, u.first_name, u.last_name, r.age, r.city, e.education, e.university, ue.email FROM "
						+ " users u JOIN records r ON r.firstname = u.first_name " 
						+ " JOIN educations e ON e.firstName = u.first_name "
						+ " JOIN user_emails ue ON ue.first_name = u.first_name";
		sparkSession.sql(query).show();
		
		System.out.println("Tutorial By: " + sparkSession.conf().get("developer"));
		
		sparkSession.close();
	}
}
