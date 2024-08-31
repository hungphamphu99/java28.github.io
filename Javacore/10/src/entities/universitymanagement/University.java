package entities.universitymanagement;

import java.util.ArrayList;
import java.util.List;

public class University {
    List<Student> students = new ArrayList<>();
    List<Teacher> teachers = new ArrayList<>();
    List<Class> classes = new ArrayList<>();
    List<Subject> subjects = new ArrayList<>();
    public University(List<Student> students, List<Teacher> teachers, List<Class> classes, List<Subject> subjects ) {
        this.students = students;
        this.teachers = teachers;
        this.classes = classes;
        this.subjects = subjects;
    }


}