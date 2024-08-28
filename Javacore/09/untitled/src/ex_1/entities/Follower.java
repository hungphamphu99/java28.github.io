package ex_1.entities;

public class Follower extends Person {
    private int numberOfLike;
    public Follower(String name, String email, int numberOfLike) {
        super(name, email);
        this.numberOfLike = numberOfLike;
    }
    public int getNumberOfLike() {
        return numberOfLike;
    }
}
