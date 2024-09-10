package entities.universitymanagement;

import data.UniversityData;
import utils.Enum;

import java.util.Objects;

public class Subject {
    private int id;
    private static int nextID = 0;
    private String name;
    private int ECTs;
    private Enum.Type type;


    public Subject(String name, int ECTs, Enum.Type type) {
        this.id = ++nextID;
        this.name = name;
        this.ECTs = ECTs;
        this.type = type;
        UniversityData.addSubject(this);

    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getECTs() {
        return ECTs;
    }

    public Enum.Type getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void setNextID(int nextID) {
        Subject.nextID = nextID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setECTs(int ECTs) {
        this.ECTs = ECTs;
    }

    public void setType(Enum.Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ECTs=" + ECTs +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id == subject.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



}
