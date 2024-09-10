package service.universitymanagement;

import data.UniversityData;
import entities.universitymanagement.Class;
import entities.universitymanagement.Student;
import entities.universitymanagement.Subject;
import entities.universitymanagement.Teacher;
import utils.Enum;

import java.util.Random;
import java.util.Scanner;

public class TeacherService {
    Scanner scanner = new Scanner(System.in);
    SubjectService subjectService = new SubjectService();

    public  void  addTeacher(){
        System.out.println("Enter teacher name:");
        String name = scanner.nextLine();
        System.out.println("Enter teacher email");
        String email = scanner.nextLine();
        System.out.println("Enter teacher address");
        String address = scanner.nextLine();
        System.out.println("Enter teacher phone");
        String phone = scanner.nextLine();
        String role = "teacher";
        String password = generateRandomPassword();
        Enum.Type major = chooseMajor();
        Teacher teacher = new Teacher(null, password, role, name, email, address, phone, major);
        String username = teacher.getId() + "-" + name +".edu";
        teacher.setUsername(username);
        System.out.println("Student added successfully" + teacher);
    }
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

    public Teacher findTeacherById(int id){
        for (Teacher teacher : UniversityData.getTeachers()){
            if (teacher.getId() == id){
                return teacher;
            }
        }
        return null;
    }
    public void displayTeacherById(){
        System.out.println("Enter teacher id:");
        int id = Integer.parseInt(scanner.nextLine());
        Teacher teacher = findTeacherById(id);
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
            Teacher teacher = findTeacherById(id);

            if (teacher != null) {
                System.out.println("Enter subject id:");
                int subjectId = Integer.parseInt(scanner.nextLine());
                Subject subject = subjectService.findSubjectByID(subjectId);

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

    public void deleteTeacherById() {
        System.out.println("Enter teacher id:");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Teacher teacher = findTeacherById(id);

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

    public void deleteSubjectFromTeacher() {
        System.out.println("Enter teacher id:");
        int id = Integer.parseInt(scanner.nextLine());
        Teacher teacher = findTeacherById(id);
        if (teacher == null) {
            System.out.println("Teacher not found with ID: " + id);
            return;
        }
        System.out.print("Enter subject ID: ");
        int subjectId = Integer.parseInt(scanner.nextLine());
        Subject subjectToRemove = subjectService.findSubjectByID(subjectId);
        if (subjectToRemove == null) {
            System.out.println("Teacher is not enrolled in the subject with ID " + subjectId);
            return;
        }
        teacher.getSubjects().remove(subjectToRemove);
        System.out.println("Subject with ID " + subjectId + " has been successfully deleted.");
    }

    public void updateTeacherById() {
        System.out.println("Enter teacher id:");
        int id = Integer.parseInt(scanner.nextLine());
        Teacher teacher = findTeacherById(id);
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

    public void displayClassesAndStudents(Teacher teacher) {
        if (UniversityData.getClasses().isEmpty()) {
            System.out.println("No classes available.");
            return;
        }

        boolean foundClasses = false;

        for (Class cl : UniversityData.getClasses()) {
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

}