package service.salesmanagement;

import data.ShopData;
import data.UniversityData;
import entities.salesmanagement.Staff;
import entities.universitymanagement.Student;
import service.Edit;

import java.util.Random;
import java.util.Scanner;

public class StaffService implements Edit<Staff> {
    Scanner scanner = new Scanner(System.in);

    public void displayStaff(){
        System.out.println("Enter the ID of the staff");
        int id = Integer.parseInt(scanner.nextLine());
        Staff staff = findById(id);
        if(staff != null){
            System.out.println(staff);
        }else {
            System.out.println("Staff not found");
        }

    }



    private String generateRandomPassword() {
        Random random = new Random();
        int randomPassword = 10000 + random.nextInt(90000);
        return String.valueOf(randomPassword);
    }



    @Override
    public void add() {
        // Ensure the staff name is not empty
        String name;
        while (true) {
            System.out.println("Enter staff name:");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Staff name cannot be empty. Please enter a valid name.");
            } else {
                break;
            }
        }

        // Ensure the staff email is not empty
        String email;
        while (true) {
            System.out.println("Enter staff email:");
            email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("Staff email cannot be empty. Please enter a valid email.");
            } else {
                break;
            }
        }

        // Ensure the staff address is not empty
        String address;
        while (true) {
            System.out.println("Enter staff address:");
            address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                System.out.println("Staff address cannot be empty. Please enter a valid address.");
            } else {
                break;
            }
        }

        // Ensure the phone number contains only digits and is not empty
        String phone;
        while (true) {
            System.out.println("Enter staff phone:");
            phone = scanner.nextLine().trim();
            if (phone.isEmpty()) {
                System.out.println("Staff phone cannot be empty. Please enter a valid phone number.");
            } else if (!phone.matches("\\d+")) {
                System.out.println("Phone number must contain only digits. Please try again.");
            } else {
                break;
            }
        }

        String role = "staff";
        String password = generateRandomPassword();
        Staff staff = new Staff(null, password, role, name, email, address, phone);
        String username = staff.getId() + "-" + name + "staff";
        staff.setUsername(username);

        System.out.println("Staff added successfully: " + staff);
    }

    @Override
    public void update() {
        System.out.print("Enter staff ID: ");
        int staffId = Integer.parseInt(scanner.nextLine());
        Staff staff = findById(staffId);

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

    @Override
    public Staff findById(int id) {
        for (Staff staff : ShopData.staffs){
            if(staff.getId() == id){
                return staff;
            }
        }
        return null;
    }

    @Override
    public void displayAll() {
        if (ShopData.staffs.isEmpty()) {
            System.out.println("No staff members available.");
            return;
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-15s %-20s %-30s %-15s\n", "ID", "Name", "Username", "Email", "Address", "Phone");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        for (Staff staff :  ShopData.staffs) {
            System.out.printf("%-10d %-20s %-15s %-20s %-30s %-15s\n",
                    staff.getId(),
                    staff.getName(),
                    staff.getUsername(),
                    staff.getEmail(),
                    staff.getAddress(),
                    staff.getPhone());
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

    }

    @Override
    public void delete() {
        System.out.println("Enter staff id:");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Staff staff = findById(id);
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
