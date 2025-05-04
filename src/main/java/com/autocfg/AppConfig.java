package com.autocfg;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

//@Configuration
//@EnableTransactionManagement
//public class AppConfig {
//
//    @Bean
//    public DataSource dataSource() {
//        return new DriverManagerDataSource("jdbc:h2:mem:testdb0718", "sa", "");
//    }
//
//    @Bean
//    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setPackagesToScan("com.example.Customer.model.rdb", "com.example.Blog.rdb");
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//        hibernateProperties.put("hibernate.show_sql", "true");
//        hibernateProperties.put("hibernate.format_sql", "true");
//        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
//        sessionFactory.setHibernateProperties(hibernateProperties);
//        return sessionFactory;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
//        return new HibernateTransactionManager(sessionFactory);
//    }
//
//}