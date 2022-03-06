package com.holmes.port;

import com.holmes.domain.Light;

public interface LightPersister {
	
	public Light getCurrentLight();
	
	public void persistLight(Light light);

}
