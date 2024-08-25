package service;

import entities.Student;

import java.util.Scanner;

import static entities.Student.studentList;


public class StudentService {
    Scanner sc = new Scanner(System.in);

    public Student inputStudent() {
        int age = 0;
        String gender ;
        int choice ;
        String name ;
        while(true){
            try {
                System.out.println("Enter the name of the student:");
                name = sc.nextLine();
                if (!name.trim().isEmpty()) {
                    break;
                } else {
                    System.out.println("Please enter a valid name.");
                }
            }catch (Exception e){
                System.out.println("Please enter a valid name.");
            }
        }


        while (age <=1) {
            try {
                System.out.println("Enter the age of the student:");
                age = Integer.parseInt(sc.nextLine());
                if (age <=0 ) {
                    System.out.println("Invalid Age");
                }
            }catch (NumberFormatException e) {
                System.out.println("invalid input");

            }

        }


        while (true) {
            try {
                System.out.println("Choose the gender:");
                System.out.println("1 - Male");
                System.out.println("2 - Female");
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        gender = "Male";
                        break;
                    case 2:
                        gender = "Female";
                        break;
                    default:
                        System.out.println("Invalid choice, please choose 1 or 2.");
                        continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }

        Student student = new Student(name, age, gender);
        studentList.add(student);

        return student;
    }
    public void printStudents() {
        System.out.println("List of students:");
        for (Student student : Student.getStudentList()) {
            System.out.println(student);
        }
    }

}
