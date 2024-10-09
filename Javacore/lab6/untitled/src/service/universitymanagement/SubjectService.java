package service.universitymanagement;

import data.UniversityData;
import entities.universitymanagement.Class;
import entities.universitymanagement.Student;
import entities.universitymanagement.Subject;
import entities.universitymanagement.Teacher;
import utils.Enum;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class SubjectService {
    Scanner scanner = new Scanner(System.in);

    public boolean isSubjectNameExists(String name) {
        for (Subject subject : UniversityData.subjects) {
            if (subject.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void addSubject() {
        System.out.print("Enter subject name: ");
        String name = scanner.nextLine();

        if (isSubjectNameExists(name)) {
            System.out.println("Subject name already exists. Please enter a different name.");
            return;
        }

        System.out.print("Enter number of ECTs: ");
        int ECTs = Integer.parseInt(scanner.nextLine());

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
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        Subject subject = new Subject(name, ECTs, type);
        UniversityData.addSubject(subject);
        System.out.println("Subject added: " + subject);
    }

    public Subject findSubjectByID(int id) {
        for (Subject subject : UniversityData.subjects) {
            if (subject.getId() == id) {
                return subject;
            }
        }
        return null;
    }

    // Fix: Updated to handle Map<Subject, Score> for students
    private void removeSubjectFromStudents(Subject subject) {
        List<Student> students = UniversityData.students;

        for (Student student : students) {
            if (student.getSubjectScores().containsKey(subject)) {
                student.getSubjectScores().remove(subject);  // Fix here
                System.out.println("Removed subject from student: " + student.getId());
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

    public void deleteSubjectByID() {
        System.out.print("Enter subject ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Subject subject = findSubjectByID(id);

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

    public void updateSubjectByID() {
        System.out.print("Enter subject ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Subject subject = findSubjectByID(id);

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

    public void searchSubjectByID() {
        System.out.print("Enter subject ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Subject subject = findSubjectByID(id);
            if (subject != null) {
                System.out.println("Subject found: " + subject);
            } else {
                System.out.println("Subject not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }
}
