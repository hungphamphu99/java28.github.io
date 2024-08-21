package EX1;

public class Person  {
    private String name;
    private int age;
    private String adress;
    private int id;
    public Person(int id,String name, int age, String adress) {
        this.name = name;
        this.age = age;
        this.adress = adress;
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAdress() {
        return adress;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setId(int id) {
        this.id = id;
    }
}
