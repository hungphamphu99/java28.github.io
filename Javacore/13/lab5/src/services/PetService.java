package services;

import entities.Pet;
import utils.Enum;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PetService {
    Scanner scanner = new Scanner(System.in);

    private String getNonEmptyInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty! Please try again.");
            }
        } while (input.isEmpty());
        return input;
    }

    private int getValidAge(String prompt) {
        int age = -1;
        while (age <= 0) {
            try {
                System.out.print(prompt);
                age = Integer.parseInt(scanner.nextLine().trim());
                if (age <= 0) {
                    System.out.println("Age must be a positive integer! Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number for age.");
            }
        }
        return age;
    }

    private Enum.TYPE getValidType(String prompt) {
        Enum.TYPE type = null;
        while (type == null) {
            try {
                System.out.print(prompt);
                type = Enum.TYPE.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid type! Please enter 'DOG' or 'CAT'.");
            }
        }
        return type;
    }

    private String getValidSex(String prompt) {
        String sex;
        do {
            sex = getNonEmptyInput(prompt);
            if (!sex.equalsIgnoreCase("Male") && !sex.equalsIgnoreCase("Female")) {
                System.out.println("Invalid sex! Please enter 'Male' or 'Female'.");
                sex = "";
            }
        } while (sex.isEmpty());
        return sex;
    }

    public void match() {
        System.out.println("Enter your pet's information:");

        String name = getNonEmptyInput("Enter pet's name: ");
        String species = getNonEmptyInput("Enter pet's species : ");
        int age = getValidAge("Enter pet's age: ");
        String sex = getValidSex("Enter pet's sex (Male/Female): ");
        String description = getNonEmptyInput("Enter pet's description: ");
        Enum.TYPE type = getValidType("Enter pet's type (DOG/CAT): ");
        String images = getNonEmptyInput("Enter pet's image (image URL or file path): ");

        Pet userPet = new Pet( name, species, age, sex, description, type, images);

        ArrayList<Pet> matchedPets = new ArrayList<>();
        for (Pet pet : data.data.getPets()) {
            if (pet.getType() == userPet.getType() && !pet.getSex().equalsIgnoreCase(userPet.getSex())) {
                matchedPets.add(pet);
            }
        }

        if (!matchedPets.isEmpty()) {
            Random random = new Random();
            Pet matchedPet = matchedPets.get(random.nextInt(matchedPets.size()));

            System.out.println("We found a match for your pet:");
            System.out.println(matchedPet);

            String choice;
            do {
                choice = getNonEmptyInput("Do you want to find another pet match? (Y/N): ").toUpperCase();
                if (choice.equals("Y")) {
                    match();
                } else if (choice.equals("N")) {
                    System.out.println("Thank you for using our pet matching service!");
                } else {
                    System.out.println("Invalid input! Please enter 'Y' or 'N'.");
                    choice = "";
                }
            } while (choice.isEmpty());
        } else {
            System.out.println("Sorry, no matches found for your pet.");
        }
    }

    public void displayAllPets(){
        System.out.println(data.data.getPets());
    }
}