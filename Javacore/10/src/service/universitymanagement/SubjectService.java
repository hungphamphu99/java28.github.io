package service.universitymanagement;

import entities.universitymanagement.Subject;
import utils.Enum;

import java.util.ArrayList;
import java.util.Scanner;

public class SubjectService {
    private ArrayList<Subject> subjects = new ArrayList<>();
     Scanner scanner = new Scanner(System.in);
    StudentService studentService = new StudentService();
    TeacherService teacherService = new TeacherService();
    public SubjectService(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }



    public void addSubject(Subject subject) {
        subjects.add(subject);
    }


    public ArrayList<Subject> addSubjectByAdmin() {
        System.out.println("Enter the name of the subject:");
        String name = scanner.nextLine();

        int ECTs;
        while (true) {
            System.out.println("Enter the number of ECTS credits for the subject:");
            if (scanner.hasNextInt()) {
                ECTs = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Please enter a valid number of ECTS credits.");
                scanner.nextLine();
            }
        }

        Enum.Type type;
        while (true) {
            System.out.println("Enter the type of the subject (TECH, BUSINESS, LANGUAGE):");
            String typeInput = scanner.nextLine().toUpperCase();
            try {
                type = Enum.Type.valueOf(typeInput);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid type. Please enter TECH, BUSINESS, or LANGUAGE.");
            }
        }

        Subject newSubject = new Subject(name, ECTs, type);
        subjects.add(newSubject);

        System.out.println("Subject added successfully: " +
                "ID: " + newSubject.getId() +
                ", Name: " + newSubject.getName() +
                ", ECTS: " + newSubject.getECTs() +
                ", Type: " + newSubject.getType());

        return subjects;
    }
    public void updateSubject() {
        System.out.print("Enter the ID of the subject you want to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Clear the newline

        Subject subjectToUpdate = findSubjectById(id);
        if (subjectToUpdate == null) {
            System.out.println("Subject with ID " + id + " not found.");
            return;
        }

        System.out.println("Current Name: " + subjectToUpdate.getName());
        System.out.print("Enter new name (or press Enter to keep the current name): ");
        String newName = scanner.nextLine();
        if (!newName.trim().isEmpty()) {
            subjectToUpdate.setName(newName);
        }

        System.out.println("Current ECTS: " + subjectToUpdate.getECTs());
        System.out.print("Enter new ECTS (or press Enter to keep the current ECTS): ");
        String newECTSInput = scanner.nextLine();
        if (!newECTSInput.trim().isEmpty()) {
            int newECTS = Integer.parseInt(newECTSInput);
            subjectToUpdate.setECTs(newECTS);
        }

        System.out.println("Current Type: " + subjectToUpdate.getType());
        System.out.print("Enter new type (or press Enter to keep the current type): ");
        String newTypeInput = scanner.nextLine().toUpperCase();
        if (!newTypeInput.trim().isEmpty()) {
            try {
                Enum.Type newType = Enum.Type.valueOf(newTypeInput);
                subjectToUpdate.setType(newType);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid type. Keeping the current type.");
            }
        }

        System.out.println("Subject updated successfully.");
    }

    public void deleteSubjectById(int id) {
        // Find the subject to delete
        Subject subjectToDelete = findSubjectById(id);

        if (subjectToDelete != null) {
            // Remove the subject from the list of subjects
            subjects.remove(subjectToDelete);

            // Remove the subject from all students' subject lists
            studentService.removeSubjectFromAllStudents(subjectToDelete);

            System.out.println("Subject with ID " + id + " has been deleted and removed from all students.");
        } else {
            System.out.println("Subject with ID " + id +" not found.");
        }
    }


    public void searchSubjectById() {
        System.out.print("Enter the ID of the subject you want to search for: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Subject subject = findSubjectById(id);
        if (subject != null) {
            System.out.println("Subject found: " +
                    "ID: " + subject.getId() +
                    ", Name: " + subject.getName() +
                    ", ECTS: " + subject.getECTs() +
                    ", Type: " + subject.getType());
        } else {
            System.out.println("Subject with ID " + id + " not found.");
        }
    }

    public Subject findSubjectByName(String name) {
        for (Subject subject : subjects) {
            if (subject.getName().equalsIgnoreCase(name)) {
                return subject;
            }
        }
        return null;
    }
    public Subject findSubjectById(int id) {
        for (Subject subject : subjects) {
            if (subject.getId() == id) {
                return subject;
            }
        }
        return null;
    }

    public void displayAllSubjects() {
        if (subjects.isEmpty()) {
            System.out.println("No subjects available.");
        } else {
            System.out.println("List of all subjects:");
            for (Subject subject : subjects) {
                System.out.println("ID: " + subject.getId() +
                        ", Name: " + subject.getName() +
                        ", ECTS: " + subject.getECTs() +
                        ", Type: " + subject.getType());
            }
        }
    }
}
