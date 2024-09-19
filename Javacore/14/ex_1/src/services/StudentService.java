package services;

import entities.Student;

import java.util.List;

public class StudentService {
    public void displayAllStudents() {

        for (Student student : data.Data.students) {
            student.display();
            System.out.println("--------------------");
        }
    }
}
