package lab_3.data;

import lab_3.entities.Worker;

import java.util.ArrayList;

public class Data {
    private static ArrayList<Worker> workers = new ArrayList<>();

    public static ArrayList<Worker> getWorkers() {
        return workers;
    }
    public static void addWorker(Worker worker) {
        workers.add(worker);
    }

    public static void data(){
        Worker worker = new Worker("1","hung",18, 100.0,"c");
    }

}
