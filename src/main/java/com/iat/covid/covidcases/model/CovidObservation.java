package com.iat.covid.covidcases.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvNumber;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "covid_observations")
public class CovidObservation {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@CsvBindByName(column = "SNo")
	private int sNo;
	
	@CsvDate(value = "MM/dd/yyyy")
	@CsvBindByName(column = "ObservationDate")
	private LocalDate observationDate;
	
	@CsvBindByName(column = "Province/State")
	private String province;
	
	@CsvBindByName(column = "Country/Region")
	private String country;
	
	@CsvBindByName(column = "Last Update")
	private String lastUpdate;
	
	@CsvBindByName(column = "Confirmed")
	@CsvNumber(value = "#0")
	private int confirmed;
	
	@CsvBindByName(column = "Deaths")
	@CsvNumber(value = "#0")
	private int deaths;
	
	@CsvBindByName(column = "Recovered")
	@CsvNumber(value = "#0")
	private int recovered;
	
}
