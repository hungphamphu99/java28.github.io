import entities.User;
import entities.universitymanagement.Subject;
import entities.universitymanagement.Teacher;
import service.universitymanagement.StudentService;
import service.universitymanagement.SubjectService;
import service.universitymanagement.TeacherService;
import service.universitymanagement.UserService;
import entities.universitymanagement.Student;
import utils.Enum;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        StudentService studentService = new StudentService();

        TeacherService teacherService = new TeacherService();

        SubjectService subjectService = new SubjectService(studentService, teacherService);

        UserService userService = new UserService(studentService, subjectService, teacherService);

        User adminAccount = new User("admin", "admin", "admin", "Admin", "admin@admin.com", "Admin Address", "0000000000");
        User student1 = new Student("hung2024", "hung123", "student", "Hung", "email@example.com", "Some Address", "090");
        User teacher1 = new Teacher("teacher", "teacher","teacher","teacher", "email","address","09", Enum.Type.TECH);

        studentService.addStudent(student1);
        userService.addAccount(adminAccount);

        userService.addAccount(student1);
        userService.addAccount(teacher1);


        Subject subject1 = new Subject("Math", 4, Enum.Type.TECH);
        subjectService.addSubject(subject1);

        Subject subject2 = new Subject("Eng", 4, Enum.Type.LANGUAGE);
        subjectService.addSubject(subject2);


        userService.login();
    }
}
