package com.holmes.domain;

public class Light {
	
	private LightColor color;
	
	public Light(LightColor color) {
		this.color = color;
	}

	public LightColor getColor() {
		return color;
	}

	public void setColor(LightColor color) {
		this.color = color;
	}

}
