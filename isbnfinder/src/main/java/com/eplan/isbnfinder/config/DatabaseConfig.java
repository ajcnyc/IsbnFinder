package com.eplan.isbnfinder.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * The spring configuration class for the application's database
 * 
 * @author Alex Cohen
 *
 */
@Configuration
public class DatabaseConfig {

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	/**
	 * A Bean method that creates a DataSource with the correct credentials to
	 * connect to the MySQL database
	 * 
	 * @return A DataSource with the correct credentials to connect to the MySQL database
	 */
	@Bean
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create().url(url).username(username).password(password)
				.driverClassName(driverClassName).build();
	}
}
