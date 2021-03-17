package com.iat.covid.covidcases.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.iat.covid.covidcases.model.CovidObservation;
import com.iat.covid.covidcases.repository.CovidObservationRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Service
public class CovidDataServiceImpl implements CovidDataService {

	Logger logger = LoggerFactory.getLogger(CovidDataServiceImpl.class);

	@Autowired
	private CovidObservationRepository repository;

	@Autowired
	ResourceLoader resourceLoader;

	@Override
	public boolean loadCovidData() throws IOException {
		Resource fileResource = resourceLoader.getResource("classpath:covid_19_data.csv");
		File file = fileResource.getFile();
		if (Objects.nonNull(file)) {
			logger.error("File not found.");
			return false;
		}

		try (Reader reader = new BufferedReader(new FileReader(file))) {

			CsvToBean<CovidObservation> csvToBean = new CsvToBeanBuilder<CovidObservation>(reader)
					.withType(CovidObservation.class)
					.withIgnoreLeadingWhiteSpace(true)
					.build();

			List<CovidObservation> covidInput = csvToBean.parse();
			repository.saveAll(covidInput);

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		return true;
	}

}
