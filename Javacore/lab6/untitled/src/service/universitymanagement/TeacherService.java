package service.universitymanagement;

import data.UniversityData;
import entities.universitymanagement.*;
import entities.universitymanagement.Class;
import service.Edit;
import utils.Enum;

import java.util.Random;
import java.util.Scanner;

public class TeacherService implements Edit<Teacher> {
    Scanner scanner = new Scanner(System.in);
    SubjectService subjectService = new SubjectService();



    private String generateRandomPassword() {
        Random random = new Random();
        int randomPassword = 10000 + random.nextInt(90000);
        return String.valueOf(randomPassword);
    }
    public Enum.Type chooseMajor(){
        while (true) {
            try {
                System.out.println("Choose teacher major:");
                System.out.println("1. Tech");
                System.out.println("2. Business");
                System.out.println("3. Language");
                System.out.print("Enter choice (1-3): ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        return Enum.Type.TECH;
                    case 2:
                        return Enum.Type.BUSINESS;
                    case 3:
                        return Enum.Type.LANGUAGE;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }catch (NumberFormatException e){
                System.out.println("Invalid choice. Please try again.");
            }

        }

    }


    public void displayTeacherById(){
        System.out.println("Enter teacher id:");
        int id = Integer.parseInt(scanner.nextLine());
        Teacher teacher = findById(id);
        if (teacher != null){
            System.out.println(teacher);
        }else {
            System.out.println("Teacher not found" +id);
        }

    }


    public boolean canTeacherTeachSubject(Teacher teacher, Subject subject) {
        if (!teacher.getMajor().equals(subject.getType())) {
            System.out.println("Teacher's major does not match the subject type.");
            return false;
        }

        long distinctSubjects = teacher.getSubjects().stream()
                .map(Subject::getId)
                .distinct()
                .count();

        if (distinctSubjects >= 2 && !teacher.getSubjects().contains(subject)) {
            System.out.println("The teacher is already teaching 2 different subjects.");
            return false;
        }

        return true;
    }

    public void addSubjectToTeacher() {
        try {
            System.out.println("Enter teacher id:");
            int id = Integer.parseInt(scanner.nextLine());
            Teacher teacher = findById(id);

            if (teacher != null) {
                System.out.println("Enter subject id:");
                int subjectId = Integer.parseInt(scanner.nextLine());
                Subject subject = subjectService.findById(subjectId);

                if (subject != null) {
                    if (canTeacherTeachSubject(teacher, subject)) {
                        if (!teacher.getSubjects().contains(subject)) {
                            teacher.addSubject(subject);
                            System.out.println("Subject added successfully to teacher.");
                        } else {
                            System.out.println("Teacher is already teaching this subject.");
                        }
                        System.out.println(teacher);
                    }
                } else {
                    System.out.println("Subject not found with ID: " + subjectId);
                }
            } else {
                System.out.println("Teacher not found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }



    public void deleteSubjectFromTeacher() {
        System.out.println("Enter teacher id:");
        int id = Integer.parseInt(scanner.nextLine());
        Teacher teacher = findById(id);
        if (teacher == null) {
            System.out.println("Teacher not found with ID: " + id);
            return;
        }
        System.out.print("Enter subject ID: ");
        int subjectId = Integer.parseInt(scanner.nextLine());
        Subject subjectToRemove = subjectService.findById(subjectId);
        if (subjectToRemove == null) {
            System.out.println("Teacher is not enrolled in the subject with ID " + subjectId);
            return;
        }
        teacher.getSubjects().remove(subjectToRemove);
        System.out.println("Subject with ID " + subjectId + " has been successfully deleted.");
    }

    public void displayScoresBySubject() {
        System.out.print("Enter subject ID: ");
        int subjectId = Integer.parseInt(scanner.nextLine());
        Subject subject = subjectService.findById(subjectId);

        if (subject == null) {
            System.out.println("Subject with ID " + subjectId + " not found.");
            return;
        }

        System.out.println("Students enrolled in " + subject.getName() + " (" + subject.getId() + "):");
        System.out.printf("%-10s %-20s %-10s %-10s %-10s%n", "ID", "Name", "Mid-term", "Final", "Overall");
        System.out.println("---------------------------------------------------------------");

        boolean studentsFound = false;
        for (Student student : UniversityData.students) {
            Score score = student.getSubjectScores().get(subject);

            if (score != null) {
                studentsFound = true;
                System.out.printf("%-10d %-20s %-10.2f %-10.2f %-10.2f%n",
                        student.getId(),
                        student.getName(),
                        score.getMidScore(),
                        score.getFinalScore(),
                        score.getOverallScore());
            }
        }

        if (!studentsFound) {
            System.out.println("No students are currently enrolled in this subject.");
        }
    }




    public void displayClassesAndStudents(Teacher teacher) {
        if (UniversityData.classes.isEmpty()) {
            System.out.println("No classes available.");
            return;
        }

        boolean foundClasses = false;

        for (Class cl : UniversityData.classes) {
            if (cl.getTeacher() == teacher) {
                foundClasses = true;
                System.out.println("Class ID: " + cl.getId() );

                if (cl.getStudents().isEmpty()) {
                    System.out.println("  No students enrolled in this class.");
                } else {
                    System.out.println("  Students:");
                    for (Student student : cl.getStudents()) {
                        System.out.println("    Student ID: " + student.getId() + ", Name: " + student.getName());
                    }
                }
            }
        }

        if (!foundClasses) {
            System.out.println("The teacher is not assigned to any classes.");
        }
    }

    @Override
    public void add() {
        // Ensure the teacher name is not empty
        String name;
        while (true) {
            System.out.println("Enter teacher name:");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Teacher name cannot be empty. Please enter a valid name.");
            } else {
                break;
            }
        }

        // Ensure the teacher email is not empty
        String email;
        while (true) {
            System.out.println("Enter teacher email:");
            email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("Teacher email cannot be empty. Please enter a valid email.");
            } else {
                break;
            }
        }

        // Ensure the teacher address is not empty
        String address;
        while (true) {
            System.out.println("Enter teacher address:");
            address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                System.out.println("Teacher address cannot be empty. Please enter a valid address.");
            } else {
                break;
            }
        }

        // Ensure the phone number contains only digits and is not empty
        String phone;
        while (true) {
            System.out.println("Enter teacher phone:");
            phone = scanner.nextLine().trim();
            if (phone.isEmpty()) {
                System.out.println("Teacher phone cannot be empty. Please enter a valid phone number.");
            } else if (!phone.matches("\\d+")) {
                System.out.println("Phone number must contain only digits. Please try again.");
            } else {
                break;
            }
        }

        String role = "teacher";
        String password = generateRandomPassword();
        Enum.Type major = chooseMajor();

        Teacher teacher = new Teacher(null, password, role, name, email, address, phone, major);
        String username = teacher.getId() + "-" + name + ".edu";
        teacher.setUsername(username);

        System.out.println("Teacher added successfully: " + teacher);
    }

    @Override
    public void update() {
        System.out.println("Enter teacher id:");
        int id = Integer.parseInt(scanner.nextLine());
        Teacher teacher = findById(id);
        if (teacher == null) {
            System.out.println("Teacher with ID " + id + " not found.");
            return;
        }
        System.out.println("Current information: ");
        System.out.println(teacher.infoTeacher());
        System.out.println("Press Enter without typing anything to keep the current information.");

        System.out.print("Enter new name : ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            teacher.setName(newName);
            String newUsername = teacher.getId() + "-" + newName +".edu";
            teacher.setUsername(newUsername);
            System.out.println("Name and username updated successfully.");

        }

        System.out.print("Enter new email : ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) {
            teacher.setEmail(newEmail);
            System.out.println("Email updated successfully.");
        }

        System.out.print("Enter new address : ");
        String newAddress = scanner.nextLine();
        if (!newAddress.isEmpty()) {
            teacher.setAddress(newAddress);
            System.out.println("Address updated successfully.");
        }

        System.out.print("Enter new phone number : ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isEmpty()) {
            teacher.setPhone(newPhone);
            System.out.println("Phone updated successfully.");
        }
        Enum.Type oldMajor = teacher.getMajor();
        Enum.Type newType = chooseMajor();
        if (!newType.equals(oldMajor)) {
            teacher.setMajor(newType);
            teacher.getSubjects().clear();
            System.out.println("Major updated. All subjects the teacher was teaching have been removed.");
        }

        System.out.println("Updated information: ");
        System.out.println(teacher.infoTeacher());
    }

    @Override
    public Teacher findById(int id) {
        for (Teacher teacher : UniversityData.teachers){
            if (teacher.getId() == id){
                return teacher;
            }
        }
        return null;
    }

    @Override
    public void displayAll() {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-30s %-20s %-15s %-15s %-40s\n", "ID", "Name", "Email", "Address", "Phone", "Major", "Subjects");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

        for (Teacher teacher : UniversityData.teachers) {
            System.out.printf("%-5d %-20s %-30s %-20s %-15s %-15s %-40s\n",
                    teacher.getId(),
                    teacher.getName(),
                    teacher.getEmail(),
                    teacher.getAddress(),
                    teacher.getPhone(),
                    teacher.getMajor(),
                    formatSubjects(teacher));
        }

        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

    }

    @Override
    public void delete() {
        System.out.println("Enter teacher id:");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Teacher teacher = findById(id);

            if (teacher != null) {
                System.out.println("Are you sure you want to delete the teacher with ID: " + id + "? (y/n)");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("y")) {
                    UniversityData.removeTeacher(teacher);
                    System.out.println("Teacher with ID " + id + " has been successfully deleted.");
                } else {
                    System.out.println("Operation cancelled.");
                }
            } else {
                System.out.println("Teacher not found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    private String formatSubjects(Teacher teacher) {
        if (teacher.getSubjects().isEmpty()) {
            return "None";
        }

        StringBuilder subjectsInfo = new StringBuilder();
        for (Subject subject : teacher.getSubjects()) {
            subjectsInfo.append(subject.getName()).append(", ");
        }

        // Remove the trailing comma and space
        if (subjectsInfo.length() > 0) {
            subjectsInfo.setLength(subjectsInfo.length() - 2);
        }

        return subjectsInfo.toString();
    }
}