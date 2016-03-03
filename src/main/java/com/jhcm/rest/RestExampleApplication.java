package com.jhcm.rest;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RestExampleApplication {
    private static final Logger LOG = LoggerFactory.getLogger( RestExampleApplication.class );

	public static void main(String[] args) {
		final ApplicationContext ctx = SpringApplication.run(
				RestExampleApplication.class, args);

        LOG.debug( "Let's inspect the beans provided by Spring Boot:" );

		final String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (final String beanName : beanNames) {
            LOG.debug( beanName );
		}

	}

}
