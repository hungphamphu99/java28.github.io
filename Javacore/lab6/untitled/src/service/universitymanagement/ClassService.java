package service.universitymanagement;

import data.UniversityData;
import entities.login.User;
import entities.universitymanagement.Class;
import entities.universitymanagement.Student;
import entities.universitymanagement.Subject;
import entities.universitymanagement.Teacher;

import java.util.List;
import java.util.Scanner;

public class ClassService {
    TeacherService teacherService = new TeacherService();
    SubjectService subjectService = new SubjectService();
    StudentService studentService = new StudentService();

    Scanner scanner = new Scanner(System.in);

    public void addClass() {
        System.out.println("Enter Class ID:");
        String classID = scanner.nextLine();
        Class existingClass = findClassByID(classID);
        if (existingClass != null) {
            System.out.println("Class already exists.");
            return;
        }
        System.out.println("Enter Teacher ID:");
        int teacherID = Integer.parseInt(scanner.nextLine());
        Teacher teacher = teacherService.findTeacherById(teacherID);
        if (teacher == null) {
            System.out.println("Teacher Not Found.");
            return;
        }

        System.out.println("Enter Subject ID:");
        int subjectID = Integer.parseInt(scanner.nextLine());
        Subject subject = subjectService.findSubjectByID(subjectID);
        if (subject == null) {
            System.out.println("Subject Not Found.");
            return;
        }

        if (!teacher.getMajor().equals(subject.getType())) {
            System.out.println("Teacher's major does not match the subject type.");
            return;
        }
        if (teacher.getSubjects().contains(subject)) {
            System.out.println("Class created successfully with Teacher ID: " + teacherID + " and Subject ID: " + subjectID);
            new Class(classID, teacher, subject);
        } else {
            if (teacher.getSubjects().size() < 2) {
                teacher.addSubject(subject);
                new Class(classID, teacher, subject);
            } else {
                System.out.println("Teachers can only teach 2 subjects.");
            }
        }
    }

    public Class findClassByID(String classID) {
        for (Class classes : UniversityData.classes) {
            if (classes.getId().equals(classID)) {
                return classes;
            }
        }
        return null;
    }

    public void displayAllClasses() {
        if (UniversityData.classes.isEmpty()) {
            System.out.println("No classes available.");
            return;
        }

        for (Class cl : UniversityData.classes) {
            System.out.println("Class ID: " + cl.getId());
            Teacher teacher = cl.getTeacher();
            System.out.println("Teacher: " + teacher.getName() + " (Major: " + teacher.getMajor() + ")");
            Subject subject = cl.getSubject();
            System.out.print("Subjects: ");
            System.out.print(subject.getName() + " (ID: " + subject.getId() + "), ");

            if (!cl.getStudents().isEmpty()) {
                System.out.println("Students:");
                for (Student student : cl.getStudents()) {
                    System.out.println("  - ID: " + student.getId() + ", Name: " + student.getName());
                }
            } else {
                System.out.println("No students enrolled in this class.");
            }

            System.out.println("-----------------------------------------");
        }
    }

    public void addStudentClass() {
        System.out.println("Enter Class ID:");
        String classID = scanner.nextLine();
        Class existingClass = findClassByID(classID);
        if (existingClass == null) {
            System.out.println("Class does not exist.");
            return;
        }

        System.out.println("Enter Student ID:");
        int studentID = Integer.parseInt(scanner.nextLine());
        Student student = studentService.findStudentByID(studentID);
        if (student == null) {
            System.out.println("Student does not exist.");
            return;
        }

        if (existingClass.getStudents().contains(student)) {
            System.out.println("Student is already enrolled in this class.");
            return;
        }

        if (student.getSubjectScores().keySet().contains(existingClass.getSubject())) {
            existingClass.addStudent(student);
            System.out.println("Student added successfully to Class ID: " + classID);
        } else if (student.getSubjectScores().size() >= 5) {
            System.out.println("Student with ID " + studentID + " is already enrolled in 5 subjects and cannot enroll in more.");
        } else {
            student.getSubjectScores().put(existingClass.getSubject(), null); // Add subject without score
            existingClass.addStudent(student);
            System.out.println("Student added successfully to Class ID: " + classID);
        }
    }

    public void removeStudentClass() {
        System.out.println("Enter Class ID:");
        String classID = scanner.nextLine();
        Class existingClass = findClassByID(classID);
        if (existingClass == null) {
            System.out.println("Class does not exist.");
            return;
        }
        System.out.println("Enter Student ID:");
        int studentID = Integer.parseInt(scanner.nextLine());
        Student student = studentService.findStudentByID(studentID);
        if (student == null) {
            System.out.println("Student does not exist.");
            return;
        }
        if (existingClass.getStudents().contains(student)) {
            existingClass.getStudents().remove(student);
            System.out.println("Student removed successfully from Class ID: " + classID);
        } else {
            System.out.println("Student is not enrolled in this class.");
        }
    }

