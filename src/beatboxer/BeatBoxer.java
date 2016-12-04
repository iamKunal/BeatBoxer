/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.io.File;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author kunal
 */
public class BeatBoxer extends Application {

    public static MediaPlayer mediaPlayer;
    public static ObservableList<BBSong> nowPlaying;
    public static boolean autoPlay = false;
    public static int currentIndex = 0;
    public static StringProperty state;
    public static BBSong defaultSong;
    public static Timeline timer;
    public static double volume;

    @Override
    public void start(Stage stage) throws Exception {
        state = new SimpleStringProperty("Unknown");
        nowPlaying = FXCollections.observableArrayList();
        defaultSong = new BBSong(1, "", "", "", "", "0.mp3");
        nowPlaying.add(defaultSong);
        mediaPlayer = toMediaPlayer(nowPlaying.get(0));
        Parent root = FXMLLoader.load(getClass().getResource("BeatBoxer.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("BeatBoxer");
        stage.getIcons().add(new Image(BeatBoxer.class.getResourceAsStream("images/BeatBoxer.png")));
        stage.show();
//        mediaPlayer.play();
        state.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("autoPlayNext")) {
//                    playNext();
                    if (currentIndex == nowPlaying.size() - 1) {
                        BBSong song = (nowPlaying.get(0));
                        play(song);
                        if (!autoPlay) {
                            mediaPlayer.pause();
                        }
                        currentIndex = 0;
                    } else {
                        BBSong song = (nowPlaying.get(++currentIndex));
                        play(song);
                    }
                } else if (newValue.equals("EOF")) {
                    mediaPlayer.stop();
                }

            }

        });
    }

    public static void play() {
        if (mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.play();
        }
    }

    public static void playNext() {
        if (currentIndex == nowPlaying.size() - 1) {
            if (autoPlay) {
                BBSong song = nowPlaying.get(0);
                currentIndex = 0;
//                System.out.println("a");
                play(song);
//                System.out.println("b");
            } else {
                mediaPlayer.stop();
            }
        } else {
            currentIndex++;
            BBSong song = nowPlaying.get(currentIndex);
            play(song);
        }
    }

    public static void play(BBSong track) {
        if (new File(track.getLocation()).exists()) {
            mediaPlayer.dispose();
            mediaPlayer = toMediaPlayer(track.getLocation());
            initMediaPlayer();
            currentIndex = BBGenerator.find(nowPlaying, track);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
        } else {
            Track t = new Track();
            t.DeleteTrack(track.getId());
            nowPlaying.remove(track);
            play(nowPlaying.get(currentIndex));
        }
    }

    public static void initMediaPlayer() {
        mediaPlayer.currentTimeProperty().addListener(BeatBoxerController.currentTimePropertyListener);
        mediaPlayer.totalDurationProperty().addListener(BeatBoxerController.totalDurationPropertyListener);
        mediaPlayer.seek(Duration.ZERO);
        mediaPlayer.statusProperty().addListener(BeatBoxerController.statusPropertyListener);
    }

    public static Media toMedia(String URI) {
        return (new Media(new File(URI).toURI().toString()));
    }

    public static Media toMedia(BBSong song) {
        return (toMedia(song.getLocation()));
    }

    public static MediaPlayer toMediaPlayer(String URI) {
        return new MediaPlayer(toMedia(URI));
    }

    public static MediaPlayer toMediaPlayer(BBSong song) {
        return toMediaPlayer(song.getLocation());
    }

    public static String getDirectory() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("JavaFX Projects");

        File defaultDirectory = new File("/");
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(new Stage());
        try {
            String path = selectedDirectory.getAbsolutePath();
            return path;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            DataBase db = new DataBase();
            db.CreateDataBase();
        } catch (Exception e) {
            ;
        }
        launch(args);
        System.exit(0);
    }

}
