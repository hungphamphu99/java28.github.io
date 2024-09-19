package menu;

import services.StudentService;

public class Menu {
    public void menu() {
        StudentService studentService = new StudentService();
        while (true) {
            try {
                System.out.println("Display All Student");
                studentService.displayAllStudents();
                break;
            }catch (NumberFormatException e){

            }
        }
    }
}
