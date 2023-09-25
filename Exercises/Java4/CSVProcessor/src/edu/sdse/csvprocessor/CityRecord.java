package edu.sdse.csvprocessor;

public record CityRecord(int id, int year, String city, int population) {
    @Override
    public String toString() {
        return String.format("id: %d, year: %d, city: %s, population: %d", id, year, city, population);
    }

    public static void main(String[] args) {
        // Create a CityRecord instance
        int id = Integer.parseInt(args[0]);
        int year = Integer.parseInt(args[1]);
        String city = args[2];
        int population = Integer.parseInt(args[3]);

        CityRecord cityRecord = new CityRecord(id, year, city, population);
        
        // Print the formatted CityRecord using toString()
        System.out.println(cityRecord);
    }
}
