package ex_2.service;


import java.util.ArrayList;
import java.util.List;


import java.util.Arrays;


public class FigureService {

    private List<String> availableChampions;
    private List<String> validPositions;

    public FigureService() {
        availableChampions = new ArrayList<>(Arrays.asList(
                "Ashe", "Garen","Ahri","Lee Sin", "Lux", "Malphite", "Leona",
                "Ezreal", "Jarvan IV", "Zed", "Sion", "Thresh", "Akali"
        ));

        validPositions = new ArrayList<>(Arrays.asList(
                "Top", "Jungle", "Mid", "ADC", "Support"
        ));
    }

    public List<String> getAvailableChampions() {
        return availableChampions;
    }

    public List<String> getValidPositions() {
        return validPositions;
    }

    public boolean isValidPosition(String position) {
        return validPositions.contains(position);
    }
}
