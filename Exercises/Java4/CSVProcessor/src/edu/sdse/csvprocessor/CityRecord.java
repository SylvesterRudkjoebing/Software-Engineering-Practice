package edu.sdse.csvprocessor;

public record CityRecord(int id, int year, String city, int population) {
    @Override
    public String toString() {
        return String.format("id: %d, year: %d, city: %s, population: %d", id, year, city, population);
    }

    public static void main(String[] args) {
        // Check if CL arguments are proper
        if (args.length != 4) {
            System.out.println("Usage: java CityRecord <id> <year> <city> <population>");
            return;
        }

        // Create a CityRecord instance directly from command-line arguments
        CityRecord cityRecord = new CityRecord(
            Integer.parseInt(args[0]),
            Integer.parseInt(args[1]),
            args[2],
            Integer.parseInt(args[3])
        );

        // Print the formatted CityRecord using toString()
        System.out.println(cityRecord);
    }
}
