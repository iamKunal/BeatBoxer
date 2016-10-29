/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;


/**
 *
 * @author kunal
 */
public class BBSong extends BBItem {
    private String album;
    private String artist;
    private String genre;
    private String location;
    public BBSong(){
        super();
        this.artist=this.genre=this.location="";
    }
    public BBSong(int Id, String name, String album, String artist, String genre, String location){
        super(Id,name);
        this.album=album;
        this.artist=artist;
        this.genre=genre;
        this.location=location;
    }
    @Override
    public String toString(){
        return name + "   |   " + album + "   |   " + artist + "   |   " + genre;
    }
    public void setAlbum(String album){
        this.album=album;
    }
    public void setArtist(String artist){
        this.artist=artist;
    }
    public void setGenre(String genre){
        this.genre=genre;
    }
    public String getLocation(){
        return this.location;
    }
    public String stringified(){
        return "Name\t:\t" + name + '\n'
                + "Album\t:\t" + album + '\n'
                + "Artist\t:\t" + artist + '\n'
                + "Genre\t:\t" + genre;
    }
}