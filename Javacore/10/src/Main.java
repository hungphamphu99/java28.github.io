import Service.StudentService;
import entities.*;
import view.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        Manager manager = new Manager("Thinh");

        Lecturer lecturer = new Lecturer("Dat");
        Student student = new Student("uwu",20,"uwu");

        students.add(student);
        Class_1 class1 = new Class_1("uwu4",students);
        CentralTeaching centralTeaching = new CentralTeaching(manager,class1);
        StudentService studentService = new StudentService();
        Menu newMenu = new Menu();
        newMenu.menu(centralTeaching, students, studentService);
    }
}