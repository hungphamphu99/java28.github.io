package entities;


public class Student implements Iclassification {

    private String name;
    private int age;
    private double marks;
    private String classification;

    public Student(String name, int age, double marks) {
        this.name = name;
        this.age = age;
        this.marks = marks;
        this.classification = classify();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
        this.classification = classify();
    }

    public String getClassification() {
        return classification;
    }

    @Override
    public String classify() {
        if (marks >= 8) {
            return "A";
        } else if (marks > 6.5) {
            return "B";
        } else {
            return "C";
        }
    }

    @Override
    public void display() {
        System.out.println("Student Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Marks: " + marks);
        System.out.println("Classification: " + classification);
    }
}
