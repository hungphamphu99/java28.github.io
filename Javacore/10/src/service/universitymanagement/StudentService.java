package service.universitymanagement;

import entities.User;
import entities.universitymanagement.Student;
import entities.universitymanagement.Subject;

import java.util.*;

public class StudentService {

    private List<Student> students = new ArrayList<>();
    private UserService userService;
    Scanner scanner = new Scanner(System.in);

    public StudentService() {}
    public List<Student> getStudents() {
        return students;
    }


    public List<Student> addStudent(User student) {
        students.add((Student) student);
        return students;
    }
    public List<Student> getAllStudents() {
        return students;
    }

    public List<Student> addStudentByAdmin() {
        String name;
        String phone;

        while (true) {
            System.out.println("Enter the name of the student:");
            name = scanner.nextLine();
            if (name.matches("[a-zA-Z ]+")) {
                break;
            } else {
                System.out.println("Please enter a valid name (letters only, no numbers).");
            }
        }

        while (true) {
            System.out.println("Enter the phone number of the student:");
            phone = scanner.nextLine();
            if (phone.matches("[0-9]+")) {
                break;
            } else {
                System.out.println("Please enter a valid phone number (numbers only).");
            }
        }

        System.out.println("Enter the email of the student:");
        String email = scanner.nextLine();
        System.out.println("Enter the address of the student:");
        String address = scanner.nextLine();

        int id = Student.getNextId();

        String username = "ba-"+id + "-" + name.replaceAll(" ", "").toLowerCase();

        String password = String.format("%05d", new Random().nextInt(100000));
        String role = "student";
        Student student = new Student(username, password, role, name, email, address, phone);
        student.setId(id);
        Student.setNextId(id + 1);

        students.add(student);


        System.out.println("Student added successfully:" +
                " ID: " + id +
                ", Name: " + name +
                ", Username: " + username +
                ", Password: " + password +
                ", Email: " + email +
                ", Phone: " + phone +
                ", Address: " + address);

        return students;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            System.out.println("List of all students:");
            for (Student student : students) {
                System.out.println("ID: " + student.getId() +
                        ", Name: " + student.getName() +
                        ", Email: " + student.getEmail() +
                        ", Phone: " + student.getPhone() +
                        ", Address: " + student.getAddress());
                List<Subject> subjectList = student.getSubjects();
                if (subjectList.isEmpty()) {
                    System.out.println("  Subjects: None");
                } else {
                    System.out.println("  Subjects:");
                    for (Subject subject : subjectList) {
                        System.out.println("    - " + subject.getName() );
                    }
                }
            }
        }
    }

    public void searchStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println("Student found: " + student);
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    public void deleteStudentById(int id) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getId() == id) {
                iterator.remove();
                System.out.println("Student with ID " + id + " has been removed.");
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    public void enrollStudentInSubject(int studentId, Subject subject) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                if (student.getSubjects().size() >= 5) {
                    System.out.println("Error: Student with ID " + studentId + " cannot be enrolled in more than 5 subjects.");
                } else if (student.enrollSubject(subject)) {
                    System.out.println("Student with ID " + studentId + " successfully enrolled in subject: " + subject.getName());
                } else {
                    System.out.println("Error enrolling student with ID " + studentId + " in subject: " + subject.getName());
                }
                return;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
    }



    public void updateStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println("Student found: " + student);


                System.out.println("Enter new name (or press Enter to keep current name: " + student.getName() + "):");
                String newName = scanner.nextLine();
                if (!newName.trim().isEmpty() && newName.matches("[a-zA-Z ]+")) {
                    student.setName(newName);
                }

                System.out.println("Enter new email (or press Enter to keep current email: " + student.getEmail() + "):");
                String newEmail = scanner.nextLine();
                if (!newEmail.trim().isEmpty()) {
                    student.setEmail(newEmail);
                }

                System.out.println("Enter new phone number (or press Enter to keep current phone: " + student.getPhone() + "):");
                String newPhone = scanner.nextLine();
                if (!newPhone.trim().isEmpty() && newPhone.matches("[0-9]+")) {
                    student.setPhone(newPhone);
                }

                System.out.println("Enter new address (or press Enter to keep current address: " + student.getAddress() + "):");
                String newAddress = scanner.nextLine();
                if (!newAddress.trim().isEmpty()) {
                    student.setAddress(newAddress);
                }

                System.out.println("Student information updated successfully.");
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }
    public void removeSubjectFromAllStudents(Subject subjectToRemove) {
        for (Student student : students) {
            boolean removed = student.getSubjects().removeIf(subject -> subject.getId() == subjectToRemove.getId());
            if (removed) {
                System.out.println("Removed subject " + subjectToRemove.getName() + " from student " + student.getName());
            }
        }
    }

}

