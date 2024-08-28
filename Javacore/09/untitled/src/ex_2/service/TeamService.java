package ex_2.service;

import ex_2.entities.Figure;
import ex_2.entities.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamService {
    private Scanner scanner = new Scanner(System.in);
    private FigureService figureService;

    public TeamService(FigureService figureService) {
        this.figureService = figureService;
    }

    public void addPlayer(Team team) {
        List<Figure> players = team.getPlayers();
        List<String> selectedChampions = new ArrayList<>();

        while (players.size() < 5) {
            System.out.println("Available Champions:");
            List<String> availableChampions = figureService.getAvailableChampions();
            for (int i = 0; i < availableChampions.size(); i++) {
                System.out.println((i + 1) + ". " + availableChampions.get(i));
            }

            int choice = -1;
            while (true) {
                System.out.println("Select a champion by number:");
                String input = scanner.nextLine();

                try {
                    choice = Integer.parseInt(input);
                    if (choice < 1 || choice > availableChampions.size()) {
                        System.out.println("Invalid choice, please try again.");
                    } else {
                        String championName = availableChampions.get(choice - 1);

                        if (selectedChampions.contains(championName)) {
                            System.out.println(" Choose another champion.");
                        } else {
                            break;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }

            String championName = availableChampions.get(choice - 1);

            String position;
            while (true) {
                System.out.println("Enter player position (Top, Jungle, Mid, ADC, Support):");
                position = scanner.nextLine();

                if (!figureService.isValidPosition(position)) {
                    System.out.println("Invalid position. Please enter one of the following: Top, Jungle, Mid, ADC, Support.");
                } else if (isPositionSelected(players, position)) {
                    System.out.println(" Choose another position.");
                } else {
                    break;
                }
            }

            Figure player = new Figure(championName, position);
            players.add(player);
            selectedChampions.add(championName);
        }
    }

    private boolean isPositionSelected(List<Figure> players, String position) {
        for (Figure player : players) {
            if (player.getPosition().equalsIgnoreCase(position)) {
                return true;
            }
        }
        return false;
    }

    public void displayTeamInfo(Team team) {
        System.out.println("Team Name: " + team.getName());
        System.out.println("Players:");
        for (Figure player : team.getPlayers()) {
            System.out.println(player);
        }
    }
}
