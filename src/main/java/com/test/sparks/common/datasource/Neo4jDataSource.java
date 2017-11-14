package com.test.sparks.common.datasource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.neo4j.spark.Neo4JavaSparkContext;

import com.test.sparks.common.NeoEntity;

public interface Neo4jDataSource {
	
	public static final Map<String, Object> PARAMS = Collections.<String, Object>singletonMap("name", "Kishoj");
	
	public static final String NEO_QUERY = "MATCH (p:Person)-[:LIKES]-> (l:Language) "
			+ " RETURN p.first_name as name, l.name as working_language";

	default Dataset<Row> getDataSet(SparkSession sparkSession) {
		JavaSparkContext sc = new JavaSparkContext(sparkSession.sparkContext());
		Neo4JavaSparkContext csc  = Neo4JavaSparkContext.neo4jContext(sc);		
		List<Map<String, Object>> rdd = csc.query(NEO_QUERY, (java.util.Map<String, Object>) PARAMS).collect();
		return sparkSession.createDataFrame(mapScalaMaptoNeoEntity(rdd), NeoEntity.class).toDF();
	}

	default List<NeoEntity> mapScalaMaptoNeoEntity(List<Map<String, Object>> rdd) {
		List<NeoEntity> results = new ArrayList<>();
		rdd.forEach(rddElement -> {
			results.add(
				new NeoEntity(
					rddElement.get("name").toString(), 
					rddElement.get("working_language").toString()));
		});
		return results;
	}
}
