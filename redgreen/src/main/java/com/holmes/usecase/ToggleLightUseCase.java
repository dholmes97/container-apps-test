package com.holmes.usecase;

import com.holmes.domain.Light;
import com.holmes.domain.LightColor;
import com.holmes.port.LightPersister;

public class ToggleLightUseCase {
	
	private LightPersister persister;

	public ToggleLightUseCase(LightPersister persister) {
		this.persister = persister;
	}

	public Light execute() {
		Light currentLight = persister.getCurrentLight();
		if (currentLight.getColor() == LightColor.GREEN) {
			currentLight.setColor(LightColor.RED);
			persister.persistLight(currentLight);
			
		} else {
			currentLight.setColor(LightColor.GREEN);
			persister.persistLight(currentLight);
		}
		return currentLight;
	}
}
