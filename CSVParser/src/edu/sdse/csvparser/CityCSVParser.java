package edu.sdse.csvparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CityCSVParser {
	
	public void readAndProcess(File file) {
		boolean isFirstLine = true;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;

			while ((line = br.readLine()) != null) {
				// Parsing Lines
				if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
				
				String[] s = line.split(",");
				
				int id = Integer.parseInt(s[0]);
				int year = Integer.parseInt(s[1]);
				String city = s[2].replace("\"", "");
				int population = Integer.parseInt(s[3]);

				System.out.println("id: " + id + ", year: " + year + ", city: " + city + ", population: " + population);
			}
	}		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final void main(String[] args) {
		CityCSVParser reader = new CityCSVParser();
		
		File dataDirectory = new File("data/");
		File csvFile = new File(dataDirectory, "Cities.csv");
		
		reader.readAndProcess(csvFile);

	}
}
