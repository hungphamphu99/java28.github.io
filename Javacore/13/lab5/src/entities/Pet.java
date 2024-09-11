package entities;


import utils.Enum;

public class Pet {
    private int id;
    private static int nextId = 0;
    private String name;
    private String species;
    private int age;
    private String sex;
    private String description;
    private Enum.TYPE type;
    private String images;

    // Constructor
    public Pet( String name, String species, int age, String sex, String description, Enum.TYPE type, String images) {
        this.id = ++nextId;
        this.name = name;
        this.species = species;
        this.age = age;
        this.sex = sex;
        this.description = description;
        this.type = type;
        this.images = images;

    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public Enum.TYPE getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getImages() {
        return images;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Species: %s, Age: %d, Sex: %s, Description: %s, Type: %s, Images: %s \n",
                id, name, species, age, sex, description, type.name(), images);
    }
}
