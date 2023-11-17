package pl.javastart.task.io;

import pl.javastart.task.model.Vehicle;

import java.util.Scanner;

public class DataReader {
    private final Scanner scanner = new Scanner(System.in);
    private final ConsolePrinter printer;

    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public int readUsersChoice() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                printer.printLine("Podałeś opcję wyboru w nieprawidłowym formacie. Spróbuj ponownie.");
            }
        }
    }

    public Vehicle createAndAddVehicle() {
        try {
            printer.printVehicleTypes();
            Vehicle.Type type = readVehicleType();
            printer.printLine("Marka:");
            String brand = scanner.nextLine();
            String model;
            int year;
            int mileage;
            printer.printLine("Model:");
            model = scanner.nextLine();
            printer.printLine("Rocznik:");
            year = Integer.parseInt(scanner.nextLine());
            printer.printLine("Przebieg:");
            mileage = Integer.parseInt(scanner.nextLine());
            printer.printLine("Nr VIN:");
            String vin = scanner.nextLine();

            return new Vehicle(type, brand, model, year, mileage, vin);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Podano dane w nieprawidłowym formacie. Spróbuj ponownie.");
        }
    }

    private Vehicle.Type readVehicleType() {
        return Vehicle.Type.createTypeFromInt(Integer.parseInt(scanner.nextLine()));
    }
}
