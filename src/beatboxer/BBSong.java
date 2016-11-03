/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.io.File;
import java.nio.file.Paths;


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
        this.artist=this.genre=this.location="Unknown";
    }
    public BBSong(int Id, String name, String album, String artist, String genre, String location){
        this.setAlbum(album);
        this.setArtist(artist);
        this.setGenre(genre);
        this.setLocation(location);
        this.setName(name);
        
    }
    @Override
    public String toString(){
        return name + "   |   " + album + "   |   " + artist + "   |   " + genre;
    }
    @Override
    public void setName(String name){
        try{
               this.name = name;
               if(name==""){
                   this.name=Paths.get(location).getFileName().toString();
               }
            }
        catch (NullPointerException e){
            this.name=Paths.get(location).getFileName().toString();
        }
    }
    public void setAlbum(String album){
        try{
           this.album = album;
           if(album=="")
               this.album="Uknown";
        }
        catch (NullPointerException e){
            this.album = "Unknown";
        }
    }
    public void setArtist(String artist){
        try{
           this.artist = artist;
           if(artist=="")
               this.artist="Uknown";
        }
        catch (NullPointerException e){
            this.artist = "Unknown";
        }
    }
    public void setGenre(String genre){
        try{
           this.genre = genre;
           if(genre=="")
               this.genre="Uknown";
        }
        catch (NullPointerException e){
            this.genre = "Unknown";
        }
    }
    public void setLocation(String location){
        this.location = location;
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