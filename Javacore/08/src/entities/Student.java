package entities;

import java.util.ArrayList;

public class Student extends Person {
    private static int nextid;
    private int id;
    private static ArrayList <Student> studentList = new ArrayList<Student>();
    public Student( String name, int age, String gender) {
        super(name, age, gender);
        this.id = ++nextid;
        addStudent(this);
    }


    @Override
    public void getInformtion() {
        System.out.println("Student Information" +" "+this.getID() + this.getName() + " " + this.getAge() + " " + this.getGender());

    }

    public static void addStudent(Student student){
        studentList.add(student);
    }
    public static void removeStudent(Student student){
        studentList.remove(student);
    }
    public static ArrayList <Student> getStudentList(){
        return studentList;
    }

    public int getID() {
        return id;
    }
}
