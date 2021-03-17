package com.iat.covid.covidcases.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CovidDataResponseDto {

	@JsonProperty("observation_date")
	private LocalDate observationDate;
	private List<CovidDataDto> countries;
}


