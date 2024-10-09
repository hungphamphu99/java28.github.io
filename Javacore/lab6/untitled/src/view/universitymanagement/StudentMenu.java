package view.universitymanagement;

import data.UniversityData;
import entities.login.User;
import service.login.UserService;
import service.universitymanagement.ClassService;

import java.util.Scanner;

public class StudentMenu {
    Scanner scanner = new Scanner(System.in);
    ClassService classService = new ClassService();
    UserService userService = new UserService();
    public void display(User user) {
        while (true) {
            try {
                System.out.println("----- Student Menu -----");
                System.out.println("1. Information");
                System.out.println("2. Check Class ");
                System.out.println("3. Check Subject");
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
                        System.out.println("3. Check Subject");
                        System.out.println(UniversityData.subjects);
                        break;
                    case 4:
                        System.out.println("3. Change Password");
                        System.out.println("Enter your new password");
                        String newPassword = scanner.nextLine();
                        userService.changePassword(newPassword);
                        break;
                    case 7:
                        System.out.println("Logout");
                        userService.logout();
                        return;
                }
            }catch (NullPointerException e){
                System.out.println("Please enter a valid option");
            }


        }


    }
}
