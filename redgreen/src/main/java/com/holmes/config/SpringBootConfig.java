package com.holmes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.holmes.adapter.StaticLightPersister;
import com.holmes.port.LightPersister;
import com.holmes.usecase.GetLightUseCase;
import com.holmes.usecase.ToggleLightUseCase;

@Configuration
public class SpringBootConfig {
	
	@Bean
	public GetLightUseCase getLightUseCase(LightPersister lp) {
		return new GetLightUseCase(lp);
	}
	
	@Bean 
	public ToggleLightUseCase toggleLightUseCase(LightPersister lp) {
		return new ToggleLightUseCase(lp);
	}
	
	@Bean 
	public LightPersister lightPersister() {
		return new StaticLightPersister();
	}
	
}
