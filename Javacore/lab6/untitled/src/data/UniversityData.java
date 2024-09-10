package data;

import entities.universitymanagement.Class;
import entities.universitymanagement.Student;
import entities.universitymanagement.Subject;
import entities.universitymanagement.Teacher;

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

}