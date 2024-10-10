package view.universitymanagement;

import entities.login.User;
import entities.universitymanagement.Teacher;
import service.universitymanagement.StudentService;
import service.universitymanagement.TeacherService;

import java.util.Scanner;

public class TeacherMenu {
    Scanner scanner = new Scanner(System.in);
    StudentService studentService = new StudentService();
    TeacherService teacherService = new TeacherService();

    public void display(User user) {
        Teacher teacher = (Teacher) user;
        while (true) {
            System.out.println("----- Teacher Menu -----");
            System.out.println("1. View Assigned Subjects");
            System.out.println("2. Edit Student Grades");
            System.out.println("3. Display Classes");
            System.out.println("4. Edit Score");
            System.out.println("5. Log Out");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        viewAssignedSubjects(teacher);
                        break;
                    case 2:
                        studentService.inputScore();
                        break;
                    case 3:
                        System.out.println("3. Display Classes");
                        teacherService.displayClassesAndStudents(teacher);
                        break;
                    case 4:
                        System.out.println("Edit Score");
                        studentService.editScore();
                        break;
                    case 5:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }


    private void viewAssignedSubjects(Teacher teacher) {
        System.out.println("Displaying assigned subjects...");
        System.out.println(teacher.getSubjects());
    }

    private void editStudentGrades() {
       while (true){
           try {
               System.out.println("Edit Score");
               int choice = Integer.parseInt(scanner.nextLine());
               switch (choice) {
                   case 1:
                       System.out.println("Edit Score");
                       studentService.inputScore();
                       break;
               }
           }catch (NumberFormatException e){
               System.out.println("Invalid choice. Please try again.");
           }

       }

    }
}
