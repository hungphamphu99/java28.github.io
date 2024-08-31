package service.universitymanagement;

import entities.universitymanagement.Teacher;
import entities.universitymanagement.Subject;
import utils.Enum;
import java.util.*;

public class TeacherService {


    private SubjectService subjectService;
    private Scanner scanner = new Scanner(System.in);

    public TeacherService() {
    }

    public List<Teacher> addTeacher(Teacher teacher,List<Teacher> teachers) {
        teachers.add(teacher);
        System.out.println("Teacher added successfully!");
        return teachers;
    }


    public void displayAllTeachers(List<Teacher> teachers) {
        if (teachers.isEmpty()) {
            System.out.println("No teachers available.");
        } else {
            System.out.println("List of all teachers:");
            for (Teacher teacher : teachers) {
                System.out.println("ID: " + teacher.getId() +
                        ", Name: " + teacher.getName() +
                        ", Email: " + teacher.getEmail() +
                        ", Phone: " + teacher.getPhone() +
                        ", Address: " + teacher.getAddress() +
                        ", Major: " + teacher.getMajor());

                List<Subject> subjectList = teacher.getSubjects();
                if (subjectList.isEmpty()) {
                    System.out.println("  Subjects: None");
                } else {
                    System.out.println("  Subjects:");
                    for (Subject subject : subjectList) {
                        System.out.println("    - " + subject.getName());
                    }
                }
            }
        }
    }

    public void searchTeacherById(int id, List<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                System.out.println("Teacher found: " + teacher);
                return;
            }
        }
        System.out.println("Teacher with ID " + id + " not found.");
    }

    public void deleteTeacherById(int id, List<Teacher> teachers) {
        Iterator<Teacher> iterator = teachers.iterator();
        while (iterator.hasNext()) {
            Teacher teacher = iterator.next();
            if (teacher.getId() == id) {
                iterator.remove();
                System.out.println("Teacher with ID " + id + " has been removed.");
                return;
            }
        }
        System.out.println("Teacher with ID " + id + " not found.");
    }

    public void assignSubjectsToTeacher(int teacherId, Subject subject,List<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == teacherId) {
                if (teacher.getSubjects().size() >= 2) {
                    System.out.println("Error: Teacher with ID " + teacherId + " cannot teach more than 2 subjects.");
                } else if (teacher.addSubject(subject)) {
                    System.out.println("Teacher with ID " + teacherId + " successfully assigned to subject: " + subject.getName());
                } else {
                    System.out.println("Error assigning subject to teacher with ID " + teacherId);
                }
                return;
            }
        }
        System.out.println("Teacher with ID " + teacherId + " not found.");
    }

    public void updateTeacherById(int id,List<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                System.out.println("Teacher found: " + teacher);

                // Update name
                System.out.println("Enter new name (or press Enter to keep current name: " + teacher.getName() + "):");
                String newName = scanner.nextLine();
                if (!newName.trim().isEmpty() && newName.matches("[a-zA-Z ]+")) {
                    teacher.setName(newName);
                }

                // Update email
                System.out.println("Enter new email (or press Enter to keep current email: " + teacher.getEmail() + "):");
                String newEmail = scanner.nextLine();
                if (!newEmail.trim().isEmpty()) {
                    teacher.setEmail(newEmail);
                }

                // Update phone
                System.out.println("Enter new phone number (or press Enter to keep current phone: " + teacher.getPhone() + "):");
                String newPhone = scanner.nextLine();
                if (!newPhone.trim().isEmpty() && newPhone.matches("[0-9]+")) {
                    teacher.setPhone(newPhone);
                }

                // Update address
                System.out.println("Enter new address (or press Enter to keep current address: " + teacher.getAddress() + "):");
                String newAddress = scanner.nextLine();
                if (!newAddress.trim().isEmpty()) {
                    teacher.setAddress(newAddress);
                }

                System.out.println("Teacher information updated successfully.");
                return;
            }
        }
        System.out.println("Teacher with ID " + id + " not found.");
    }

    public void removeSubjectFromAllTeachers(Subject subjectToRemove,List<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            boolean removed = teacher.getSubjects().removeIf(subject -> subject.getId() == subjectToRemove.getId());
            if (removed) {
                System.out.println("Removed subject " + subjectToRemove.getName() + " from teacher " + teacher.getName());
            }
        }
    }

    public void addTeacherByAdmin(List<Teacher> teachers) {
        String name;
        String phone;

        while (true) {
            System.out.println("Enter the name of the teacher:");
            name = scanner.nextLine();
            if (name.matches("[a-zA-Z ]+")) {
                break;
            } else {
                System.out.println("Please enter a valid name (letters only, no numbers).");
            }
        }

        while (true) {
            System.out.println("Enter the phone number of the teacher:");
            phone = scanner.nextLine();
            if (phone.matches("[0-9]+")) {
                break;
            } else {
                System.out.println("Please enter a valid phone number (numbers only).");
            }
        }

        System.out.println("Enter the email of the teacher:");
        String email = scanner.nextLine();
        System.out.println("Enter the address of the teacher:");
        String address = scanner.nextLine();
        System.out.println("Enter the major (specialization) of the teacher (TECH, BUSINESS, LANGUAGE):");
        String majorInput = scanner.nextLine().toUpperCase();

        Enum.Type major = Enum.Type.valueOf(majorInput);

        int id = Teacher.getNextId();

        String username = id + "-" + name.replaceAll(" ", "").toLowerCase();
        String password = String.format("%05d", new Random().nextInt(100000));
        String role = "teacher";

        Teacher teacher = new Teacher(username, password, role, name, email, address, phone, major);

        teachers.add(teacher);

        System.out.println("Teacher added successfully:" +
                " ID: " + id +
                ", Name: " + name +
                ", Username: " + username +
                ", Password: " + password +
                ", Email: " + email +
                ", Phone: " + phone +
                ", Address: " + address +
                ", Major: " + major);
    }
}
