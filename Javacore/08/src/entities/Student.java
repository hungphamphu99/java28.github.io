package entities;

import java.util.ArrayList;

public class Student extends Person {
    private static int nextid;
    private int id;
    public static ArrayList <Student> studentList = new ArrayList<Student>();
    public Student( String name, int age, String gender) {
        super(name, age, gender);
        this.id = ++nextid;

    }

    @Override
    public void getInformtion() {
        System.out.println("id: " + getID() + "name"+ getName()+"age"+ getAge()+"gender"+ getGender());
    }
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + getName() + ", Age: " + getAge() + ", Gender: " + getGender();
    }





    public static ArrayList <Student> getStudentList(){
        return studentList;
    }



    public int getID() {
        return id;
    }
}
