package ex_2.service;

import ex_2.entities.Figure;
import ex_2.entities.SummonRift;
import ex_2.entities.Team;

public class SummonRiftService {
    public void displayMatchInfo(SummonRift match) {
        System.out.println("Match Start Time: " + match.getStartTime());

        int teamIndex = 1;
        for (Team team : match.getTeams()) {
            System.out.println("Team " + teamIndex + ": " + team.getName());
            displayTeamInfo(team);
            teamIndex++;
        }
    }

    private void displayTeamInfo(Team team) {
        System.out.println("Team Name: " + team.getName());
        System.out.println("Players:");
        for (Figure player : team.getPlayers()) {
            System.out.println(player);
        }
    }
}
