package com.test.sparks.common;

import java.util.Optional;
import java.util.Properties;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public interface PostgreSQLDataSource extends SparkKeyword, PropertyKey, PropertiesLoader {

	default Dataset<Row> getDataSet(SparkSession sparkSession) {
		Optional<Properties> maybeProperties = loadPropertiesFromFile(POSTGRESQL_PROPERTIES);

		if (maybeProperties.isPresent()) {
			Properties properties = maybeProperties.get();
			return sparkSession.read().format(SparkKeyword.JDBC)
					.option(SparkKeyword.URL, properties.getProperty(PropertyKey.POSTGRESQL_URL))
					.option(SparkKeyword.DBTABLE, properties.getProperty(PropertyKey.POSTGRESQL_DB_TABLE))
					.option(SparkKeyword.USER, properties.getProperty(PropertyKey.POSTGRESQL_USER))
					.option(SparkKeyword.PASSWORD, properties.getProperty(PropertyKey.POSTGRESQL_PASSWORD))
					.load();
		}
		return null;
	}

}