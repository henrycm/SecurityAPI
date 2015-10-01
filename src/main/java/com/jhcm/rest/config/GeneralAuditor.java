package com.jhcm.rest.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class GeneralAuditor implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		return "JavaByDefault";
	}

}