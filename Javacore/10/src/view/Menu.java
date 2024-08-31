package View;

import entities.universitymanagement.Subject;
import entities.universitymanagement.Teacher;
import service.universitymanagement.StudentService;
import service.universitymanagement.SubjectService;
import service.universitymanagement.TeacherService;
import service.universitymanagement.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private TeacherService teacherService;
    private StudentService studentService;
    private SubjectService subjectService;
    private UserService userService;
    private List<Teacher> teachers = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public Menu(StudentService studentService, SubjectService subjectService, TeacherService teacherService) {
        this.studentService = studentService;
        this.subjectService = subjectService;

        this.teacherService = teacherService;
    }



    public void editStudentMenu() {
        while (true) {
            System.out.println("Edit Student Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student By ID");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student By ID");
            System.out.println("5. Add student to the subjects");
            System.out.println("6. Back to Main Menu");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("1. Add Student");
                    studentService.addStudentByAdmin();
                    break;
                case 2:
                    System.out.println("2. Update Student By ID");
                    System.out.print("Enter student ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    studentService.updateStudentById(id);
                    break;
                case 3:
                    System.out.println("3. Delete Student by ID");
                    System.out.print("Enter student ID: ");
                    id = Integer.parseInt(scanner.nextLine());
                    studentService.deleteStudentById(id);
                    break;
                case 4:
                    System.out.println("4. Search Student by ID");
                    System.out.print("Enter student ID: ");
                    id = Integer.parseInt(scanner.nextLine());
                    studentService.searchStudentById(id);
                    break;
                case 5:
                    System.out.println("5. Add student to the subjects");
                    System.out.print("Enter student ID: ");
                    id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter subject name: ");
                    String name = scanner.nextLine();
                    Subject subject = subjectService.findSubjectByName(name);
                    if (subject != null) {
                        studentService.enrollStudentInSubject(id, subject);
                    } else {
                        System.out.println("Subject not found!");
                    }
                    break;
                case 6:
                    adminMenu();
                    return;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void editTeacherMenu() {
        while (true) {
            System.out.println("Edit Teacher Menu:");
            System.out.println("1. Add Teacher");
            System.out.println("2. Update Teacher By ID");
            System.out.println("3. Delete Teacher");
            System.out.println("4. Search Teacher By ID");
            System.out.println("5. Add Teacher to the subjects");
            System.out.println("6. Back to Main Menu");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("1. Add Teacher");
                    teacherService.addTeacherByAdmin(teachers);
                    break;



                case 2:
                    System.out.println("2. Update Teacher By ID");
                    System.out.print("Enter Teacher ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    teacherService.updateTeacherById(id,teachers);

                    break;
                case 3:
                    System.out.println("3. Delete Teacher by ID");
                    System.out.print("Enter student ID: ");
                    id = Integer.parseInt(scanner.nextLine());
                    teacherService.deleteTeacherById(id,teachers);


                    break;
                case 4:
                    System.out.println("4. Search Teacher by ID");
                    System.out.print("Enter student ID: ");
                    id = Integer.parseInt(scanner.nextLine());
                    teacherService.searchTeacherById(id,teachers);


                    break;
                case 5:
                    System.out.println("5. Add Teacher to the subjects");
                    System.out.print("Enter Teacher ID: ");
                    id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter subject ID: ");
                    int subjectId = Integer.parseInt(scanner.nextLine());
                    teacherService.assignSubjectsToTeacher(id, subjectService.findSubjectById(subjectId),teachers);

                    break;
                case 6:
                    adminMenu();
                    return;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void adminMenu() {
        while (true) {
            System.out.println("Welcome to the School Management System");
            System.out.println("1. Edit Student");
            System.out.println("2. View Students");
            System.out.println("3. Edit Subject");
            System.out.println("4. View Subject");
            System.out.println("5. Edit Teacher");
            System.out.println("6. View Teacher");
            System.out.println("7. Log out ");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    editStudentMenu();
                    break;
                case 2:
                    studentService.displayAllStudents();
                    break;
                case 3:
                    editSubjectMenu();
                    break;
                case 4:
                    subjectService.displayAllSubjects();
                    break;
                case 5:
                    editTeacherMenu();
                    break;
                case 6:
                    teacherService.displayAllTeachers(teachers);
                    break;
                case 7:
                    userService.logout();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void  editSubjectMenu() {
        while (true) {
            System.out.println("Edit Subject Menu:");
            System.out.println("1. Add Subject");
            System.out.println("2. Update Subject");
            System.out.println("3. Delete Subject");
            System.out.println("4. Search Subject by ID");
            System.out.println("5. Back to Main Menu");
            System.out.print("6. Log out ");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("1. Add Subject");
                    subjectService.addSubjectByAdmin();
                    break;
                case 2:
                    System.out.println("2. Update Subject");
                    subjectService.updateSubject();
                    break;
                case 3:
                    System.out.println("3. Delete Subject");
                    System.out.print("Enter subject ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    subjectService.deleteSubjectById(id);
                    break;
                case 4:
                    System.out.println("4. Search Subject by ID");
                    subjectService.searchSubjectById();
                    break;
                case 5:
                    System.out.println("5. Back to Main Menu");
                    adminMenu();
                    break;
                case 6:
                    System.out.print("6. Log out ");
                    userService.logout();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public void studentMenu() {
        while (true) {
            System.out.println("Welcome to the School Management System (Student)");
            System.out.println("1. View Subjects");
            System.out.println("2. View Grades");
            System.out.println("3. Log out");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    subjectService.displayAllSubjects();
                    break;
                case 2:
                    // Display student grades (implement this functionality)
                    break;
                case 3:
                    userService.logout();
                    return;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
