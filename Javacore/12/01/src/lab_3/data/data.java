package lab_3.data;

import lab_3.entities.Worker;

import java.util.ArrayList;

public class data {
    private static ArrayList<Worker> workers = new ArrayList<>();

    public static ArrayList<Worker> getWorkers() {
        return workers;
    }
    public static void addWorker(Worker worker) {
        workers.add(worker);
    }

}
