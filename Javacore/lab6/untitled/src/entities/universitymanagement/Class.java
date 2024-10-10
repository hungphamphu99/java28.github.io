package entities.universitymanagement;

import data.UniversityData;

import java.util.ArrayList;
import java.util.List;

public class Class {
    private int id;
    private static int nextClassId = 0;
    private Teacher teacher;
    private List<Student> students;
    private Subject subject;


    public Class( Teacher teacher, Subject subject) {
        this.id = ++nextClassId;
        this.teacher = teacher;
        this.subject = subject;
        this.students = new ArrayList<>();
        UniversityData.addClass(this);
    }

    @Override
    public String toString() {
        return "\n" +"Class{" +
                "id='" + id + '\'' +
                ", subject=" + subject.getName() + "\n" +
                ", teacher: id " +teacher.getId()+ " name: " + teacher.getName() +" major: " + teacher.getMajor()  + "\n"+
                ", students=" + students +
                '}' + "\n";
    }



    public int getId() {
        return id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public List<Student> getStudents() {
        return students;
    }
    public void addStudent(Student s) {
        students.add(s);
    }

    public Subject getSubject() {
        return subject;
    }



    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }



    public void setSubject(Subject subject) {
        this.subject = subject;
    }



}
