package entities.universitymanagement;

import java.util.ArrayList;
import java.util.List;

public class Class {
    private String id;
    private Teacher teacher;
    private List<Student> students;
    private Subject subject;

    public Class(String id, Teacher teacher, Subject subject) {
        this.id = id;
        this.teacher = teacher;
        this.subject = subject;
        this.students = new ArrayList<>();
    }


    public String getId() {
        return id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Subject getSubject() {
        return subject;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    // Method to add a student to the class
    public void addStudent(Student student) {
        this.students.add(student);
    }

    // Method to remove a student from the class
    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    public String getClassName() {
        return this.id; // Assuming 'id' is the class name
    }


    public void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students enrolled in this class.");
        } else {
            for (Student student : students) {
                System.out.println(student.getName());
            }
        }
    }
}
