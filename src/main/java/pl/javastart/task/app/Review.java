package pl.javastart.task.app;

import pl.javastart.task.io.ConsolePrinter;
import pl.javastart.task.io.DataReader;
import pl.javastart.task.io.file.FileManager;
import pl.javastart.task.model.Vehicle;

import java.io.IOException;
import java.util.Queue;

public class Review {
    public static final int EXIT = 0;
    public static final int ADD_VEHICLE = 1;
    public static final int EXAMINE_VEHICLE = 2;

    private Queue<Vehicle> vehicles;
    private ConsolePrinter printer = new ConsolePrinter();
    private FileManager fileManager = new FileManager(printer);
    private DataReader reader = new DataReader(printer);

    public void run() throws ClassNotFoundException {
        initializeVehiclesQueue();
        boolean quit = false;

        while (!quit) {
            try {
                printer.printOptions();
                switch (reader.readUsersChoice()) {
                    case EXIT -> {
                        exitActions();
                        quit = true;
                    }
                    case ADD_VEHICLE -> vehicles.add(reader.createAndAddVehicle());
                    case EXAMINE_VEHICLE -> examineVehicle();
                    default -> printer.printLine("Wybrałeś nieprawidłową opcję. Spróbuj ponownie.");
                }
            } catch (IOException | IllegalArgumentException e) {
                printer.printLine(e.getMessage());
            }
        }
    }

    private void exitActions() throws IOException {
        if (vehicles.size() > 0) {
            fileManager.exportVehicles(vehicles);
        } else {
            /*
            Poniżej kasuję istniejący ewentualnie wcześniej plik, by przy kolejnym uruchomieniu programu
            nie uległy zaczytaniu ponownie te same pojazdy, które w aktualnym uruchomieniu zostały zdjęte z kolejki
             */
            fileManager.deleteFile();
        }
    }

    private void examineVehicle() {
        if (vehicles.size() == 0) {
            printer.printLine("Kolejka pojazdów jest w tej chwili pusta. Dodaj jakieś pojazdy, by móc poddać je przeglądowi.");
            return;
        }

        printer.printLine("Następujący pojazd zostanie teraz poddany przeglądowi:" +
                "\n\t" + vehicles.poll());
    }

    private void initializeVehiclesQueue() throws ClassNotFoundException {
        vehicles = fileManager.importVehicles();
        if (vehicles.size() > 0) {
            printer.printLine("Liczba pojazdów oczekujących na przegląd po ostatnim działaniu programu: " + vehicles.size());
        }
    }
}
