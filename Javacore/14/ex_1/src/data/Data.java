package data;


import entities.Student;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<Student> students = new ArrayList<>();


    public Data() {
        students.add(new Student( "Alice", 12,8.5));
        students.add(new Student( "Bob", 12,7.2));
        students.add(new Student( "Charlie", 12,6.0));
        students.add(new Student( "David",12, 9.0));
        students.add(new Student( "Eva",12, 5.5));
    }

}
