package com.test.sparks.common.datasource;

import java.util.Optional;
import java.util.Properties;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.test.sparks.common.PropertiesLoader;
import com.test.sparks.common.PropertyKey;
import com.test.sparks.common.SparkKeyword;

public interface MySQLDataSource extends SparkKeyword, PropertiesLoader, PropertyKey {
	
	default Dataset<Row> getDataSet(SparkSession sparkSession) {
		Optional<Properties> maybeProperties = loadPropertiesFromFile(MYSQL_PROPERTIES);		
		
		if (maybeProperties.isPresent()) {
			Properties properties = maybeProperties.get();
			
			return sparkSession.read().format(JDBC)
					.option(URL, properties.getProperty(MYSQL_URL))
					.option(DBTABLE, properties.getProperty(MYSQL_DB_TABLE))
					.option(USER, properties.getProperty(MYSQL_USER))
					.option(PASSWORD, properties.getProperty(MYSQL_PASSWORD))
					.load();
		}
		return null;
	}
}
