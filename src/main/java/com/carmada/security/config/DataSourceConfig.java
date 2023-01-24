package com.carmada.security.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class DataSourceConfig {
	
	@Autowired 
	private Environment env;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Bean
	public DataSource securityDataSource() {
		
		ComboPooledDataSource securityDataSource
							= new ComboPooledDataSource();
		
		try {
			securityDataSource.setDriverClass(env.getProperty("spring.datasource.login.jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
		
		logger.info("======>>> jdbc.url" + env.getProperty("spring.datasource.login.url"));
		logger.info("======>>> jdbc.user" + env.getProperty("spring.datasource.login.username"));
		
		securityDataSource.setJdbcUrl(env.getProperty("spring.datasource.login.url"));
		securityDataSource.setUser(env.getProperty("spring.datasource.login.username"));
		securityDataSource.setPassword(env.getProperty("spring.datasource.login.password"));
		
		securityDataSource.setInitialPoolSize(getIntProperty("spring.datasource.drivers.hikari.connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("spring.datasource.drivers.hikari.connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("spring.datasource.drivers.hikari.connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("spring.datasource.drivers.hikari.connection.pool.maxIdleTime"));
		
		
		return securityDataSource;
	}
	
	private int getIntProperty(String propName) {
		
		String propVal = env.getProperty(propName);
		
		int intPropVal = Integer.parseInt(propVal);
		
		return intPropVal;
		
	}
}
