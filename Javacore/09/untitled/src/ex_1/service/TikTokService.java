package ex_1.service;

import ex_1.entities.Follower;
import ex_1.entities.Idol;
import ex_1.entities.Song;
import ex_1.entities.TikTok;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;


public class TikTokService {

    private TikTok tiktok;
    Scanner scanner = new Scanner(System.in);

    public TikTokService() {
        List<Idol> idolList = new ArrayList<>();
        List<Song> songList = new ArrayList<>();

        tiktok = new TikTok(idolList, songList);

        scanner = new Scanner(System.in);
    }

    public void start() {
        int choice = 0;
        while (choice != 6) {
            System.out.println("TikTok Management System");
            System.out.println("1. Add Idol");
            System.out.println("2. Add Follower to an Idol");
            System.out.println("3. Add Song");
            System.out.println("4. Display All Idols");
            System.out.println("5. Display All Songs");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addIdol();
                        break;
                    case 2:
                        addFollowerToIdol();
                        break;
                    case 3:
                        addSong();
                        break;
                    case 4:
                        displayAllIdols();
                        break;
                    case 5:
                        displayAllSongs();
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
            }
        }
    }


    private void addIdol() {
        System.out.print("Enter Idol's Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Idol's Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Idol's Group: ");
        String group = scanner.nextLine();
        List<Follower> followers = new ArrayList<>();
        Idol idol = new Idol(name, email, followers, group);
        tiktok.getIdols().add(idol);
        System.out.println("Idol added successfully!");
    }

    private void addFollowerToIdol() {
        System.out.print("Enter Idol's ID: ");
        int idolId = Integer.parseInt(scanner.nextLine());
        Idol idol = tiktok.findIdolById(idolId);
        if (idol != null) {
            System.out.print("Enter Follower's Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Follower's Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Follower's Number of Likes: ");
            int numberOfLikes = Integer.parseInt(scanner.nextLine());
            Follower follower = new Follower(name, email, numberOfLikes);
            idol.getFollowers().add(follower);
            System.out.println("Follower added successfully to Idol!");
        } else {
            System.out.println("Idol not found.");
        }
    }

    private void addSong() {
        System.out.print("Enter Song's Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Singer's Name: ");
        String singer = scanner.nextLine();
        Song song = new Song(singer,name);
        tiktok.getSongs().add(song);
        System.out.println("Song added successfully!");
    }

    private void displayAllIdols() {
        System.out.println("Displaying all Idols:");
        for (Idol idol : tiktok.getIdols()) {
            System.out.println(idol);
        }
    }

    private void displayAllSongs() {
        System.out.println("Displaying all Songs:");
        for (Song song : tiktok.getSongs()) {
            System.out.println(song);
        }
    }

}


