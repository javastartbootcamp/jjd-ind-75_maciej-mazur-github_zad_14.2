package pl.javastart.task.io.file;

import pl.javastart.task.io.ConsolePrinter;
import pl.javastart.task.model.Vehicle;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class FileManager {
    private static final String FILE_NAME = "vehicles.obj";

    private final ConsolePrinter printer;

    public FileManager(ConsolePrinter printer) {
        this.printer = printer;
    }

    public Queue<Vehicle> importVehicles() throws ClassNotFoundException {
        Queue<Vehicle> vehicles = new LinkedList<>();

        try (var fis = new FileInputStream(FILE_NAME);
            var ois = new ObjectInputStream(fis)
        ) {
            vehicles = (Queue<Vehicle>) ois.readObject();
        } catch (IOException e) {
            printer.printLine("Podczas ostatniego zamknięcia programu nie zostały zapisane w pliku żadne pojazdy oczekujące na przegląd.");
        }

        return vehicles;
    }

    public void exportVehicles(Queue<Vehicle> vehicles) throws IOException {
        try (var fos = new FileOutputStream(FILE_NAME);
            var oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(vehicles);
            printer.printLine("Prawidłowo zapisano do pliku pozostałe do przeglądu pojazdy w ilości " + vehicles.size());
        } catch (IOException e) {
            throw new IOException("Błąd zapisu do pliku " + FILE_NAME);
        }
    }

    public void deleteFile() {
        File file = new File(FILE_NAME);
        file.delete();
    }
}
