package pl.javastart.task.io;

import pl.javastart.task.app.Review;
import pl.javastart.task.model.Vehicle;

public class ConsolePrinter {
    public void printLine(String text) {
        System.out.println(text);
    }

    public void printOptions() {
        printLine("Wybierz opcję:" +
                "\n > Zakończ program - " + Review.EXIT +
                "\n > Dodaj nowy pojazd do kolejki - " + Review.ADD_VEHICLE +
                "\n > Poddaj przeglądowi pierwszy pojazd w kolejce - " + Review.EXAMINE_VEHICLE);
    }

    public void printVehicleTypes() {
        printLine("Typ pojazdu:" +
                "\n > Motocykl - " + Vehicle.Type.BIKE.ordinal() +
                "\n > Samochód - " + Vehicle.Type.CAR.ordinal() +
                "\n > Samochód ciężarowy - " + Vehicle.Type.TRUCK.ordinal());
    }
}
