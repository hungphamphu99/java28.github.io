
package service.universitymanagement;

import entities.universitymanagement.Class;
import entities.universitymanagement.Teacher;
import entities.universitymanagement.Subject;
import entities.universitymanagement.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClassService {

    private List<Class> classes = new ArrayList<>();

    public void addClass(String className, Teacher teacher, Subject subject) {
        for (Class existingClass : classes) {
            if (existingClass.getClassName().equals(className)) {
                System.out.println("Class already exists.");
                return;
            }
        }
        // Create a new class with teacher and subject
        Class newClass = new Class(className, teacher, subject);
        // Add the new class to the list of classes
        classes.add(newClass);
        System.out.println("Class created successfully.");
    }


    public void addStudentToClass(String className, Student student) {
        for (Class existingClass : classes) {
            if (existingClass.getClassName().equals(className)) {
                if (existingClass.getStudents().contains(student)) {
                    System.out.println("Student is already in the class.");
                    return;
                }
                existingClass.addStudent(student);
                System.out.println("Student added successfully.");
                return;
            }
        }
        System.out.println("Class not found.");
    }

    public void addStudentsToClassByIdRange(String className, List<Student> allStudents, int idStart, int idEnd) {
        for (Class existingClass : classes) {
            if (existingClass.getClassName().equals(className)) {
                List<Student> studentsToAdd = allStudents.stream()
                        .filter(student -> student.getId() >= idStart && student.getId() <= idEnd)
                        .collect(Collectors.toList());
                for (Student student : studentsToAdd) {
                    if (existingClass.getStudents().contains(student)) {
                        System.out.println("Student with ID " + student.getId() + " is already in the class.");
                    } else {
                        existingClass.addStudent(student);
                    }
                }
                System.out.println("Students added successfully.");
                return;
            }
        }
        System.out.println("Class not found.");
    }

    private boolean canAddTeacherToClass(Class existingClass, Teacher teacher) {
        if (existingClass.getTeacher() != null) {
            System.out.println("This class already has a teacher.");
            return false;
        }
        long subjectCount = classes.stream()
                .filter(cls -> cls.getTeacher().equals(teacher))
                .map(cls -> cls.getSubject())
                .distinct()
                .count();
        if (subjectCount >= 2) {
            System.out.println("This teacher is already teaching 2 subjects.");
            return false;
        }
        return true;
    }

    public void addTeacherToClass(String className, Teacher teacher) {
        for (Class existingClass : classes) {
            if (existingClass.getClassName().equals(className)) {
                if (canAddTeacherToClass(existingClass, teacher)) {
                    existingClass.setTeacher(teacher);
                    System.out.println("Teacher added successfully.");
                }
                return;
            }
        }
        System.out.println("Class not found.");
    }

    public void addSubjectToClass(String className, Subject subject) {
        for (Class existingClass : classes) {
            if (existingClass.getClassName().equals(className)) {
                if (existingClass.getSubject() != null) {
                    System.out.println("This class already has a subject.");
                    return;
                }
                existingClass.setSubject(subject);
                System.out.println("Subject added successfully.");
                return;
            }
        }
        System.out.println("Class not found.");
    }
}
