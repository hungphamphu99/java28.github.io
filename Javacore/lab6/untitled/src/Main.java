import entities.universitymanagement.*;
import entities.universitymanagement.Class;
import view.login.LoginMenu;
import entities.login.User;
import entities.salesmanagement.Customer;
import entities.salesmanagement.Product;
import entities.salesmanagement.Staff;
import utils.Enum;

public class Main {
    public static void main(String[] args) {
        Staff staff1 = new Staff("staff","staff","staff","staff","staff","staff","staff");
        User admin = new User("admin", "admin", "admin", "Admin Name", "admin@example.com", "123 Admin Street", "555-1234");

        Product product1 = new Product("iphone",Enum.statusProduct.In_Stock,10,10.0,"Iphone");
        Product product2 = new Product("macbook",Enum.statusProduct.In_Stock,20,10.0,"Macbook");
        Product product3 = new Product("android",Enum.statusProduct.In_Stock,30,10.0,"Android");
        Product product4 = new Product("androidv2",Enum.statusProduct.Out_Stock,30,10.0,"Android");
        Customer customer1 = new Customer("customer1","customer1","customer","customer1","customer1","customer1","customer1");
        Customer customer2 = new Customer("customer2","customer2","customer","customer2","customer1","customer1","customer1");
        customer1.setBalance(10000.1);
        customer2.setBalance(10000.1);
        Teacher teacher = new Teacher("teacher", "teacher", "teacher", "Teacher 1", "teacher@example.com", "123 Teacher Street", "555-1234", Enum.Type.TECH);
        Teacher teacher2 = new Teacher("teacher2", "teacher2", "teacher", "Teacher 2", "teacher2@example.com", "123 Teacher Street", "555-1234", Enum.Type.TECH);
        Teacher teacher3 = new Teacher("teacher3", "teacher3", "teacher", "Teacher 3", "teacher3@example.com", "123 Teacher Street", "555-1234", Enum.Type.TECH);
        Teacher teacher4 = new Teacher("teacher4", "teacher4", "teacher", "Teacher 4", "teacher4@example.com", "123 Teacher Street", "555-1234", Enum.Type.LANGUAGE);
        Student student = new Student("student", "student", "student", "Student Name", "student@example.com", "456 Student Street", "555-5678");
        Student student1 = new Student("student1", "student1", "student", "Student Name", "student@example.com", "456 Student Street", "555-5678");

        Subject subject1 = new Subject("Math", 4, Enum.Type.TECH);
        Subject subject2 = new Subject("Eng", 4, Enum.Type.LANGUAGE);
        Subject subject3 = new Subject("Math02", 4, Enum.Type.TECH);
        Subject subject4 = new Subject("Math03", 4, Enum.Type.TECH);
        Subject subject5 = new Subject("Math04", 4, Enum.Type.TECH);
        Subject subject6 = new Subject("Math05", 4, Enum.Type.TECH);

        Class class1 = new Class("1", teacher2, subject1);

        Class class2 = new Class("2", teacher3, subject1);
        Class class3 = new Class("3", teacher4, subject2);
        Class class4 = new Class("4", teacher2, subject1);

        student.getSubjectScores().put(subject1, new Score(7.0,7.0, 5.6));
        student.getSubjectScores().put(subject2, new Score(7.0,8.0, 7.6));
        student.getSubjectScores().put(subject3, new Score(7.0,8.0, 7.6));
        student.getSubjectScores().put(subject4, new Score(7.0,8.0, 7.6));

        student.calculateAvgScore();

        teacher.getSubjects().add(subject5);
        teacher.getSubjects().add(subject6);

        teacher2.getSubjects().add(subject1);
        teacher3.getSubjects().add(subject1);
        teacher4.getSubjects().add(subject2);

        class1.addStudent(student);

        LoginMenu loginMenu = new LoginMenu();
        loginMenu.mainMenu();
    }
}
