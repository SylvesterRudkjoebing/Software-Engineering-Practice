package edu.sdse.csvprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CityCSVProcessor {
	private ArrayList<CityRecord> allRecords;
	private Map<String, List<CityRecord>> recordsByCity;

	public CityCSVProcessor() {
		allRecords = new ArrayList<>();
		recordsByCity = new HashMap<>();
	}

	public void readAndProcess(File file) {
		//Try with resource statement (as of Java 8)
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			//Discard header row
			br.readLine();
			
			String line;
			
			while ((line = br.readLine()) != null) {
				// Parse each line
				String[] rawValues = line.split(",");
				
				int id = convertToInt(rawValues[0]);
				int year = convertToInt(rawValues[1]);
				String city = convertToString(rawValues[2]);
				int population = convertToInt(rawValues[3]);
				
				//System.out.println("id: " + id + ", year: " + year + ", city: " + city + ", population: " + population);
				
				//Creates object cityRecord with the current line and adds it to a list of cityRecords
				CityRecord cityRecord = new CityRecord(id, year, city, population);
				allRecords.add(cityRecord);

				// Add the CityRecord to the 'recordsByCity' map
				recordsByCity.computeIfAbsent(city, k -> new ArrayList<>()).add(cityRecord);

			}
		} catch (Exception e) {
			System.err.println("An error occurred:");
			e.printStackTrace();
		}
	}

	public void printAllRecords() {
        for (CityRecord record : allRecords) {
            System.out.println(record);
        }
    }
	
	private String cleanRawValue(String rawValue) {
		return rawValue.trim();
	}
	
	private int convertToInt(String rawValue) {
		rawValue = cleanRawValue(rawValue);
		return Integer.parseInt(rawValue);
	}
	
	private String convertToString(String rawValue) {
		rawValue = cleanRawValue(rawValue);
		
		if (rawValue.startsWith("\"") && rawValue.endsWith("\"")) {
			return rawValue.substring(1, rawValue.length() - 1);
		}
		
		return rawValue;
	}
	
	public void printRecordsByCity() {
        for (Map.Entry<String, List<CityRecord>> entry : recordsByCity.entrySet()) {
            String cityName = entry.getKey();
            List<CityRecord> cityRecords = entry.getValue();

			int totalPopulation = 0;
			int minYear = Integer.MAX_VALUE;
			int maxYear = Integer.MIN_VALUE;
	
			for (CityRecord record : cityRecords) {
				totalPopulation += record.population();
				int year = record.year();
				minYear = Math.min(minYear, year);
				maxYear = Math.max(maxYear, year);
			}
	
			int entries = cityRecords.size();
			float avgPopulation = (float) totalPopulation / entries;
	
			String formattedString = String.format("%s (%d-%d; %d entries): %.0f", cityName, minYear, maxYear, entries, avgPopulation);
			System.out.println(formattedString);

        }
    }
	public static final void main(String[] args) {
		CityCSVProcessor reader = new CityCSVProcessor();
		
		File dataDirectory = new File("data/");
		File csvFile = new File(dataDirectory, "Cities.csv");
		
		reader.readAndProcess(csvFile);

		reader.printAllRecords();

		reader.printRecordsByCity();
	}
}
