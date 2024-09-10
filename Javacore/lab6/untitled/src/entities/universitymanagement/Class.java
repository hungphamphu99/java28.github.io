package entities.universitymanagement;

import data.UniversityData;

import java.util.ArrayList;
import java.util.List;

public class Class {
    private String id;
    private Teacher teacher;
    private List<Student> students;
    private Subject subject;


    public Class(String id, Teacher teacher, Subject subject) {
        this.id = id;
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





    public String getId() {
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

    public void setId(String id) {
        this.id = id;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }



}
