package com.holmes.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.holmes.domain.Light;
import com.holmes.usecase.GetLightUseCase;
import com.holmes.usecase.ToggleLightUseCase;

@RestController
@RequestMapping("/light")
public class RedGreenApi {
	
	private final GetLightUseCase getLightUseCase;
	private final ToggleLightUseCase toggleLightUseCase;

	public RedGreenApi(GetLightUseCase uc, ToggleLightUseCase toggleUseCase) {
		this.getLightUseCase = uc;
		this.toggleLightUseCase = toggleUseCase;
	}
	
	@GetMapping
	public Light getLight() {
		return getLightUseCase.execute();
	}
	
	@PostMapping
	public Light toggleLight() {
		return toggleLightUseCase.execute();
	}

}
