package service.universitymanagement;

import entities.universitymanagement.Student;
import entities.universitymanagement.Teacher;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;

public class UniversityService {
    private List<Teacher> teachers=new ArrayList<>();
    private List<Student> students=new ArrayList<>();
    private List<Subject> subjects=new ArrayList<>();
    private List<Class> classes=new ArrayList<>();
}
