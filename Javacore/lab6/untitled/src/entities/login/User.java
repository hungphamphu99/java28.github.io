package entities.login;

import data.UsersData;

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
        UsersData.addUser(this);
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

    public boolean isCustomer() {
        return role.equals("customer");
    }
    public boolean isStaff() {
        return role.equals("staff");
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



    public void setId(int id) {
        this.id = id;
    }

    public static void setNextId(int nextId) {
        User.nextId = nextId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
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


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
