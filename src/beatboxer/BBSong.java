/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.nio.file.Paths;

/**
 *
 * @author kunal
 */
public class BBSong extends BBItem {

    private String album;
    private static final String SEP = "   -   ";

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }
    private String artist;
    private String genre;
    private String location;
    private boolean isFavourite;

    public BBSong() {
        super();
        this.artist = this.genre = this.location = "Unknown";
//        this.isFavourite=false;
    }

    public BBSong(int Id, String name, String album, String artist, String genre, String location) {
        this.Id = Id;
        this.setAlbum(album);
        this.setArtist(artist);
        this.setGenre(genre);
        this.setLocation(location);
        this.setName(name);

    }

    public BBSong(int Id, String name, String album, String artist, String genre, String location, boolean isFavourite) {
        this(Id, name, album, artist, genre, location);
        this.setFavourite(isFavourite);
    }

    @Override
    public String toString() {
        return name + SEP + album + SEP + artist + SEP + genre;
    }

    @Override
    public void setName(String name) {
        try {
            this.name = name;
            if (name == "") {
                this.name = Paths.get(location).getFileName().toString();
            }
        } catch (NullPointerException e) {
            this.name = Paths.get(location).getFileName().toString();
        }
    }

    public void setAlbum(String album) {
        try {
            this.album = album;
            if (album == "") {
                this.album = "Unknown";
            }
        } catch (NullPointerException e) {
            this.album = "Unknown";
        }
    }

    public void setArtist(String artist) {
        try {
            this.artist = artist;
            if (artist == "") {
                this.artist = "Unknown";
            }
        } catch (NullPointerException e) {
            this.artist = "Unknown";
        }
    }

    public void setGenre(String genre) {
        try {
            this.genre = genre;
            if (genre == "") {
                this.genre = "Unknown";
            }
        } catch (NullPointerException e) {
            this.genre = "Unknown";
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public boolean isFavourite() {
        return this.isFavourite;
    }

    public void setFavourite(boolean isFavourite) {
        if (isFavourite == true) {
            this.isFavourite = true;
        } else {
            this.isFavourite = false;
        }
    }

    public String stringified() {
        return "Name\t:\t" + name + '\n'
                + "Album\t:\t" + album + '\n'
                + "Artist\t:\t" + artist + '\n'
                + "Genre\t:\t" + genre;
    }
}
