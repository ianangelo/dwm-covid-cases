package com.iat.covid.covidcases.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iat.covid.covidcases.model.CovidObservation;

@Repository
public interface CovidObservationRepository extends JpaRepository<CovidObservation, Long>{
	
	@Query(value = "SELECT * from "
			+ "		 ( SELECT g.country, sum(g.confirmed) as confirmed, sum(g.deaths) as deaths, "
			+ "			sum(g.recovered) as recovered "
			+ "			from covid_observations g"
			+ "			where observation_date = :observationDate"
			+ "			group by g.country "
			+ "		 ) agg "
			+ "		JOIN "
			+ "		 ( SELECT distinct on (country) country as raw_country, id, s_no, last_update, "
			+ "			province, observation_date from covid_observations "
			+ "		 ) raw "
			+ "		on raw.raw_country = agg.country"
			+ "		order by agg.confirmed desc"
			+ "		limit :count", 
			nativeQuery = true)
	List<CovidObservation> findTopCountriesWithConfirmedCase(
			@Param("observationDate") LocalDate observationDate, 
			@Param("count") int count);
	
	
}
