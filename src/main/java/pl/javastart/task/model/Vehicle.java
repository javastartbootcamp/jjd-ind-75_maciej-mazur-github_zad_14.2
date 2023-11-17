package pl.javastart.task.model;

import java.io.Serializable;

public class Vehicle implements Serializable {
    private final Type type;
    private final String brand;
    private final String model;
    private final int year;
    private final int mileage;
    private final String vin;

    public Vehicle(Type type, String brand, String model, int year, int mileage, String vin) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.vin = vin;
    }

    public enum Type implements Serializable {
        BIKE, CAR, TRUCK;

        public static Type createTypeFromInt(int choice) {
            try {
                return values()[choice];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Podano nieprawidłowy typ pojazdu.");
            }
        }
    }

    @Override
    public String toString() {
        String typeName = switch (type) {
            case CAR -> "Samochód";
            case BIKE -> "Motor";
            case TRUCK -> "Samochód ciężarowy";
            default -> "";
        };

        return String.format("%s, %s %s, rocznik %d, przebieg %dkm, nr VIN %S",
                typeName, brand, model, year, mileage, vin);
    }
}
