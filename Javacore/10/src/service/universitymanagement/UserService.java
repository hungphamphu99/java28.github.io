package service.universitymanagement;

import View.Menu;
import entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private Menu menu;
    private List<User> accounts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private User loggedInUser;

    public UserService(StudentService studentService, SubjectService subjectService, TeacherService teacherService) {
        this.menu = new Menu(studentService, subjectService,teacherService);
    }

    public void addAccount(User account) {
        accounts.add(account);
    }

    public void login() {
        System.out.println("Enter Username:");
        String username = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        for (User account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                loggedInUser = account;
                System.out.println("Login Successful");
                if (account.isAdmin()) {
                    System.out.println("Welcome Admin");
                    menu.adminMenu();
                } else if (account.isTeacher()) {
                    System.out.println("Welcome Teacher");

                } else if (account.isStudent()) {
                    System.out.println("Welcome Student");
                    menu.studentMenu();
                }
                return;
            }
        }
        System.out.println("Wrong Username or Password");
    }

    public void logout() {
        if (loggedInUser != null) {
            System.out.println("Logging out...");
            loggedInUser = null;
            System.out.println("Logged out successfully. Redirecting to login...");
            login();
        } else {
            System.out.println("No user is currently logged in.");
        }
    }
}
