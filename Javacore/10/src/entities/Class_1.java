package entities;

import java.util.List;

public class Class_1 {
    private String  subject;
    private List<Student> students;

    public Class_1(String subject, List<Student> students) {
        this.subject = subject;
        this.students = students;
    }

    @Override
    public String toString() {
        return "Class_1{" +
                "subject='" + subject + '\'' +
                ", students=" + students +
                '}';
    }
}
