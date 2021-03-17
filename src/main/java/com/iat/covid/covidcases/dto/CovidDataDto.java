package com.iat.covid.covidcases.dto;

import lombok.Data;

@Data
public class CovidDataDto {

	private String country;
	private int confirmed;
	private int deaths;
	private int recovered;

}
