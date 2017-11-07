package com.test.sparks.common.datasource;

import java.util.Optional;
import java.util.Properties;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.spark_project.guava.collect.ImmutableMap;

import com.test.sparks.common.PropertiesLoader;
import com.test.sparks.common.PropertyKey;
import com.test.sparks.common.SparkKeyword;

public interface CassandraDataSource extends SparkKeyword, PropertyKey, PropertiesLoader {

	default Dataset<Row> getDataSet(SparkSession sparkSession) {
		Optional<Properties> maybeProperties = loadPropertiesFromFile(CASSANDRA_PROPERTIES);
		
		if (maybeProperties.isPresent()) {
			Properties properties = maybeProperties.get();			
			return sparkSession.read()
					.format(CASSANDRA_SOURCE)
					.options(
							ImmutableMap.of (
								CASSANDRA_DB_TABLE, 
								properties.getProperty(CASSANDRA_TABLE), 
								CASSANDRA_DB_KEYSPACE, 
								properties.getProperty(CASSANDRA_KEYSPACE)
							))
					.load();
		}		
		return null;
	}
}
