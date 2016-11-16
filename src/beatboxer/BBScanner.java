/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jaudiotagger.audio.*;
import org.jaudiotagger.tag.*;
/**
 *
 * @author kunal
 */
public class BBScanner {
    private ArrayList<String> songList=null;
    private final Object obj = new Object();
    private BBSong song;
    private HashMap<String,String> songDetailsMap = new HashMap<>();
    public BBScanner(){
        songList = new ArrayList<>();
    }
    public ArrayList<String> scan(String directory){
        File dir = new File(directory);
        for(File file : dir.listFiles()){
            if(file.isDirectory()){
                scan(file.toPath().toString());
            }
            else{
                if(file.getName().toLowerCase().endsWith(".mp3")){
                    songList.add(file.getPath());
                }
            }
        }
        return songList;
    }
    public ArrayList<String> scan(ArrayList<String> dirs){
        for(String dir : dirs){
            scan(dirs);
        }
        return songList;
    }
    public ArrayList<String> getList(){
        return songList;
    }
    public void reset(){
        songList.clear();
        
    }
    public static BBSong getMeta(String location){
        try{
            AudioFile a = AudioFileIO.read(new File(location));
            Tag tags = a.getTag();
            return new BBSong(-1,tags.getFirst(FieldKey.TITLE),
                    tags.getFirst(FieldKey.ALBUM),
                    tags.getFirst(FieldKey.ARTIST),
                    tags.getFirst(FieldKey.GENRE),
                    location);
        }
        catch(Exception e){
            ;
        }
        return null;
    }
    public ObservableList<BBSong> getMeta(ArrayList<String> paths){
        ObservableList<BBSong> list = FXCollections.observableArrayList();
        try{
            for(String path: paths){
                list.add(getMeta(path));
            }
        }
        catch (Exception e){
            ;
        }
        return list;
    }
    
}
