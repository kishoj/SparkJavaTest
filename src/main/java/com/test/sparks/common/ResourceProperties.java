package com.test.sparks.common;

public interface ResourceProperties {
	static final ClassLoader CLASS_LOADER = ResourceProperties.class.getClassLoader();
	static final String POSTGRESQL_PROPERTIES = "rdbms/postgresql.properties";
	static final String JSON_LOCATION  = "jsons/people.json";
}
