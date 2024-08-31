package Service;

import entities.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentService {
    public StudentService() {}

    Scanner scanner = new Scanner(System.in);
    public List<Student> addStudentList(Scanner scanner, List<Student> studentList){

        System.out.println("Enter student name: ");
        String name = scanner.nextLine();
        System.out.println("Enter student age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter student rank: ");
        String rank = scanner.nextLine();
        Student student = new Student(name,age, rank );
        studentList.add(student);
        return studentList;
    }


    public void findStudentByID( int id, List<Student> studentList){
        for (Student student : studentList) {
            if(student.getId() == id){
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student not found");
    }
    public void setRankStudentByID(int id, List<Student> studentList) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                System.out.println("Edit student rank (choose one of the following): ");
                int rankOption;
                while (true) {
                    System.out.println("1. Excellent");
                    System.out.println("2. Good");
                    System.out.println("3. Average");
                    System.out.print("Enter the rank (1, 2, or 3): ");

                    try {
                        rankOption = Integer.parseInt(scanner.nextLine());

                        switch (rankOption) {
                            case 1:
                                student.setRank("Excellent");
                                break;
                            case 2:
                                student.setRank("Good");
                                break;
                            case 3:
                                student.setRank("Average");
                                break;
                            default:
                                System.out.println("Invalid option. Please choose 1, 2, or 3.");
                                continue;
                        }
                        System.out.println("Student rank updated successfully!");
                        return;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                    }
                }
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }


    public void removeStudentByID( int id, List<Student> studentList){
        for (Student student : studentList) {
            if(student.getId() == id){
                studentList.remove(student);
                return;
            }
        }
        System.out.println("Student not found");
    }
}
