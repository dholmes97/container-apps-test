package com.holmes.adapter;

import com.holmes.domain.Light;
import com.holmes.domain.LightColor;
import com.holmes.port.LightPersister;

public class StaticLightPersister implements LightPersister {
	
	private static Light light = new Light(LightColor.GREEN);

	@Override
	public Light getCurrentLight() {
		return StaticLightPersister.light;
	}

	@Override
	public void persistLight(Light light) {
		StaticLightPersister.light = light;
	}

}
