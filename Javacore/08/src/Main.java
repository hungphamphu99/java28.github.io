import entities.Student;
import service.StudentService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService studentService = new StudentService();
        int num =0;
        while (num <= 0){
            try {
                System.out.println("Enter number of students : " );
                num = Integer.parseInt(scanner.nextLine());
                if (num < 0) {
                    System.out.println("Please, enter a positive integer ");
                }

            }catch (NumberFormatException e){
                System.out.println("Invalid input");
            }
        }
        for (int i = 1; i <= num; i++) {
            System.out.println("Student " + i + ":");
            studentService.inputStudent();

        }
        studentService.printStudents();

    }
}