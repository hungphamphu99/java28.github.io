package view.universitymanagement;

import data.UniversityData;
import service.login.UserService;
import service.salesmanagement.StaffService;
import service.universitymanagement.ClassService;
import service.universitymanagement.StudentService;
import service.universitymanagement.SubjectService;
import service.universitymanagement.TeacherService;

import java.util.Scanner;

public class AdminMenu {
    Scanner scanner = new Scanner(System.in);
    ClassService classService = new ClassService();
    SubjectService subjectService = new SubjectService();
    StudentService studentService = new StudentService();
    TeacherService teacherService = new TeacherService();
    UserService userService = new UserService();
    StaffService staffService = new StaffService();
    public void display() {
        System.out.println("----- Admin Menu -----");
        while (true){
            try{
                System.out.println("1. Edit Subject");
                System.out.println("2. Edit Student");
                System.out.println("3. Edit Teacher");
                System.out.println("4. Edit Class");
                System.out.println("5. Edit Staff");
                System.out.println("7. Logout");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        System.out.println("----- Edit Subject Menu -----");

                        editSubject();
                        break;
                    case 2:
                        System.out.println("----- Edit Student Menu -----");
                        editStudent();
                        break;
                    case 3:
                        System.out.println("----- Edit Teacher Menu -----");
                        editTeacher();
                        break;
                    case 4:
                        System.out.println("----- Edit Class Menu -----");
                        editClass();
                        break;
                    case 5:
                        System.out.println("----- Edit Staff Menu -----");
                        editStaff();
                        break;
                    case 7:
                        System.out.println("Logout");
                        userService.logout();
                        return;
                }
            }catch (NumberFormatException e){
                System.out.println("Invalid input");
            }
        }
    }

    private void editStudent(){

        while (true){

            try {System.out.println("""
                    1. Add Student\s
                    2. Add Subject To Student\s
                    3. Delete Subject From Student\s
                    4. Delete Student\s
                    5. Search Student By Id\s
                    6. Update Student\s
                    7. Display All Student\s
                    8. Back Main Menu"""
            );
                System.out.println("Please choose one of the following options:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        System.out.println("1. Add Student");
                        studentService.add();
                        break;
                    case 2:
                        System.out.println("2. Add Subject to Student");
                        studentService.addSubjectToStudent();
                        break;
                    case 3:
                        System.out.println("3. Delete Subject From Student");
                        studentService.deleteSubjectFromStudent();
                        break;
                    case 4:
                        System.out.println("4. Delete Student");
                        studentService.delete();
                        break;
                    case 5:
                        System.out.println("5. Search Student By Id");
                        studentService.displayStudentByID();
                        break;
                    case 6:
                        System.out.println("6. Update Student");
                        studentService.update();
                        break;
                    case 7:
                        System.out.println("7. Display All Student ");
                        studentService.displayAll();
                        break;

                    case 8:
                        System.out.println("7. Back Main Menu");
                        System.out.println("----- Admin Menu -----");
                        return;

                }
            }catch (NumberFormatException e){
                System.out.println("Invalid input");
            }

        }


    }
    private void editSubject(){

        while (true){

            try {
                System.out.println(
                        """
                                1. Add Subject\s
                                2. Delete Subject\s
                                3. Update Subject\s
                                4. Search Subject \s
                                5. Display All Subject\s
                                6. Back Main Menu""");
                System.out.println("Please choose one of the following options:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        System.out.println("1. Add Subject");
                        subjectService.add();
                        break;
                    case 2:
                        System.out.println("2. Delete Subject");
                        subjectService.delete();
                        break;
                    case 3:
                        System.out.println("3. Update Subject");
                        subjectService.update();
                        break;
                    case 4:
                        System.out.println("4. Search Subject");
                        subjectService.searchSubjectByName();
                        break;
                    case 5:
                        System.out.println("5. Display All Subject");
                        subjectService.displayAll();
                    case 6:
                        System.out.println("5. Back Main Menu");

                        System.out.println("----- Admin Menu -----");
                        return;

                }
            }catch (NumberFormatException e){
                System.out.println("Invalid input");
            }
        }
    }
    private void editTeacher(){
        while (true){
            try {
                System.out.println("""
                        1. Add Teacher\s
                        2. Add Subject To Teacher\s
                        3. Delete Subject From Teacher\s
                        4. Delete Teacher\s
                        5. Search Teacher By Id\s
                        6. Update Teacher\s
                        7. Display All Teacher\s
                        8. Back Main Menu""");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        System.out.println("1. Add Teacher");
                        teacherService.add();
                        break;
                    case 2:
                        System.out.println("2. Add Subject to Teacher");
                        teacherService.addSubjectToTeacher();
                        break;
                    case 3:
                        System.out.println("3. Delete Subject From Teacher ");
                        teacherService.deleteSubjectFromTeacher();
                        break;
                    case 4:
                        System.out.println("4. Delete Teacher ");
                        teacherService.delete();
                        break;
                    case 5:
                        System.out.println("5. Search Teacher By Id");
                        teacherService.displayTeacherById();
                        break;
                    case 6:
                        System.out.println("6. Update Teacher ");
                        teacherService.update();
                        break;
                    case 7:
                        System.out.println("7. Display All Teacher ");
                        teacherService.displayAll();
                    case 8:
                        System.out.println("7. Back Main Menu");
                        System.out.println("----- Admin Menu -----");
                        return;
                }

            }catch (NumberFormatException e){
                System.out.println("Invalid input");
            }
        }
    }

    private void editClass(){
        while (true){
            try {
                System.out.println("""
                        1. Add Class\s
                        2. Add Student To Class\s
                        3. Delete Student From Class\s
                        4. Delete Class\s
                        5. Change Teacher In Class\s
                        6. Add Student Range By ID\s
                        7. Display Class\s
                        8. Back Main Menu""");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        System.out.println("1. Add Class");
                        classService.add();
                        break;
                    case 2:
                        System.out.println("2. Add Student to Class");
                        classService.addStudentClass();
                        break;
                    case 3:
                        System.out.println("3. Delete Student From Class");
                        classService.removeStudentClass();
                        break;
                    case 4:
                        System.out.println("4. Delete Class");
                        classService.delete();
                        break;
                    case 5:
                        System.out.println("5. Change Teacher In Class");
                        classService.changeTeacherInClass();
                        break;
                    case 6:
                        System.out.println("6. Add Student Range By ID");
                        classService.addStudentRangeByID();
                        break;
                    case 7:
                        System.out.println("7. Display Class ");
                        classService.displayAll();
                        break;

                    case 8:
                        System.out.println("8. Back Main Menu");
                        System.out.println("----- Admin Menu -----");
                        return;


                }

            }catch (NumberFormatException e){
                System.out.println("Invalid input");
            }
        }
    }

    private void editStaff(){
        while (true){
            try {
                System.out.println("1. Add Staff");
                System.out.println("2. Update Staff");
                System.out.println("3. Delete Staff");
                System.out.println("4. Display All Staff");
                System.out.println("4. Back Main Menu");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        System.out.println("1. Add Staff");
                        staffService.add();
                        break;
                    case 2:
                        System.out.println("2. Update Staff");
                        staffService.update();
                        break;
                    case 3:
                        System.out.println("3. Delete Staff");
                        staffService.delete();
                        break;
                    case 4:
                        System.out.println("4. Display All Staff");
                        staffService.displayAll();
                    case 5:
                        System.out.println("8. Back Main Menu");
                        System.out.println("----- Admin Menu -----");
                        return;

                }

            }catch (NumberFormatException e){
                System.out.println("Invalid input");
            }
        }
    }

}
