package com.jhcm.rest.backend.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class UsernameAuditorAware implements AuditorAware<String>
{
    private static final Logger LOGGER = LoggerFactory.getLogger( UsernameAuditorAware.class );
    @Override
    public String getCurrentAuditor()
    {
        LOGGER.debug( "Returning user..." );
        return "MockUser";
    }
}