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
        Student student = new Student("student", "student", "student", "Student Name", "student@example.com", "456 Student Street", "555-5678");
        Student student1 = new Student("student1", "student1", "student", "Student Name", "student@example.com", "456 Student Street", "555-5678");
        Student student2 = new Student("student2", "student2", "student", "Student Name", "student@example.com", "456 Student Street", "555-5678");
        Student student3 = new Student("student3", "student3", "student", "Student Name", "student@example.com", "456 Student Street", "555-5678");
        Student student4 = new Student("student4", "student4", "student", "Student Name", "student@example.com", "456 Student Street", "555-5678");


        Subject subject1 = new Subject("Math", 4, Enum.Type.TECH);
        Subject subject2 = new Subject("Eng", 4, Enum.Type.LANGUAGE);
        Subject subject3 = new Subject("Math02", 4, Enum.Type.TECH);
        Subject subject4 = new Subject("Math03", 4, Enum.Type.TECH);
        Subject subject5 = new Subject("Math04", 4, Enum.Type.TECH);
        Subject subject6 = new Subject("Math05", 4, Enum.Type.TECH);

        Class class1 = new Class("1", teacher2, subject1);
        Class class2 = new Class("2", teacher3, subject1);
        Class class3 = new Class("3", teacher4, subject2);
        Class class4 = new Class("4", teacher2, subject1);




        student.getSubjectScores().put(subject1, new Score(7.0,7.0, 5.6));
        student.getSubjectScores().put(subject2, new Score(7.0,8.0, 7.6));
        student.getSubjectScores().put(subject3, new Score(7.0,8.0, 7.6));
        student.getSubjectScores().put(subject4, new Score(7.0,8.0, 7.6));

        student.calculateAvgScore();

        teacher.getSubjects().add(subject5);
        teacher.getSubjects().add(subject6);

        teacher2.getSubjects().add(subject1);
        teacher3.getSubjects().add(subject1);
        teacher4.getSubjects().add(subject2);

        class1.addStudent(student);


    }

}