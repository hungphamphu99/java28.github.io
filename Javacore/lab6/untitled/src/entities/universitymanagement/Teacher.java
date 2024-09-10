package entities.universitymanagement;

import data.UniversityData;
import entities.login.User;
import utils.Enum;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {
    private int id;
    private static int nextId = 0;
    private Enum.Type major;
    private List<Subject> subjects;
    public Teacher(String username, String password, String role, String name, String email, String address, String phone, Enum.Type major) {
        super(username, password, role, name, email, address, phone);
        this.id = ++nextId;
        this.major = major;
        this.subjects = new ArrayList<>();
        UniversityData.addTeacher(this);

    }

    public boolean canTeachMoreSubjects(Subject subject) {
        if (subjects.size() < 2) {
            return subjects.add(subject);
        } else {
            return false;
        }
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
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

    public void setName(String newName) {
        super.setName(newName);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name=" + getName() +
                ", email=" + getEmail() +
                ", address=" + getAddress() +
                ", phone=" + getPhone() +
                ", major=" + major +
                ", subjects=" + subjects +
                '}';
    }
    public  String infoTeacher(){
        return "Teacher{" +
                "id=" + id +
                ", name=" + getName() +
                ", email=" + getEmail() +
                ", address=" + getAddress() +
                ", phone=" + getPhone() +
                ", major=" + major +
                '}';
    }



}
