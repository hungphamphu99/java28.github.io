package service.login;

import data.UsersData;
import entities.login.User;
import entities.salesmanagement.Customer;

import java.util.Scanner;
import java.util.regex.Pattern;

public class UserService {
    private User loggedInUser = null;
    private final Scanner scanner = new Scanner(System.in);

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[!@#$])[A-Za-z\\d!@#$]{7,15}$";

    public boolean registerUser() {
        String username = promptInput("Enter username: ");
        if (isUsernameTaken(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return false;
        }

        String email = promptInput("Enter email: ");
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return false;
        }
        if (isEmailTaken(email)) {
            System.out.println("Email already exists. Please choose a different email.");
            return false;
        }

        String password = promptInput("Enter password: ");
        if (!isValidPassword(password)) {
            System.out.println("Invalid password. Must be 7-15 characters long, contain at least 1 uppercase letter, and 1 special character (! @ # $).");
            return false;
        }

        String name = promptInput("Enter your name: ");
        String address = promptInput("Enter your address: ");
        String phone = promptInput("Enter your phone: ");

        String role = "customer";

        User newUser = new Customer(username, password, role, name, email, address, phone);
        UsersData.addUser(newUser);

        System.out.println("Registration successful! Role assigned: " + role);
        return true;
    }

    public boolean loginUser(String username, String password) {
        for (User user : UsersData.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Login successful. Welcome " + user.getUsername() + "!");
                return true;
            }
        }
        System.out.println("Invalid username or password.");
        return false;
    }

    public void changeUsername(String newUsername) {
        if (loggedInUser == null) {
            System.out.println("No user is logged in.");
            return;
        }
        if (isUsernameTaken(newUsername)) {
            System.out.println("This username already exists. Please choose a different username.");
        } else {
            loggedInUser.setUsername(newUsername);
            System.out.println("Username successfully changed.");
        }
    }

    public void changeEmail(String newEmail) {
        if (loggedInUser == null) {
            System.out.println("No user is logged in.");
            return;
        }
        if (!isValidEmail(newEmail)) {
            System.out.println("Invalid email format.");
        } else if (isEmailTaken(newEmail)) {
            System.out.println("This email is already taken.");
        } else {
            loggedInUser.setEmail(newEmail);
            System.out.println("Email successfully changed.");
        }
    }

    public void changePassword(User user, String newPassword) {
        if (user == null) {
            System.out.println("No user is logged in.");
            return;
        }
        if (!isValidPassword(newPassword)) {
            System.out.println("Invalid password. Must be 7-15 characters long, contain at least 1 uppercase letter, and 1 special character (! @ # $).");
        } else {
            user.setPassword(newPassword);
            System.out.println("Password successfully changed.");
            logout();
        }
    }


    public boolean forgotPassword(String username, String email) {
        for (User user : UsersData.getUsers()) {
            if (user.getUsername().equals(username) && user.getEmail().equals(email)) {
                String newPassword = promptInput("Enter your new password: ");
                if (isValidPassword(newPassword)) {
                    user.setPassword(newPassword);
                    System.out.println("Password reset successful. You can log in again.");
                    return true;
                } else {
                    System.out.println("Invalid password. Must be 7-15 characters long, contain at least 1 uppercase letter, and 1 special character (! @ # $).");
                    return false;
                }
            }
        }
        System.out.println("Username or email is incorrect.");
        return false;
    }

    public void logout() {
        if (loggedInUser != null) {
            System.out.println("User " + loggedInUser.getUsername() + " successfully logged out.");
            loggedInUser = null;
        } else {
            System.out.println("No user is logged in.");

        }
    }


    private boolean isUsernameTaken(String username) {
        return UsersData.getUsers().stream().anyMatch(user -> user.getUsername().equals(username));
    }

    private boolean isEmailTaken(String email) {
        return UsersData.getUsers().stream().anyMatch(user -> user.getEmail().equals(email));
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    private boolean isValidPassword(String password) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }

    private String promptInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
