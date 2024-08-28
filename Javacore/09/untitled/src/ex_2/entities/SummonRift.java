package ex_2.entities;

import java.util.ArrayList;
import java.util.List;

public class SummonRift {
    private List<Team> teams;
    private String startTime;

    public SummonRift(String startTime) {
        this.teams = new ArrayList<>();
        this.startTime = startTime;
    }

    public void addTeam(Team team) {
        if (teams.size() < 2) {
            teams.add(team);
        } else {
            System.out.println("Only two teams can participate in a match.");
        }
    }

    public List<Team> getTeams() {
        return teams;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Team getTeam(int index) {
        if (index >= 0 && index < teams.size()) {
            return teams.get(index);
        }
        return null;
    }
}
