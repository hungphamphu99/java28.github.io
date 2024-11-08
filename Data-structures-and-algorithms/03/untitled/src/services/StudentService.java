package entities;

import java.util.Comparator;

public class StudentService {

    public static final Comparator<Student> BY_NAME_THEN_AGE_DESCENDING = new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            int nameComparison = s1.fullName.compareTo(s2.fullName);
            if (nameComparison != 0) {
                return nameComparison;
            } else {
                return Integer.compare(s2.age, s1.age);
            }
        }
    };

    public static final Comparator<Student> BY_AGE_THEN_GPA_DESCENDING = new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            int ageComparison = Integer.compare(s1.age, s2.age);
            if (ageComparison != 0) {
                return ageComparison;
            } else {
                return Integer.compare(s2.GPA, s1.GPA);
            }
        }
    };

    public static final Comparator<Student> BY_LAST_NAME = new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            String[] nameParts1 = s1.fullName.split(" ");
            String[] nameParts2 = s2.fullName.split(" ");
            String lastName1 = nameParts1[nameParts1.length - 1];
            String lastName2 = nameParts2[nameParts2.length - 1];
            return lastName1.compareTo(lastName2);
        }
    };
}
