package com.iat.covid.covidcases.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CovidDataRequestDto {
	
	@JsonProperty("observation_date")
	private String observationDate;
	
	@JsonProperty("max_results")
	private int maxresults;
}
