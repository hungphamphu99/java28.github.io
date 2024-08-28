package ex_2.entities;

public class Figure {
    private String name;
    private String position;

    public Figure(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Figure{name='" + name + "', position='" + position + "'}";
    }
}
