package com.holmes.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.holmes.adapter.StaticLightPersister;
import com.holmes.domain.Light;
import com.holmes.domain.LightColor;

public class ToggleLightUseCaseTest {

	@Test
	public void lightShouldToggleToRedAndBackToGreen() {
		ToggleLightUseCase uc = new ToggleLightUseCase(new StaticLightPersister());
		Light light = uc.execute();
		Assertions.assertEquals(LightColor.RED, light.getColor());
		light =  uc.execute();
		Assertions.assertEquals(LightColor.GREEN, light.getColor());
	}

}
