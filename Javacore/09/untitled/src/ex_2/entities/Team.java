package ex_2.entities;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Figure> players;

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Figure> getPlayers() {
        return players;
    }

    public void setPlayers(List<Figure> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Team{name='" + name + "', players=" + players + "}";
    }
}
