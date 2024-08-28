package ex_1.entities;

public class Person {
    private String name;
    private int id;
    private static  int nextId = 0;
    private String email;
    public Person(String name, String email) {
        this.name = name;
        this.id = nextId++;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static int getNextId() {
        return nextId;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void setNextId(int nextId) {
        Person.nextId = nextId;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
