package com.carmada.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class DriversDataSourceConfiguration  {

    @Bean
    @ConfigurationProperties("spring.datasource.drivers")
    public DataSourceProperties driversDataSourceProperties() {
        return new DataSourceProperties();
    }
    
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.drivers.hikari")
    public DataSource driversDataSource() {
        return driversDataSourceProperties()
          .initializeDataSourceBuilder()
          .build();
    }
}
