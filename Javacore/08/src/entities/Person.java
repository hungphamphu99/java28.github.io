package entities;

public abstract class Person {

    private String name;
    private int age;
    private String gender;
    public Person( String name, int age, String gender) {

        this.name = name;
        this.age = age;
        this.gender = gender;

    }

    public abstract void getInformtion();



    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }


}
