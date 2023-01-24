package com.carmada.config;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.carmada.drivers.entity.Driver;
import com.carmada.payment.entity.Payment;
import com.carmada.vehicle.entity.Vehicle;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = {"com.carmada.drivers.dao", "com.carmada.payment.dao", "com.carmada.vehicle.dao"},
  entityManagerFactoryRef = "driversEntityManagerFactory",
  transactionManagerRef = "driversTransactionManager"
)
public class DriversJpaConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean driversEntityManagerFactory(
      @Qualifier("driversDataSource") DataSource dataSource,
      EntityManagerFactoryBuilder builder) {
        return builder
          .dataSource(dataSource)
          .packages(Driver.class, Payment.class, Vehicle.class)
          .build();
    }

    @Bean
    public PlatformTransactionManager driversTransactionManager(
      @Qualifier("driversEntityManagerFactory") LocalContainerEntityManagerFactoryBean driversEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(driversEntityManagerFactory.getObject()));
    }
}

