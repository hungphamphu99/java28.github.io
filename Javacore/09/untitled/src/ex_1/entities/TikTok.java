package ex_1.entities;

import java.util.List;

public class TikTok {
    private List<Idol> idols;
    private List<Song> songs;

    public TikTok(List<Idol> idols, List<Song> songs) {
        this.idols = idols;
        this.songs = songs;
    }

    public List<Idol> getIdols() {
        return idols;
    }

    public Idol findIdolById(int id) {
        for (Idol idol : idols) {
            if (idol.getId() == id) {
                return idol;
            }
        }
        return null;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addIdol(Idol idol) {
        this.idols.add(idol);
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }
}
