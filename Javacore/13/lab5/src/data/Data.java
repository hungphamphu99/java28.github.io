package data;


import entities.Pet;
import utils.Enum;

import java.util.ArrayList;

public class Data {
    public static ArrayList<Pet> pets = new ArrayList<>();
    static {
        pets.add(new Pet( "Buddy1", "Golden Retriever", 3, "Male", "Friendly and playful", Enum.TYPE.DOG, "image1.jpg"));
        pets.add(new Pet( "Buddy2", "Golden Retriever", 4, "Male", "Friendly and playful", Enum.TYPE.DOG, "image1.jpg"));
        pets.add(new Pet( "Milo1", "Persian", 2, "Male", "Curious and calm", Enum.TYPE.CAT, "image2.jpg"));
        pets.add(new Pet( "Milo2", "Persian", 2, "Male", "Curious and calm", Enum.TYPE.CAT, "image2.jpg"));
        pets.add(new Pet( "Lucy1", "Labrador", 4, "Female", "Energetic and loving", Enum.TYPE.DOG, "image3.jpg"));
        pets.add(new Pet( "Lucy2", "Labrador", 5, "Female", "Energetic and loving", Enum.TYPE.DOG, "image3.jpg"));
        pets.add(new Pet( "Luna1", "Bengal", 1, "Female", "Independent and agile", Enum.TYPE.CAT, "image4.jpg"));
        pets.add(new Pet( "Luna2", "Bengal", 1, "Female", "Independent and agile", Enum.TYPE.CAT, "image4.jpg"));


        pets.add(new Pet( "Buddy3", "Golden Retriever", 3, "Male", "Friendly and playful", Enum.TYPE.DOG, "image1.jpg"));
        pets.add(new Pet( "Buddy4", "Golden Retriever", 4, "Male", "Friendly and playful", Enum.TYPE.DOG, "image1.jpg"));
        pets.add(new Pet( "Milo3", "Persian", 2, "Male", "Curious and calm", Enum.TYPE.CAT, "image2.jpg"));
        pets.add(new Pet( "Milo4", "Persian", 2, "Male", "Curious and calm", Enum.TYPE.CAT, "image2.jpg"));
        pets.add(new Pet( "Lucy3", "Labrador", 4, "Female", "Energetic and loving", Enum.TYPE.DOG, "image3.jpg"));
        pets.add(new Pet( "Lucy4", "Labrador", 5, "Female", "Energetic and loving", Enum.TYPE.DOG, "image3.jpg"));
        pets.add(new Pet( "Luna3", "Bengal", 1, "Female", "Independent and agile", Enum.TYPE.CAT, "image4.jpg"));
        pets.add(new Pet( "Luna4", "Bengal", 1, "Female", "Independent and agile", Enum.TYPE.CAT, "image4.jpg"));

    }


}
