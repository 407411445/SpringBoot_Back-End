package com.example.springboot_sp;

import com.example.SpringBootApp.SpringBootSpApplication;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.lang.Nullable;
import org.springframework.orm.hibernate5.HibernateTemplate;


import java.util.Arrays;

import static junit.framework.TestCase.assertNotNull;

@SpringBootTest(classes = SpringBootSpApplication.class )
class SpringBootSpApplicationTests {

	private static final  Logger LOGGER = LoggerFactory.getLogger(SpringBootSpApplicationTests.class);
    @Autowired
    private ApplicationArguments springApplicationArguments;


	@Autowired
	private ApplicationContext applicationContext;

	@Value("${spring.datasource.url}")
	private String datasourceUrl;

	@Test
	public void testPropertiesLoaded() {
		LOGGER.warn("Datasource URL: " + datasourceUrl);
	}

    //	@Test
	void contextLoads() {
		System.out.println(springApplicationArguments.getOptionNames());
	}

	@Test
	void contextEntityManagerFactory() {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) applicationContext.getBean("entityManagerFactory");

		LOGGER.warn(String.valueOf(entityManagerFactory));
	}


//	@Test //SessionFactory不好用
//	void contextSessionFactory() {
//		SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
//		assertNotNull(sessionFactory);
//		LOGGER.warn(String.valueOf(sessionFactory.openSession()));
//	}

}
