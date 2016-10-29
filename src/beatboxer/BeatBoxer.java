/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.io.File;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author kunal
 */
public class BeatBoxer extends Application {
    public static MediaPlayer mediaPlayer;
    public static ObservableList<BBSong> nowPlaying;
    public static boolean autoPlay=false;
    public static int currentId=0;
    @Override
    public void start(Stage stage) throws Exception {
        
        nowPlaying = FXCollections.observableArrayList();
        nowPlaying.add(new BBSong(1,"ABC","CDEF","FGH","GHI","/home/kunal/Documents/JAVA/siaa.mp3"));
        mediaPlayer = toMediaPlayer(nowPlaying.get(0));
        Parent root = FXMLLoader.load(getClass().getResource("BeatBoxer.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        mediaPlayer.play();
    }
    public static void play(){
        if(mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING))
            mediaPlayer.pause();
        else if(mediaPlayer.getStatus().equals(MediaPlayer.Status.STOPPED)){
            if(autoPlay){
                if(nowPlaying.size()==1){
                    mediaPlayer.play();
                }
                else if(currentId==0){
                    BBSong song = nowPlaying.get(0);
                    play(song);
                }
            }
            else {
                
            }
        }
            mediaPlayer.play();
    }
    public static void play(BBSong track){
        mediaPlayer.dispose();
        mediaPlayer = toMediaPlayer(track.getLocation());
        initMediaPlayer();
        mediaPlayer.play();
    }
    public static void initMediaPlayer(){
        mediaPlayer.currentTimeProperty().addListener(BeatBoxerController.currentTimePropertyListener);
        mediaPlayer.totalDurationProperty().addListener(BeatBoxerController.totalDurationPropertyListener);
        mediaPlayer.seek(Duration.ZERO);      
        mediaPlayer.statusProperty().addListener(BeatBoxerController.statusPropertyListener);
    }
    public static void hey(){
        System.out.println("YOLO");
    }
    public static MediaPlayer toMediaPlayer(String URI){
        return new MediaPlayer(new Media(new File(URI).toURI().toString()));
    }
    public static MediaPlayer toMediaPlayer(BBSong song){
        return toMediaPlayer(song.getLocation());
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}