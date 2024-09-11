package data;

import entities.login.User;

import java.util.ArrayList;
import java.util.List;

public class UsersData {
    private static List<User> users = new ArrayList<>();
    public UsersData(){

    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        UsersData.users = users;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void  initialize(){
        User admin = new User("admin", "admin", "admin", "Admin Name", "admin@example.com", "123 Admin Street", "555-1234");
    }
}
