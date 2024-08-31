package entities.universitymanagement;


import entities.User;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private int id;
    private static int nextId = 0;
    private double midtermPoint;
    private double finalPoint;
    private double averageGrade;
    private List<Subject> subjects = new ArrayList<>();

    public Student(String username, String password, String role, String name, String email, String address, String phone) {
        super(username, password, role, name, email, address, phone);

        this.id = nextId++;
    }


    @Override
    public int getId() {
        return id;
    }

    public static int getNextId() {
        return nextId;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", averageGrade=" + averageGrade +
                ", enrolledSubjects=" + subjects +
                '}';
    }


    public void setId(int id) {
        this.id = id;
    }

    public static void setNextId(int nextId) {
        Student.nextId = nextId;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public boolean enrollSubject(Subject subject) {
        if (subjects.size() < 5) {  // Ensure the student is not enrolled in more than 5 subjects
            return subjects.add(subject);
        } else {
            return false;
        }
    }

    public boolean removeSubject(Subject subject) {
        return subjects.remove(subject);
    }

    public void setName(String newName) {
        this.setName(newName);
    }
}
