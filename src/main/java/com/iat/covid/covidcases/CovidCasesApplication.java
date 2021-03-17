package com.iat.covid.covidcases;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.iat.covid.covidcases.service.CovidDataService;

@SpringBootApplication
public class CovidCasesApplication {

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication
				.run(CovidCasesApplication.class, args);
        CovidDataService service = configurableApplicationContext.getBean(CovidDataService.class);
        service.loadCovidData();
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}
