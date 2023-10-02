package edu.sdse.csvprocessor;

public record CityRecord(int id, int year, String city, int population) {
    @Override
    public String toString() {
        return String.format("id: %d, year: %d, city: %s, population: %d", id, year, city, population);
    }
}
