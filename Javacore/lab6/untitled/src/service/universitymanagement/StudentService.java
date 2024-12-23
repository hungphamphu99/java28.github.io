package service.universitymanagement;

import data.UniversityData;
import entities.universitymanagement.Score;
import entities.universitymanagement.Student;
import entities.universitymanagement.Subject;
import service.Edit;
import utils.Validator;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class StudentService implements Edit<Student> {
    Scanner scanner = new Scanner(System.in);
    SubjectService subjectService = new SubjectService();


    private String generateRandomPassword() {
        Random random = new Random();
        int randomPassword = 10000 + random.nextInt(90000);
        return String.valueOf(randomPassword);
    }


    public void displayStudentByID() {
        System.out.print("Enter student ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Student student = findById(id);

        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }



    public void addSubjectToStudent() {
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        Student student = findById(studentId);

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

        Subject subject = subjectService.findById(subjectId);
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
        Student student = findById(studentId);

        if (student == null) {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }

        System.out.print("Enter subject ID: ");
        int subjectId = Integer.parseInt(scanner.nextLine());
        Subject subjectToRemove = subjectService.findById(subjectId);

        // Check if the student is enrolled in the subject
        if (subjectToRemove == null || !student.getSubjectScores().containsKey(subjectToRemove)) {
            System.out.println("Student is not enrolled in the subject with ID " + subjectId);
            return;
        }

        // Check if there is a recorded score for the subject
        Score score = student.getSubjectScores().get(subjectToRemove);
        if (score != null && score.getOverallScore() > 0) {
            System.out.println("Cannot remove subject " + subjectToRemove.getName() + " because the student has a score recorded.");
            return;
        }

        // Remove the subject from the student's list of subjects
        student.getSubjectScores().remove(subjectToRemove);
        System.out.println("Subject " + subjectToRemove.getName() + " removed from student " + student.getName());
    }


    public void inputScore() {
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        Student student = findById(studentId);
        if (student == null) {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }

        int subjectCount = student.getSubjectScores().size();
        if (subjectCount == 0) {
            System.out.println("Student " + student.getName() + " (ID: " + student.getId() + ") has not enrolled in any subjects.");
            return;
        }

        double totalOverallScore = 0;
        int subjectsWithScores = 0; // Count the number of subjects to calculate the average score.

        // Iterate through each subject and prompt for scores, allowing updates even if scores exist.
        for (Subject subject : student.getSubjectScores().keySet()) {
            System.out.println("Entering scores for subject: " + subject.getName());

            // Use Validator.inputPositiveDouble_v2 to input scores.
            double midScore = Validator.inputPositiveDouble_v2(scanner, "Enter mid-term score", 0);
            double finalScore = Validator.inputPositiveDouble_v2(scanner, "Enter final-term score", 0);

            double overallScore = midScore * 0.4 + finalScore * 0.6;
            System.out.println("Overall score for subject " + subject.getName() + ": " + overallScore);

            // Update or add the score in the student's subject scores map.
            student.getSubjectScores().put(subject, new Score(midScore, finalScore, overallScore));

            // Add to the total score to calculate the average later.
            totalOverallScore += overallScore;
            subjectsWithScores++;
        }

        // Calculate the average score only if there is at least one subject.
        if (subjectsWithScores > 0) {
            double avgScore = totalOverallScore / subjectsWithScores;
            student.setAvgScore(avgScore);
            System.out.println("Average score for student " + student.getName() + " (ID: " + student.getId() + "): " + avgScore);
        } else {
            System.out.println("No scores were updated.");
        }
    }


    public void editScore() {
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        Student student = findById(studentId);

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

        // Check if the student is enrolled in the subject
        Subject subject = student.getSubjectScores().keySet().stream()
                .filter(s -> s.getId() == subjectId)
                .findFirst()
                .orElse(null);

        if (subject == null) {
            System.out.println("Student is not enrolled in subject with ID " + subjectId + ".");
            return;
        }

        // Get the existing score or initialize it if it doesn't exist
        Score existingScore = student.getSubjectScores().get(subject);
        if (existingScore == null) {
            System.out.println("No scores available for this subject. Initializing new score entry.");
            existingScore = new Score(0, 0, 0); // Default values
            student.getSubjectScores().put(subject, existingScore);
        }

        System.out.println("Existing scores for subject " + subject.getName() +
                " - Mid-term: " + existingScore.getMidScore() +
                ", Final: " + existingScore.getFinalScore() +
                ", Overall: " + existingScore.getOverallScore());

        // Input new scores
        System.out.print("Enter new mid-term score: ");
        double newMidScore = Validator.inputPositiveInteger(scanner);

        System.out.print("Enter new final score: ");
        double newFinalScore = Validator.inputPositiveInteger(scanner);

        double newOverallScore = (newMidScore * 0.4) + (newFinalScore * 0.6);

        // Update the score for the subject
        student.getSubjectScores().put(subject, new Score(newMidScore, newFinalScore, newOverallScore));

        // Recalculate average score
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


    public void displayStudentScores(Student student) {
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        // Check if the student has any subjects
        Map<Subject, Score> subjectScores = student.getSubjectScores();
        if (subjectScores.isEmpty()) {
            System.out.println("No subjects available for student " + student.getName() + ".");
            return;
        }

        // Print the score table header
        System.out.println("Score table for student: " + student.getName());
        System.out.printf("%-20s %-10s %-10s %-10s%n", "Subject", "Mid-term", "Final", "Overall");
        System.out.println("-----------------------------------------------------------");

        // Iterate through the subject scores and print each one
        for (Map.Entry<Subject, Score> entry : subjectScores.entrySet()) {
            Subject subject = entry.getKey();
            Score score = entry.getValue();

            // Check if the score is null and display accordingly
            if (score == null) {
                System.out.printf("%-20s %-10s %-10s %-10s%n",
                        subject.getName(),
                        "null",
                        "null",
                        "null");
            } else {
                System.out.printf("%-20s %-10.2f %-10.2f %-10.2f%n",
                        subject.getName(),
                        score.getMidScore(),
                        score.getFinalScore(),
                        score.getOverallScore());
            }
        }

        System.out.println("-----------------------------------------------------------");
    }






    // Helper method to format subjects for a student
    private String formatSubjects(Student student) {
        if (student.getSubjectScores().isEmpty()) {
            return "None";
        }

        StringBuilder subjectsInfo = new StringBuilder();
        for (Subject subject : student.getSubjectScores().keySet()) {
            subjectsInfo.append(subject.getName()).append(", ");
        }

        // Remove the trailing comma and space
        if (subjectsInfo.length() > 0) {
            subjectsInfo.setLength(subjectsInfo.length() - 2);
        }

        return subjectsInfo.toString();
    }


    @Override
    public void add() {
        // Ensure the student name is not empty
        String name;
        while (true) {
            System.out.println("Enter student name:");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Student name cannot be empty. Please enter a valid name.");
            } else {
                break;
            }
        }

        // Ensure the student email is not empty
        String email;
        while (true) {
            System.out.println("Enter student email:");
            email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("Student email cannot be empty. Please enter a valid email.");
            } else {
                break;
            }
        }

        // Ensure the student address is not empty
        String address;
        while (true) {
            System.out.println("Enter student address:");
            address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                System.out.println("Student address cannot be empty. Please enter a valid address.");
            } else {
                break;
            }
        }

        // Ensure the phone number contains only digits and is not empty
        String phone;
        while (true) {
            System.out.println("Enter student phone:");
            phone = scanner.nextLine().trim();
            if (phone.isEmpty()) {
                System.out.println("Student phone cannot be empty. Please enter a valid phone number.");
            } else if (!phone.matches("\\d+")) {
                System.out.println("Phone number must contain only digits. Please try again.");
            } else {
                break;
            }
        }

        String role = "student";
        String password = generateRandomPassword();
        Student student = new Student(null, password, role, name, email, address, phone);
        String username = student.getId() + "-" + name;
        student.setUsername(username);

        System.out.println("Student added successfully: " + student);
    }

    @Override
    public void update() {
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        Student student = findById(studentId);

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

    @Override
    public Student findById(int id) {
        for (Student student : UniversityData.students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    @Override
    public void displayAll() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-15s %-15s %-10s %-42s %-25s\n", "ID", "Username", "Password", "Name", "Avg Score", "Subjects", "Email");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Student student : UniversityData.students) {
            System.out.printf("%-5d %-15s %-15s %-15s %-10.2f %-42s %-25s\n",
                    student.getId(),
                    student.getUsername(),
                    student.getPassword(),  // Display the password
                    student.getName(),
                    student.getAvgScore(),
                    formatSubjects(student),
                    student.getEmail());
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    @Override
    public void delete() {
        System.out.println("Enter student id:");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Student student = findById(id);
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
