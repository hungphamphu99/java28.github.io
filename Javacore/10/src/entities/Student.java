package entities;

public class Student {
    private int id;
    private static int nextId = 0;
    private String name;
    private int age;
    private String rank;
    public Student( String name, int age, String rank) {
        this.id = nextId++;
        this.name = name;
        this.age = age;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public static int getNextId() {
        return nextId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getRank() {
        return rank;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void setNextId(int nextId) {
        Student.nextId = nextId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", rank='" + rank + '\'' +
                '}';
    }
}
