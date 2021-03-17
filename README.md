# dwm-covid-cases

## Java - Spring Boot - Maven - PostgreSQL - Spring JPA - OpenCSV

### Setup Prerequisites
 - PostgresSQL 
 - Lombok (Requires installation for some IDEs eg Eclipse: https://www.baeldung.com/lombok-ide

### Instruction
##### Install the Project and its artifacts by running
  - **mvn clean install** from the commandline
##### Run the CovidCasesApplication.java as a 'Java Application' in your chosen IDE
##### To test the endpoint you can either send a request via Postman or cURL
  - Request URL: <localhost>**/api/v1/top/confirmed**?observation_date=2020-01-22&max_results=20
  - Request Parameters: 
        - **observation_date**: accepts Local Date
        - **max_results**: accepts integer value; Number of countries to be returned ordered by # of Confirmed Cases; Defaults to 10
