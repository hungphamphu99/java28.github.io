package entities.universitymanagement;

import data.UniversityData;
import entities.login.User;

import java.util.HashMap;
import java.util.Map;

public class Student extends User {
    private int id;
    private static int nextId = 0;
    private double avgScore;

    private Map<Subject, Score> subjectScores = new HashMap<>();


    public Student(String username, String password, String role, String name, String email, String address, String phone) {
        super(username, password, role, name, email, address, phone);
        this.id = ++nextId;
        UniversityData.addStudent(this);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student{");
        sb.append("id=").append(id);
        sb.append(", username='").append(super.getUsername()).append('\'');
        sb.append(", name='").append(super.getName()).append('\'');
        sb.append(", avgScore=").append(avgScore);
        sb.append(", subjects=[");

        for (Subject subject : subjectScores.keySet()) {
            sb.append("\n   Subject{id=").append(subject.getId());
            sb.append(", name='").append(subject.getName()).append('\'');
            sb.append("},");
        }

        sb.append("\n]}");
        return sb.toString();
    }

    public String infoStudent() {
        return "Student[" +
                "id: " + id +
                ", username: " + super.getUsername() +
                ", password: " + super.getPassword() +
                ", name: " + super.getName() +
                ", email: " + super.getEmail() +
                ", address: " + super.getAddress() +
                ", phone: " + super.getPhone() +
                ']';
    }

    public String getName() {
        return super.getName();
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getNextId() {
        return nextId;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void calculateAvgScore() {
        double totalOverallScore = 0;
        int subjectCount = subjectScores.size();

        for (Score score : subjectScores.values()) {
            if (score != null) {
                totalOverallScore += score.getOverallScore();
            }
        }

        if (subjectCount > 0) {
            this.avgScore = totalOverallScore / subjectCount;
        } else {
            this.avgScore = 0;
        }
    }



    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }
    public Map<Subject, Score> getSubjectScores() {
        return subjectScores;
    }
}
