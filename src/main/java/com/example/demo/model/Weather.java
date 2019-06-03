package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.CoOrdinates;

public class Weather {

	@Autowired
	private CoOrdinates coord;
	
	private String name;

	private String description;
	
	private String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CoOrdinates getCoord() {
		return coord;
	}

	public void setCoord(CoOrdinates coord) {
		this.coord = coord;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
