package service;

import entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserService {
    private List<User> users = new ArrayList<>();
    private User loggedInUser = null;
    Scanner scanner = new Scanner(System.in);

    public boolean registerUser(String username, String email, String password) {
        boolean usernameExists = isUsernameTaken(username);
        boolean emailExists = isEmailTaken(email);

        if (usernameExists && emailExists) {
            System.out.println("Username and email already exist. Please choose a different username and email.");
            return false;
        } else if (usernameExists) {
            System.out.println("Username already exists. Please choose a different username.");
            return false;
        } else if (emailExists) {
            System.out.println("Email already exists. Please choose a different email.");
            return false;
        }

        if (!isValidEmail(email)) {
            System.out.println("Invalid email.");
            return false;
        }

        if (!isValidPassword(password)) {
            System.out.println("Invalid password. Must be 7-15 characters long, contain at least 1 uppercase letter, and 1 special character (. , - _ ;).");
            return false;
        }

        users.add(new User(username, email, password));
        System.out.println("Registration successful!");
        return true;
    }

    public boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    loggedInUser = user;
                    System.out.println("Welcome " + user.getUsername() + ", you can perform the following tasks:");
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void changeUsername(String newUsername) {
        if (!isUsernameTaken(newUsername)) {
            loggedInUser.setUsername(newUsername);
            System.out.println("Username successfully changed.");
        } else {
            System.out.println("This username already exists. Please choose a different username.");
        }
    }

    public void changeEmail(String newEmail) {
        if (!isEmailTaken(newEmail) && isValidEmail(newEmail)) {
            loggedInUser.setEmail(newEmail);
            System.out.println("Email successfully changed.");
        } else {
            System.out.println("This email already exists or is invalid.");
        }
    }

    public void changePassword(String newPassword) {
        if (isValidPassword(newPassword)) {
            loggedInUser.setPassword(newPassword);
            System.out.println("Password successfully changed.");
        } else {
            System.out.println("Invalid password. Must be 7-15 characters long, contain at least 1 uppercase letter, and 1 special character (. , - _ ;).");
        }
    }

    public boolean forgotPassword(String username, String email) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getEmail().equals(email)) {
                System.out.print("Enter your new password: ");
                String newPassword = scanner.nextLine();

                if (isValidPassword(newPassword)) {
                    user.setPassword(newPassword);
                    System.out.println("Password reset successful. You can log in again.");
                    return true;
                } else {
                    System.out.println("Invalid password. Must be 7-15 characters long, contain at least 1 uppercase letter, and 1 special character (. , - _ ;).");
                    return false;
                }
            }
        }
        System.out.println("Username or email is incorrect.");
        return false;
    }


    public void logout() {
        loggedInUser = null;
        System.out.println("Successfully logged out.");
    }

    private boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEmailTaken(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*[.\\-_,;])[A-Za-z\\d.\\-_,;]{7,15}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
