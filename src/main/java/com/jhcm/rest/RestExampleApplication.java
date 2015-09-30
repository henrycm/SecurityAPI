package com.jhcm.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RestExampleApplication {
	private static final Logger log = LoggerFactory
			.getLogger(RestExampleApplication.class);

	public static void main(String[] args) {
		final ApplicationContext ctx = SpringApplication.run(
				RestExampleApplication.class, args);

		/*
		 * log.debug("Let's inspect the beans provided by Spring Boot:");
		 * 
		 * final String[] beanNames = ctx.getBeanDefinitionNames();
		 * Arrays.sort(beanNames); for (final String beanName : beanNames) {
		 * log.debug(beanName); }
		 */
	}

}
