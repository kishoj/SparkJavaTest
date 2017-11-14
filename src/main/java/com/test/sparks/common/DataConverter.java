package com.test.sparks.common;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class DataConverter implements Converter<Dataset<Row>, List<?>> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6479737591179212933L;
	
	private SparkSession spark;
	private SparkConf sparkConf;
	
	public DataConverter(SparkSession spark) {
		this.spark = spark;
		this.sparkConf = spark.sparkContext().conf();
	}
	
	public DataConverter(SparkConf sparkConf) {
		this.sparkConf = sparkConf;
	}

	@SuppressWarnings("resource")
	@Override
	public Dataset<Row> convert(List<?> persons, Class<?> beanClass) {
		return spark.createDataFrame(new JavaSparkContext(sparkConf)
	        	 .parallelize(persons), beanClass);
	}

}
