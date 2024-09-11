package data;

import entities.universitymanagement.*;
import entities.universitymanagement.Class;
import utils.Enum;

import java.util.ArrayList;
import java.util.List;

public class UniversityData {
    public static List<Student> students = new ArrayList<>();
    public static List<Teacher> teachers = new ArrayList<>();
    public static List<Class> classes = new ArrayList<>();
    public static List<Subject> subjects = new ArrayList<>();
    public UniversityData() {

    }

    public static List<Student> getStudents() {
        return students;
    }

    public static List<Teacher> getTeachers() {
        return teachers;
    }

    public static List<Class> getClasses() {
        return classes;
    }

    public static List<Subject> getSubjects() {
        return subjects;
    }

    public static void setStudents(List<Student> students) {
        UniversityData.students = students;
    }

    public static void setTeachers(List<Teacher> teachers) {
        UniversityData.teachers = teachers;
    }

    public static void setClasses(List<Class> classes) {
        UniversityData.classes = classes;
    }

    public static void setSubjects(List<Subject> subjects) {
        UniversityData.subjects = subjects;
    }


    public static void removeStudent(Student studentToRemove) {
        students.remove(studentToRemove);
    }
    public static void removeTeacher(Teacher teacherToRemove) {
        teachers.remove(teacherToRemove);
    }
    public static void removeClass(Class classToRemove) {
        classes.remove(classToRemove);
    }
    public static void addStudent(Student studentToAdd) {
        students.add(studentToAdd);
    }
    public static void addTeacher(Teacher teacherToAdd) {
        teachers.add(teacherToAdd);

    }
    public static void addClass(Class classToAdd) {
        classes.add(classToAdd);
    }
    public static void addSubject(Subject subjectToAdd) {
        subjects.add(subjectToAdd);
    }

    public static void removeSubject(Subject subjectToRemove) {
        subjects.remove(subjectToRemove);
    }

    public static void initialize() {
        Teacher teacher = new Teacher("teacher", "teacher", "teacher", "Teacher 1", "teacher@example.com", "123 Teacher Street", "555-1234", Enum.Type.TECH);
        Teacher teacher2 = new Teacher("teacher2", "teacher2", "teacher", "Teacher 2", "teacher2@example.com", "123 Teacher Street", "555-1234", Enum.Type.TECH);
        Teacher teacher3 = new Teacher("teacher3", "teacher3", "teacher", "Teacher 3", "teacher3@example.com", "123 Teacher Street", "555-1234", Enum.Type.TECH);
        Teacher teacher4 = new Teacher("teacher4", "teacher4", "teacher", "Teacher 4", "teacher4@example.com", "123 Teacher Street", "555-1234", Enum.Type.LANGUAGE);


        Subject subject1 = new Subject("Math", 4, Enum.Type.TECH);
        Subject subject2 = new Subject("Eng", 4, Enum.Type.LANGUAGE);


        Class class1 = new Class("1", teacher2, subject1);
        Class class2 = new Class("2", teacher3, subject1);
        Class class3 = new Class("3", teacher4, subject2);



        Student student = new Student("student", "student", "student", "Student Name", "student@example.com", "456 Student Street", "555-5678");
        student.getSubjectScores().put(subject1, new Score(7.0, 7.0, 5.6));
        student.getSubjectScores().put(subject2, new Score(7.0, 8.0, 7.6));
        student.calculateAvgScore();


    }

}