    public void deleteClassByID() {
        System.out.println("Enter Class ID:");
        String classID = scanner.nextLine();
        Class existingClass = findClassByID(classID);
        if (existingClass == null) {
            System.out.println("Class does not exist.");
            return;
        }
        UniversityData.classes.remove(existingClass);
        System.out.println("Class with ID: " + classID + " has been deleted successfully.");
    }

    public void changeTeacherInClass() {
        System.out.println("Enter Class ID:");
        String classID = scanner.nextLine();
        Class existingClass = findClassByID(classID);
        if (existingClass == null) {
            System.out.println("Class does not exist.");
            return;
        }
        System.out.println("Enter New Teacher ID:");
        int teacherID = Integer.parseInt(scanner.nextLine());
        Teacher newTeacher = teacherService.findTeacherById(teacherID);
        if (newTeacher == null) {
            System.out.println("Teacher ID not found.");
            return;
        }
        Subject classSubject = existingClass.getSubject();

        if (!newTeacher.getMajor().equals(classSubject.getType())) {
            System.out.println("New teacher's major does not match the subject type.");
            return;
        }
        if (!newTeacher.getSubjects().contains(classSubject) && newTeacher.getSubjects().size() >= 2) {
            System.out.println("Teacher cannot teach more than 2 subjects.");
        } else if (!newTeacher.getSubjects().contains(classSubject) && newTeacher.getSubjects().size() < 2) {
            newTeacher.addSubject(classSubject);
            existingClass.setTeacher(newTeacher);
            System.out.println("Teacher has been changed successfully for Class ID: " + classID);
        } else if (newTeacher.getSubjects().contains(classSubject)) {
            existingClass.setTeacher(newTeacher);
            System.out.println("Teacher has been changed successfully for Class ID: " + classID);
        }
    }

    public void addStudentRangeByID() {
        System.out.println("Enter Class ID:");
        String classID = scanner.nextLine();
        Class existingClass = findClassByID(classID);
        if (existingClass == null) {
            System.out.println("Class does not exist.");
            return;
        }

        System.out.println("Enter starting Student ID (a):");
        int startID = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter ending Student ID (b):");
        int endID = Integer.parseInt(scanner.nextLine());

        if (startID >= endID) {
            System.out.println("Invalid input: Starting Student ID must be less than ending Student ID.");
            return;
        }

        for (int studentID = startID; studentID <= endID; studentID++) {
            Student student = studentService.findStudentByID(studentID);
            if (student == null) {
                System.out.println("Student with ID " + studentID + " does not exist.");
                continue;
            }

            if (existingClass.getStudents().contains(student)) {
                System.out.println("Student with ID " + studentID + " is already enrolled in this class.");
                continue;
            }

            if (student.getSubjectScores().size() >= 5 && !student.getSubjectScores().keySet().contains(existingClass.getSubject())) {
                System.out.println("Student with ID " + studentID + " is already enrolled in 5 subjects and cannot enroll in more.");
                continue;
            }

            if (!student.getSubjectScores().keySet().contains(existingClass.getSubject())) {
                student.getSubjectScores().put(existingClass.getSubject(), null); // Add subject without score
            }

            existingClass.addStudent(student);
            System.out.println("Student with ID " + studentID + " added successfully to Class ID: " + classID);
        }
    }

    public void displayStudentClassesAndTeachers(User studentUser) {
        if (!studentUser.isStudent()) {
            System.out.println("This functionality is only available for students.");
            return;
        }

        List<Class> classes = UniversityData.classes;
        boolean isEnrolled = false;

        System.out.println("Classes for Student: " + studentUser.getName() + " (ID: " + studentUser.getId() + ")");

        for (Class cl : classes) {
            for (Student student : cl.getStudents()) {
                if (student.getId() == studentUser.getId()) {
                    isEnrolled = true;
                    Teacher teacher = cl.getTeacher();
                    System.out.println("Class ID: " + cl.getId() + ", Subject: " + cl.getSubject().getName());
                    System.out.println("Teacher: " + teacher.getName() + " (ID: " + teacher.getId() + ")");
                    System.out.println("------------------------------------------");
                    break;
                }
            }
        }

        if (!isEnrolled) {
            System.out.println("This student is not enrolled in any classes.");
        }
    }
}
