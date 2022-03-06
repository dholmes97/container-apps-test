package com.holmes.usecase;

import com.holmes.domain.Light;
import com.holmes.port.LightPersister;

public class GetLightUseCase {
	
	private LightPersister persister;

	public GetLightUseCase(LightPersister persister) {
		this.persister = persister;
	}

	public Light execute() {
		return persister.getCurrentLight();
	}

}
