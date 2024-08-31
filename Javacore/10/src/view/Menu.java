package view;

import Service.StudentService;
import entities.CentralTeaching;
import entities.Student;

import java.util.List;
import java.util.Scanner;

public class Menu {

    public void menu( CentralTeaching teaching, List<Student> studentList , StudentService studentService) {


        Scanner scanner = new Scanner(System.in);

        int option;
        while ( true ) {
            System.out.println("Welcome to the  menu!");

            System.out.println("1. Display CentralTeacher Information");
            System.out.println("2. ADD Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Edit Rank");
            System.out.println("5. Exit");
            System.out.println("Please select one of the following options:");
            try{
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        System.out.println("1. Display CentralTeacher Information");
                        System.out.println(teaching.toString());
                        break;
                    case 2:
                        System.out.println("2. ADD Student");
                        studentService.addStudentList(scanner,studentList);
                        break;
                    case 3:
                        System.out.println("3. Delete Student");
                        System.out.println("Enter Student ID");
                        int id= Integer.parseInt(scanner.next());
                        studentService.removeStudentByID(id,studentList);
                        break;
                    case 4:
                        System.out.println("4. Edit Rank");
                        System.out.println("Enter the id of the student you would like to edit");
                        id = Integer.parseInt(scanner.next());
                        studentService.setRankStudentByID(id,studentList);
                        break;
                    case 5:
                        System.out.println("5. Exit");
                        System.exit(0);


                }
            }catch (NumberFormatException e){
                System.out.println("Please enter a valid number");
            }
            }



    }
}
