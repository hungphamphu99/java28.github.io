package service;

import entities.BizStudent;
import java.util.ArrayList;
import java.util.Scanner;

public class BizStudentService {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<BizStudent> bizStudents = new ArrayList<>();
    private String name;
    private String major;
    private Double marketingPoint;
    private Double salesPoint;
    private int numStudents;

    public void inputBizStudents() {
        while (true) {
            try {
                System.out.print("Enter the number of Biz students: ");
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
            System.out.println("Entering details for Biz student " + (i + 1));

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
                System.out.println("1. Business");
                System.out.println("2. Marketing");

                System.out.print("Enter the number corresponding to the major: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        major = "Business";
                        break;
                    case 2:
                        major = "Marketing";
                        break;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        continue;
                }
                break;
            }

            while (true) {
                try {
                    System.out.print("Enter Marketing point: ");
                    marketingPoint = Double.parseDouble(sc.nextLine());
                    if (marketingPoint >= 0) {
                        break;
                    } else {
                        System.out.println("Please enter a valid marketing point (>= 0).");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter Sales point: ");
                    salesPoint = Double.parseDouble(sc.nextLine());
                    if (salesPoint >= 0) {
                        break;
                    } else {
                        System.out.println("Please enter a valid sales point (>= 0).");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                }
            }

            BizStudent student = new BizStudent(name, major, marketingPoint, salesPoint);
            bizStudents.add(student);
        }
    }

    public ArrayList<BizStudent> getBizStudents() {
        return bizStudents;
    }
}
