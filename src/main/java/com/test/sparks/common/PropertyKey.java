package com.test.sparks.common;

public interface PropertyKey {
	
	static final String POSTGRESQL_URL = "rdbms.postgresql.url";
	static final String POSTGRESQL_USER = "rdbms.postgresql.user";
	static final String POSTGRESQL_PASSWORD = "rdbms.postgresql.password";
	static final String POSTGRESQL_DB_TABLE = "rdbms.postgresql.dbtable";
	
	static final String MYSQL_URL = "rdbms.mysql.url";
	static final String MYSQL_USER = "rdbms.mysql.user";
	static final String MYSQL_PASSWORD = "rdbms.mysql.password";
	static final String MYSQL_DB_TABLE = "rdbms.mysql.dbtable";
	
	static final String CSV_HEADER = "header";
	
	static final String CASSANDRA_KEYSPACE = "nosql.cassandra.keyspace";
	static final String CASSANDRA_TABLE = "nosql.cassandra.dbtable";
	static final String CASSANDRA_HOST = "nosql.cassandra.host";
	static final String CASSANDRA_PORT = "nosql.cassandra.port";
}
