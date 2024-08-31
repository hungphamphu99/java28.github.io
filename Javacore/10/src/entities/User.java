package entities;

public class User {
    private int id;
    private static int nextId = 0;
    private String username;
    private String password;
    private String role;
    private String name;
    private String email;
    private String address;
    private String phone;

    public User(String username, String password, String role, String name, String email, String address, String phone) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.id = nextId++;
    }

    public boolean isAdmin() {
        return role.equals("admin");
    }

    public boolean isTeacher() {
        return role.equals("teacher");
    }

    public boolean isStudent() {
        return role.equals("student");
    }


    public int getId() {
        return id;
    }

    public static int getNextId() {
        return nextId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
