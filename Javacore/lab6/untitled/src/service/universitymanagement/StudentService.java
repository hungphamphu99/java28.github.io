package service.universitymanagement;

import data.UniversityData;
import entities.universitymanagement.Score;
import entities.universitymanagement.Student;
import entities.universitymanagement.Subject;

import java.util.Random;
import java.util.Scanner;

public class StudentService {
    Scanner scanner = new Scanner(System.in);
    SubjectService subjectService = new SubjectService();

    public void addStudent() {
        System.out.println("Enter student name:");
        String name = scanner.nextLine();
        System.out.println("Enter student email:");
        String email = scanner.nextLine();
        System.out.println("Enter student address:");
        String address = scanner.nextLine();
        System.out.println("Enter student phone:");
        String phone = scanner.nextLine();
        String role = "student";

        String password = generateRandomPassword();
        Student student = new Student(null, password, role, name, email, address, phone);
        String username = student.getId() + "-" + name;
        student.setUsername(username);

        System.out.println("Student added successfully: " + student);
    }

    private String generateRandomPassword() {
        Random random = new Random();
        int randomPassword = 10000 + random.nextInt(90000);
        return String.valueOf(randomPassword);
    }

    public Student findStudentByID(int id) {
        for (Student student : UniversityData.getStudents()) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public void displayStudentByID() {
        System.out.print("Enter student ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Student student = findStudentByID(id);

        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    public void displayAllStudents() {
        System.out.println(UniversityData.getStudents());
    }

    public void addSubjectToStudent() {
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        Student student = findStudentByID(studentId);

        if (student == null) {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }

        if (student.getSubjectScores().size() >= 5) {
            System.out.println("Student with ID " + studentId + " is already enrolled in 5 subjects and cannot enroll in more.");
            return;
        }

        System.out.print("Enter subject ID: ");
        int subjectId = Integer.parseInt(scanner.nextLine());

        for (Subject subject : student.getSubjectScores().keySet()) {  // Fix here
            if (subject.getId() == subjectId) {
                System.out.println("The student is already enrolled in a subject with ID " + subjectId);
                return;
            }
        }

        Subject subject = subjectService.findSubjectByID(subjectId);
        if (subject == null) {
            System.out.println("Subject with ID " + subjectId + " not found.");
            return;
        }

        student.getSubjectScores().put(subject, null);  // No score added yet
        System.out.println("Subject " + subject.getName() + " added to student " + student.getName());
    }

    public void deleteSubjectFromStudent() {
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        Student student = findStudentByID(studentId);

        if (student == null) {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }

        System.out.print("Enter subject ID: ");
        int subjectId = Integer.parseInt(scanner.nextLine());

        // Check if the student is enrolled in the subject
        Subject subjectToRemove = subjectService.findSubjectByID(subjectId);

        if (subjectToRemove == null || !student.getSubjectScores().containsKey(subjectToRemove)) {  // Fix here
            System.out.println("Student is not enrolled in the subject with ID " + subjectId);
            return;
        }

        // Remove the subject from the student's list of subjects
        student.getSubjectScores().remove(subjectToRemove);
        System.out.println("Subject " + subjectToRemove.getName() + " removed from student " + student.getName());
    }

    public void inputScore() {
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        Student student = findStudentByID(studentId);
        if (student == null) {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }

        double totalOverallScore = 0;
        int subjectCount = student.getSubjectScores().size();

        for (Subject subject : student.getSubjectScores().keySet()) {
            System.out.println("Entering scores for subject: " + subject.getName());

            System.out.print("Enter mid-term score : ");
            double midScore = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter final-term score : ");
            double finalScore = Double.parseDouble(scanner.nextLine());

            double overallScore = midScore * 0.4 + finalScore * 0.6;
            System.out.println("Overall score for subject " + subject.getName() + ": " + overallScore);

            student.getSubjectScores().put(subject, new Score(midScore, finalScore, overallScore));

            totalOverallScore += overallScore;
        }

        double avgScore = totalOverallScore / subjectCount;
        student.setAvgScore(avgScore);

        System.out.println("Average score for student " + student.getName() + " (ID: " + student.getId() + "): " + avgScore);
    }

    public void editScore() {
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        Student student = findStudentByID(studentId);

        if (student == null) {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }

        System.out.println("Available subjects for student:");
        for (Subject subject : student.getSubjectScores().keySet()) {
            System.out.println("Subject ID: " + subject.getId() + ", Name: " + subject.getName());
        }

        System.out.print("Enter subject ID for the score to be edited: ");
        int subjectId = Integer.parseInt(scanner.nextLine());
        Subject subject = subjectService.findSubjectByID(subjectId);


        if (subject == null) {
            System.out.println("Subject with ID " + subjectId + " not found for this student.");
            return;
        }

        Score existingScore = student.getSubjectScores().get(subject);

        if (existingScore == null) {
            System.out.println("No scores available for this subject.");
        } else {
            System.out.println("Existing scores - Mid-term: " + existingScore.getMidScore() + ", Final: " + existingScore.getFinalScore());
        }

        // Input new scores
        System.out.print("Enter new mid-term score: ");
        double newMidScore = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter new final score: ");
        double newFinalScore = Double.parseDouble(scanner.nextLine());

        double newOverallScore = (newMidScore * 0.4) + (newFinalScore * 0.6);

        student.getSubjectScores().put(subject, new Score(newMidScore, newFinalScore, newOverallScore));

        double totalOverallScore = 0;
        for (Score score : student.getSubjectScores().values()) {
            totalOverallScore += score.getOverallScore();
        }
        double avgScore = totalOverallScore / student.getSubjectScores().size();
        student.setAvgScore(avgScore);

        System.out.println("Scores updated successfully for subject " + subject.getName() + ".");
        System.out.println("New scores - Mid-term: " + newMidScore + ", Final: " + newFinalScore + ", Overall: " + newOverallScore);
        System.out.println("New average score for student: " + avgScore);
    }

    public void updateInformationStudent() {
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        Student student = findStudentByID(studentId);

        if (student == null) {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }

        System.out.println("Current information: ");
        System.out.println(student.infoStudent());

        System.out.println("Press Enter without typing anything to keep the current information.");

        // Update Name
        System.out.print("Enter new name (" + student.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            student.setName(newName);
            String newUsername = student.getId() + "-" + newName;
            student.setUsername(newUsername);
            System.out.println("Name and username updated successfully.");
        }

        // Update Email
        System.out.print("Enter new email (" + student.getEmail() + "): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) {
            student.setEmail(newEmail);
            System.out.println("Email updated successfully.");
        }

        // Update Address
        System.out.print("Enter new address (" + student.getAddress() + "): ");
        String newAddress = scanner.nextLine();
        if (!newAddress.isEmpty()) {
            student.setAddress(newAddress);
            System.out.println("Address updated successfully.");
        }

        // Update Phone
        System.out.print("Enter new phone number (" + student.getPhone() + "): ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isEmpty()) {
            student.setPhone(newPhone);
            System.out.println("Phone updated successfully.");
        }

        System.out.println("Updated information: ");
        System.out.println(student.infoStudent());
    }

    public void deleteStudentById() {
        System.out.println("Enter student id:");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Student student = findStudentByID(id);
            if (student != null) {
                System.out.println("Are you sure you want to delete the student with ID: " + id + "? (y/n)");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("y")) {
                    UniversityData.removeStudent(student);
                    System.out.println("Student with ID " + id + " has been successfully deleted.");
                } else {
                    System.out.println("Operation cancelled.");
                }
            } else {
                System.out.println("Student not found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }
}
