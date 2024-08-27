package service;

import entities.IctStudent;
import entities.TechMasterStudent;
import java.util.ArrayList;
import java.util.Scanner;

public class IctStudentService {
    private Scanner sc = new Scanner(System.in);
    private String name;
    private String major;
    private Double javaPoint;
    private Double cssPoint;
    private Double htmlPoint;
    private int numStudents;

    public ArrayList<TechMasterStudent> inputIctStudents() {
        ArrayList<TechMasterStudent> students = new ArrayList<>();

        while (true) {
            try {
                System.out.print("Enter the number of ICT students: ");
                numStudents = Integer.parseInt(sc.nextLine());
                if (numStudents > 0) {
                    break;
                } else {
                    System.out.println("Please enter a valid number greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Entering details for ICT student " + (i + 1));

            while (true) {
                System.out.print("Enter the name of the student: ");
                name = sc.nextLine();
                if (name.matches("[a-zA-Z ]+")) {
                    break;
                } else {
                    System.out.println("Please enter a valid name (letters only, no numbers).");
                }
            }

            while (true) {
                System.out.println("Choose major:");
                System.out.println("1. Information and Communication Technologies");
                System.out.println("2. Cyber Security");


                System.out.print("Enter the number corresponding to the major: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        major = "Information and Communication Technologies";
                        break;
                    case 2:
                        major = "Cyber Security";
                        break;

                    default:
                        System.out.println("Invalid choice. Please select again.");
                        continue;
                }
                break;
            }

            while (true) {
                try {
                    System.out.print("Enter Java point: ");
                    javaPoint = Double.parseDouble(sc.nextLine());
                    if (javaPoint >= 0) {
                        break;
                    } else {
                        System.out.println("Please enter a valid Java point (>= 0).");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter CSS point: ");
                    cssPoint = Double.parseDouble(sc.nextLine());
                    if (cssPoint >= 0) {
                        break;
                    } else {
                        System.out.println("Please enter a valid CSS point (>= 0).");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter HTML point: ");
                    htmlPoint = Double.parseDouble(sc.nextLine());
                    if (htmlPoint >= 0) {
                        break;
                    } else {
                        System.out.println("Please enter a valid HTML point (>= 0).");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                }
            }

            IctStudent student = new IctStudent(name, major, javaPoint, cssPoint, htmlPoint);
            students.add(student);
        }

        return students;
    }
}
