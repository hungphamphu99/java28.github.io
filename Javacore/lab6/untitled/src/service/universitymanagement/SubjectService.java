package service.universitymanagement;

import data.UniversityData;
import entities.universitymanagement.*;
import entities.universitymanagement.Class;
import service.Edit;
import utils.Enum;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class SubjectService implements Edit<Subject> {
    Scanner scanner = new Scanner(System.in);

    public boolean isSubjectNameExists(String name) {
        for (Subject subject : UniversityData.subjects) {
            if (subject.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }



    private void removeSubjectFromStudents(Subject subject) {
        List<Student> students = UniversityData.students;

        for (Student student : students) {
            if (student.getSubjectScores().containsKey(subject)) {
                Score score = student.getSubjectScores().get(subject);

                // Check if the score is null or if the student has non-zero scores for the subject
                if (score == null) {
                    // If the score is null, it means no scores are recorded, so it can be removed
                    student.getSubjectScores().remove(subject);
                    System.out.println("Removed subject from student: " + student.getId());
                } else if (score.getMidScore() > 0 || score.getFinalScore() > 0 || score.getOverallScore() > 0) {
                    System.out.println("Cannot remove subject from student: " + student.getId() +
                            " as they have scores recorded for this subject.");
                } else {
                    student.getSubjectScores().remove(subject);
                    System.out.println("Removed subject from student: " + student.getId());
                }

                // Recalculate the student's average score after removing the subject
                student.calculateAvgScore();
            }
        }
    }


    private void removeSubjectFromTeachers(Subject subject) {
        List<Teacher> teachers = UniversityData.teachers;

        for (Teacher teacher : teachers) {
            if (teacher.getSubjects().contains(subject)) {
                teacher.getSubjects().remove(subject);
                System.out.println("Removed subject from teacher: " + teacher.getId());
            }
        }
    }

    private void removeSubjectFromClasses(Subject subject) {
        List<Class> classes = UniversityData.classes;
        Iterator<Class> iterator = classes.iterator();

        while (iterator.hasNext()) {
            Class cl = iterator.next();
            if (cl.getSubject().equals(subject)) {
                iterator.remove();
                System.out.println("Removed class: " + cl.getId());
            }
        }
    }

    public void searchSubjectByID() {
        System.out.print("Enter subject ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Subject subject = findById(id);
            if (subject != null) {
                System.out.println("Subject found: " + subject);
            } else {
                System.out.println("Subject not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }



    @Override
    public void add() {
        // Input subject name and ensure it's not empty
        String name;
        while (true) {
            System.out.print("Enter subject name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Subject name cannot be empty. Please enter a valid name.");
            } else if (isSubjectNameExists(name)) {
                System.out.println("Subject name already exists. Please enter a different name.");
            } else {
                break; // Valid subject name entered
            }
        }

        // Input and validate ECTs value (must be a positive integer)
        int ECTs = 0;
        while (true) {
            try {
                System.out.print("Enter number of ECTs: ");
                ECTs = Integer.parseInt(scanner.nextLine());
                if (ECTs <= 0) {
                    System.out.println("Number of ECTs must be greater than 0. Please try again.");
                } else {
                    break; // Valid ECTs value entered
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for ECTs.");
            }
        }

        // Select the subject type with validation
        Enum.Type type = null;
        while (true) {
            try {
                System.out.println("Select subject type:");
                System.out.println("1. TECH");
                System.out.println("2. BUSINESS");
                System.out.println("3. LANGUAGE");
                int typeChoice = Integer.parseInt(scanner.nextLine());

                switch (typeChoice) {
                    case 1:
                        type = Enum.Type.TECH;
                        break;
                    case 2:
                        type = Enum.Type.BUSINESS;
                        break;
                    case 3:
                        type = Enum.Type.LANGUAGE;
                        break;
                    default:
                        System.out.println("Invalid type choice. Please try again.");
                        continue;
                }
                break; // Valid type selected
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for the type.");
            }
        }

        // Create and add the new subject
        Subject newSubject = new Subject(name, ECTs, type);

        System.out.println("Subject added successfully: " + newSubject);
    }

    @Override
    public void update() {
        System.out.print("Enter subject ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Subject subject = findById(id);

            if (subject != null) {
                System.out.println("Current information: ");
                System.out.println(subject);
                System.out.println("Press Enter without typing anything to keep the current information.");

                System.out.print("Enter subject name: ");
                String name = scanner.nextLine();
                if (!name.isEmpty()) {
                    subject.setName(name);
                    System.out.println("Name updated successfully.");
                }

                System.out.print("Enter number of ECTs: ");
                String ectsInput = scanner.nextLine();
                if (!ectsInput.isEmpty()) {
                    try {
                        int ECTs = Integer.parseInt(ectsInput);
                        subject.setECTs(ECTs);
                        System.out.println("ECTs updated successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number for ECTs. Update cancelled.");
                    }
                }
            } else {
                System.out.println("Subject not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid subject ID. Please enter a valid number.");
        }
    }

    @Override
    public Subject findById(int id) {
        for (Subject subject : UniversityData.subjects) {
            if (subject.getId() == id) {
                return subject;
            }
        }
        return null;
    }

    @Override
    public void displayAll() {
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-10s %-20s %-10s %-15s\n", "ID", "Name", "ECTs", "Type");
        System.out.println("-----------------------------------------------------------");

        for (Subject subject : UniversityData.subjects) {
            System.out.printf("%-10d %-20s %-10d %-15s\n",
                    subject.getId(),
                    subject.getName(),
                    subject.getECTs(),
                    subject.getType());
        }

        System.out.println("-----------------------------------------------------------");
    }

    @Override
    public void delete() {
        System.out.print("Enter subject ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Subject subject = findById(id);

            if (subject != null) {
                removeSubjectFromStudents(subject);
                removeSubjectFromTeachers(subject);
                removeSubjectFromClasses(subject);
                UniversityData.removeSubject(subject);
                System.out.println("Subject removed: " + subject);
            } else {
                System.out.println("Subject not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }
}
