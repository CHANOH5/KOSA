package com.my.jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/db.properties")
public class MyApplicationContext {
	
	@Value("${spring.datasource.hikari.driver-class-name}")
	private String hikariDriverClassName;
	
	@Autowired
	Environment env;

	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig config = new HikariConfig();
//		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		config.setDriverClassName(hikariDriverClassName);
		
		config.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:xe");
//		config.setUsername("hr");
		config.setUsername(env.getProperty("spring.datasource.hikari.username"));
		config.setPassword("hr");
		config.setMinimumIdle(3);
		
		return config;
	} // hikariConfig
	
	@Bean
	public HikariDataSource hikariDataSource() {
		return new HikariDataSource(hikariConfig());
	} // HikariDataSource
	
	@Bean
	public DataSourceTransactionManager transactionManager() throws Exception {
		
		DataSourceTransactionManager tx = new DataSourceTransactionManager();
		tx.setDataSource(hikariDataSource());
		
		return tx;

	} // transactionManager

} // end class
