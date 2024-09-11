package lab_3.data;

import lab_3.entities.Worker;

import java.util.ArrayList;

public class Data {
    public static ArrayList<Worker> workers = new ArrayList<>();




    public static void data(){
        Worker worker = new Worker("1","hung",18, 100.0,"c");
        workers.add(worker);
    }

}
