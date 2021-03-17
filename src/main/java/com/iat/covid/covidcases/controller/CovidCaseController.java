package com.iat.covid.covidcases.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iat.covid.covidcases.dto.CovidDataDto;
import com.iat.covid.covidcases.dto.CovidDataResponseDto;
import com.iat.covid.covidcases.model.CovidObservation;
import com.iat.covid.covidcases.repository.CovidObservationRepository;

@RestController
@RequestMapping("/api/v1")
public class CovidCaseController {

	@Autowired
	private CovidObservationRepository repository;
	
    @Autowired
    private ModelMapper modelMapper;


	@GetMapping("/get-all")
	public List<CovidObservation> getAllData() {
		return repository.findAll();
	}

	@GetMapping("/top/confirmed")
	public ResponseEntity<CovidDataResponseDto> getTopCountriesByConfirmedCases(
			@RequestParam("observation_date") String observationDate,
			@RequestParam(value = "max_results", defaultValue = "10") int maxResult) {
		
		
		LocalDate parsedObservationDate = LocalDate.parse(observationDate);
		List<CovidObservation> covidObservationResultList = repository.findTopCountriesWithConfirmedCase(parsedObservationDate, maxResult);
		List<CovidDataDto> covidDataDto = covidObservationResultList.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
		CovidDataResponseDto responseBody = new CovidDataResponseDto();
		responseBody.setObservationDate(parsedObservationDate);
		responseBody.setCountries(covidDataDto);
		
		return ResponseEntity.ok().body(responseBody);
	}
	
	private CovidDataDto convertToDto(CovidObservation covidObservation) {
		return modelMapper.map(covidObservation, CovidDataDto.class);
				
	}

}
