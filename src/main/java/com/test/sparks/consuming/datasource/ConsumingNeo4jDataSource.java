package com.test.sparks.consuming.datasource;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.neo4j.spark.Neo4JavaSparkContext;

import com.test.sparks.common.SparkAppName;
import com.test.sparks.common.SparkKeyword;

import java.util.Map;
import java.util.Collections;

public class ConsumingNeo4jDataSource implements SparkKeyword {
	
	public static final Map<String, Object> PARAMS = Collections.<String, Object>singletonMap("name", "Kishoj");

	private static final String NEO_QUERY = "MATCH (p:Person)-[:LIKES]-> (l:Language) "
			+ " RETURN p.first_name as name, l.name as working_language";

	public static void main(String[] args) {
		SparkConf sparkConf = new SparkConf()
							.setAppName(SparkAppName.NEO4J_CONSUMER.getValue())
							.setMaster(SPARK_LOCAL)
							.set(SPARK_MULTIPLE_CONTEXT, "true")
							.set(NEO4J_USER, "neo4j")
							.set(NEO4J_PASSWORD, "pass")
							.set(NEO4J_URL, "bolt://localhost:7687");
		
		JavaSparkContext sc = new JavaSparkContext(sparkConf);
		Neo4JavaSparkContext csc  = Neo4JavaSparkContext.neo4jContext(sc);
		
		csc.query(NEO_QUERY, (java.util.Map<String, Object>) PARAMS).collect().forEach(row -> {
			System.out.println(row);
			System.out.println(row.get("name"));
			System.out.println(row.get("working_language"));
		});
	}
	
}
