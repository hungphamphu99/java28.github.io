package ex_1.entities;

public class Song {
    private int id;
    private static int nextId = 0;
    private String singer;
    private String name;
    public Song(String singer, String name) {
        this.singer = singer;
        this.name = name;
        this.id = nextId++;
    }

    public int getId() {
        return id;
    }

    public static int getNextId() {
        return nextId;
    }

    public String getSinger() {
        return singer;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void setNextId(int nextId) {
        Song.nextId = nextId;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setName(String name) {
        this.name = name;
    }

}
