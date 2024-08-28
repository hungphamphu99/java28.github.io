package ex_2;

import ex_2.entities.SummonRift;
import ex_2.entities.Team;
import ex_2.service.FigureService;
import ex_2.service.TeamService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FigureService figureService = new FigureService();
        TeamService teamService = new TeamService(figureService);


        System.out.println("Enter name for Team 1:");
        String team1Name = scanner.nextLine();

        Team team1 = new Team(team1Name);
        teamService.addPlayer(team1);

        System.out.println("Enter name for Team 2:");
        String team2Name = scanner.nextLine();
        Team team2 = new Team(team2Name);

        teamService.addPlayer(team2);

        SummonRift match = new SummonRift("20:00");
        match.addTeam(team1);

        match.addTeam(team2);

        System.out.println("Match Information:");

        for (Team team : match.getTeams()) {
            teamService.displayTeamInfo(team);
        }
    }
}
