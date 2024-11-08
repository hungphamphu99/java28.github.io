package entities;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Hoàng Văn A", 20, 3));
        students.add(new Student("Nguyễn Văn B", 21, 4));
        students.add(new Student("Hoàng Văn A", 22, 3));
        students.add(new Student("Lê Văn C", 20, 5));

        students.sort(entities.StudentService.BY_NAME_THEN_AGE_DESCENDING);
        System.out.println("Sắp xếp theo fullName rồi tuổi giảm dần:");
        students.forEach(System.out::println);

        students.sort(entities.StudentService.BY_AGE_THEN_GPA_DESCENDING);
        System.out.println("\nSắp xếp theo tuổi tăng dần rồi GPA giảm dần:");
        students.forEach(System.out::println);

        students.sort(entities.StudentService.BY_LAST_NAME);
        System.out.println("\nSắp xếp theo tên cuối cùng:");
        students.forEach(System.out::println);
    }
}
