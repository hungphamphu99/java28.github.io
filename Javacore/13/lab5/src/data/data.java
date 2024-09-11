package data;


import entities.Pet;
import utils.Enum;

import java.util.ArrayList;

public class data {
    private static ArrayList<Pet> pets = new ArrayList<>();
    static {
        pets.add(new Pet( "Buddy", "Golden Retriever", 3, "Male", "Friendly and playful", Enum.TYPE.DOG, "image1.jpg"));
        pets.add(new Pet( "Milo", "Persian", 2, "Male", "Curious and calm", Enum.TYPE.CAT, "image2.jpg"));
        pets.add(new Pet( "Lucy", "Labrador", 4, "Female", "Energetic and loving", Enum.TYPE.DOG, "image3.jpg"));
        pets.add(new Pet( "Luna", "Bengal", 1, "Female", "Independent and agile", Enum.TYPE.CAT, "image4.jpg"));
    }

   public static ArrayList<Pet> getPets() {
        return pets;
   }
}
