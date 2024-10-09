package service.salesmanagement;

import data.ShopData;
import data.UniversityData;
import entities.salesmanagement.Staff;
import entities.universitymanagement.Student;

import java.util.Random;
import java.util.Scanner;

public class StaffService {
    Scanner scanner = new Scanner(System.in);
    public Staff findStaffByID(int id){
        for (Staff staff : ShopData.staffs){
            if(staff.getId() == id){
                return staff;
            }
        }
        return null;
    }
    public void displayStaff(){
        System.out.println("Enter the ID of the staff");
        int id = Integer.parseInt(scanner.nextLine());
        Staff staff = findStaffByID(id);
        if(staff != null){
            System.out.println(staff);
        }else {
            System.out.println("Staff not found");
        }

    }

    public void addStaff() {
        System.out.println("Enter student name:");
        String name = scanner.nextLine();
        System.out.println("Enter student email:");
        String email = scanner.nextLine();
        System.out.println("Enter student address:");
        String address = scanner.nextLine();
        System.out.println("Enter student phone:");
        String phone = scanner.nextLine();
        String role = "staff";

        String password = generateRandomPassword();
        Staff staff = new Staff(null, password, role, name, email, address, phone);
        String username = staff.getId() + "-" + name +"staff";
        staff.setUsername(username);

        System.out.println("Student added successfully: " + staff);
    }

    private String generateRandomPassword() {
        Random random = new Random();
        int randomPassword = 10000 + random.nextInt(90000);
        return String.valueOf(randomPassword);
    }

    public void updateInformationStaff() {
        System.out.print("Enter staff ID: ");
        int staffId = Integer.parseInt(scanner.nextLine());
        Staff staff = findStaffByID(staffId);

        if (staff == null) {
            System.out.println("staff with ID " + staffId + " not found.");
            return;
        }

        System.out.println("Current information: ");
        System.out.println(staff);

        System.out.println("Press Enter without typing anything to keep the current information.");

        // Update Name
        System.out.print("Enter new name (" + staff.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            staff.setName(newName);
            String newUsername = staff.getId() + "-" + newName +"-"+"staff";
            staff.setUsername(newUsername);
            System.out.println("Name and username updated successfully.");
        }

        // Update Email
        System.out.print("Enter new email (" + staff.getEmail() + "): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) {
            staff.setEmail(newEmail);
            System.out.println("Email updated successfully.");
        }

        // Update Address
        System.out.print("Enter new address (" + staff.getAddress() + "): ");
        String newAddress = scanner.nextLine();
        if (!newAddress.isEmpty()) {
            staff.setAddress(newAddress);
            System.out.println("Address updated successfully.");
        }

        // Update Phone
        System.out.print("Enter new phone number (" + staff.getPhone() + "): ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isEmpty()) {
            staff.setPhone(newPhone);
            System.out.println("Phone updated successfully.");
        }

        System.out.println("Updated information: ");
        System.out.println(staff);
    }

    public void deleteStaffById() {
        System.out.println("Enter staff id:");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Staff staff = findStaffByID(id);
            if (staff != null) {
                System.out.println("Are you sure you want to delete the staff with ID: " + id + "? (y/n)");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("y")) {
                    ShopData.removeStaff(staff);
                    System.out.println("staff with ID " + id + " has been successfully deleted.");
                } else {
                    System.out.println("Operation cancelled.");
                }
            } else {
                System.out.println("staff not found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

}
