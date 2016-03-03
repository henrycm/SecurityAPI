package com.jhcm.rest.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.repository.support.Repositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.jhcm.rest.backend.model.User;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "usernameAuditorAware")
public class RestExporterRestConfig extends RepositoryRestConfigurerAdapter
{
    @Override
    public void configureRepositoryRestConfiguration(
        RepositoryRestConfiguration config )
    {
        config.exposeIdsFor( User.class );
    }

    @Bean
    public MessageSource messageSource()
    {
        ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
        bean.setBasename( "classpath:messages" );
        bean.setDefaultEncoding( "UTF-8" );
        return bean;
    }

    @Bean
    public Validator validator()
    {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource( messageSource() );
        return bean;
    }

    public ValidatingRepositoryEventListener validatingRepositoryEventListener(
        ObjectFactory<Repositories> repositories )
    {
        ValidatingRepositoryEventListener listener = new ValidatingRepositoryEventListener(
            repositories );
        configureValidatingRepositoryEventListener( listener );
        listener.addValidator( "beforeSave", validator() );
        return listener;
    }

}