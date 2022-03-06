package com.holmes.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.holmes.adapter.StaticLightPersister;
import com.holmes.domain.Light;
import com.holmes.domain.LightColor;

public class GetLightUseCaseTest {
	
	@Test
	public void lightShouldStartGreen() {
		GetLightUseCase uc = new GetLightUseCase(new StaticLightPersister());
		Light light = uc.execute();
		Assertions.assertEquals(LightColor.GREEN, light.getColor());
	}
}
