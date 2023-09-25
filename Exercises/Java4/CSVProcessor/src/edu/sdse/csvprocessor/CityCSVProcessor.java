package edu.sdse.csvprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CityCSVProcessor {
	public ArrayList<CityRecord> allRecords = new ArrayList<>();
	public Map<String, List<CityRecord>> recordsByCity = new HashMap<>();

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
	
	public static final void main(String[] args) {
		CityCSVProcessor reader = new CityCSVProcessor();
		
		File dataDirectory = new File("data/");
		File csvFile = new File(dataDirectory, "Cities.csv");
		
		reader.readAndProcess(csvFile);

		reader.printAllRecords();

		reader.printRecordsByCity();
	}

	public void printRecordsByCity() {
        for (Map.Entry<String, List<CityRecord>> entry : recordsByCity.entrySet()) {
            String cityName = entry.getKey();
            List<CityRecord> cityRecords = entry.getValue();

			List<Integer> years = new ArrayList<>();
			List<Integer> populations = new ArrayList<>();
			int entries = cityRecords.size();

            for (CityRecord record : cityRecords) {
                years.add(record.year());
				populations.add(record.population());
            }

			// Finds Average Population in the Entries
			int sum = 0;
			for (Integer i : populations) {
				sum += i;
			}

			float avg = sum/entries;

			// Sorts list to find minimum and maximum year
			int min = Collections.min(years);
			int max = Collections.max(years);

			// Prints the data aggregations
			String formattedString = String.format("%s (%d-%d; %d entries): %.0f", cityName, min, max, entries, avg);
			System.out.println(formattedString);

        }
    }
}
