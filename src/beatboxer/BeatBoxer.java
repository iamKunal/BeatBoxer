/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.*;
import javafx.stage.Stage;

/**
 *
 * @author kunal
 */
public class BeatBoxer extends Application {
    public static MediaPlayer mediaPlayer;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("BeatBoxer.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        mediaPlayer = new MediaPlayer(new Media("file://" + "/home/kunal/Documents/JAVA/cs.mp3"));
        
    }
    public static void play(){
        if(mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING))
            mediaPlayer.pause();
        else
            mediaPlayer.play();
    }
    public static void hey(){
        System.out.println("YOLO");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
