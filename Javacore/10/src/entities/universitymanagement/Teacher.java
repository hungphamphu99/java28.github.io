package entities.universitymanagement;

import entities.User;
import utils.Enum;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {
    private int id;
    private static int nextId = 0;
    private Enum.Type major;
    private List<Subject> subjects;

    // Updated constructor to accept Enum.Type for major
    public Teacher(String username, String password, String role, String name, String email, String address, String phone, Enum.Type major) {
        super(username, password, role, name, email, address, phone);

        this.id = nextId++;
        this.major = major;
        this.subjects = new ArrayList<>();
    }

    public boolean canTeachMoreSubjects() {
        return subjects.size() < 2;
    }

    public boolean addSubject(Subject subject) {
        if (canTeachMoreSubjects()) {
            return subjects.add(subject);
        } else {
            return false;
        }
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public int getId() {
        return id;
    }

    public static int getNextId() {
        return nextId;
    }

    public Enum.Type getMajor() {
        return major;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void setNextId(int nextId) {
        Teacher.nextId = nextId;
    }

    public void setMajor(Enum.Type major) {
        this.major = major;
    }

    // Using the setName method from User to set the name

    public void setName(String newName) {
        this.setName(newName);
    }
}
