package ex_1.entities;

import java.util.ArrayList;
import java.util.List;

public class Idol extends Person {
    private List<Follower> followers = new ArrayList<>();
    private String group;
    public Idol(String name, String email, List<Follower> followers, String group) {
        super(name, email);
        this.followers = followers;
        this.group = group;
    }


    public List<Follower> getFollowers() {
        return followers;
    }

    public String getGroup() {
        return group;
    }

    public String toString() {
        return "Idol{" +
                "name='" + getName() + '\'' +
                ", id=" + getId() +
                ", email='" + getEmail() + '\'' +
                ", group='" + group + '\'' +
                ", followers=" + followers.size() +
                '}';
    }
}
