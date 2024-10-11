package view.universitymanagement;

import data.UniversityData;
import entities.login.User;
import entities.universitymanagement.Student;
import service.login.UserService;
import service.universitymanagement.ClassService;
import service.universitymanagement.StudentService;

import java.util.Scanner;

public class StudentMenu {
    Scanner scanner = new Scanner(System.in);
    ClassService classService = new ClassService();
    UserService userService = new UserService();
    StudentService studentService = new StudentService();
    public void display(User user) {
        Student student = (Student) user;
        while (true) {
            try {
                System.out.println("----- Student Menu -----");
                System.out.println("1. Information");
                System.out.println("2. Check Class ");
                System.out.println("3. Display Scores");
                System.out.println("4. Change Password");
                System.out.println("7. Log In");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("1. Information");
                        System.out.println(user.toString());
                        break;
                    case 2:
                        System.out.println("2. Check Class");
                        classService.displayStudentClassesAndTeachers(user);
                        break;
                    case 3:
                        System.out.println("4. Display Scores");
                        studentService.displayStudentScores(student);
                        break;
                    case 4:
                        System.out.println("3. Change Password");
                        System.out.println("Enter your new password");
                        String newPassword = scanner.nextLine();
                        userService.changePassword(student,newPassword);
                        if (!userService.isLoggedIn()) {
                           userService.logout();
                        }
                        break;

                    case 7:

                        userService.logout();
                        return;
                }
            }catch (NullPointerException e){
                System.out.println("Please enter a valid option");
            }


        }


    }
}
