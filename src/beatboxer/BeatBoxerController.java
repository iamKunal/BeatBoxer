/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *
 * @author kunal
 */
public class BeatBoxerController implements Initializable {
    
    @FXML
    private AnchorPane root;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu fileMenu ;
    @FXML
    private Menu controlMenu;
    @FXML
    private Menu playlistMenu;
    @FXML
    private Menu helpMenu;
    @FXML
    private Label trackDetails;
    @FXML
    private Label nowPlaying;
    @FXML
    private ListView<BBSong> nowPlayingListView;
    @FXML
    private ListView<BBSong> allsongsListView;
    @FXML
    private ListView<BBItem> playlistListView;
    @FXML
    private BorderPane player;
    @FXML
    private Slider timeSlider;
    @FXML
    private Label timer;
    @FXML
    private Label totalTimer;
    @FXML
    private ToggleButton playButton;
    @FXML
    public void playMusic(){        
        BeatBoxer.play();
    }
    public void playPlaylist(BBItem playlist){
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*------------Instance of the Main BeatBoxer Class for easy Access --------*/
        BeatBoxer bb = new BeatBoxer();
        /*-------------Slider and Current Time Listeners----------------------------*/
        bb.mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(
                    ObservableValue<? extends Duration> observableValue,
                    Duration duration,
                    Duration current) {
                    if(! timeSlider.isValueChanging()){
                        timeSlider.setValue(current.toSeconds());
                        timer.setText(String.format("%02.0f:%02.0f", Math.floor(current.toSeconds()/60),Math.floor(current.toSeconds()%60)));
                        double total = bb.mediaPlayer.getTotalDuration().toSeconds();
                        totalTimer.setText(String.format("%02.0f:%02.0f", Math.floor(total/60),Math.floor(total%60)));
                        
                    }
            }
        });
        bb.mediaPlayer.totalDurationProperty().addListener((obs, oldD, newD) -> {
            timeSlider.setMax(newD.toSeconds());
        });
        timeSlider.valueChangingProperty().addListener((obs,wasCh,isCh)->{
            if(! isCh){
                bb.mediaPlayer.seek(Duration.seconds(timeSlider.getValue()));
            }
        });
        timeSlider.valueProperty().addListener((obs, oldVal,newVal) -> {
            if(! timeSlider.isValueChanging()){
                double current = bb.mediaPlayer.getCurrentTime().toSeconds();
                if(Math.abs(current - newVal.doubleValue())>0.5){
                    bb.mediaPlayer.seek(Duration.seconds(newVal.doubleValue()));
                }
            }
        });
        
        /*--------------For Play-Pause button Sync--------------------------------*/
        bb.mediaPlayer.statusProperty().addListener(new ChangeListener<MediaPlayer.Status>() {
            @Override
            public void changed(ObservableValue<? extends MediaPlayer.Status> observable, MediaPlayer.Status oldValue, MediaPlayer.Status newValue) {
                if(newValue.equals(MediaPlayer.Status.PLAYING)){
                    playButton.setSelected(true);
                }
                else
                    playButton.setScaleShape(false);
            }
        });
        
        
        nowPlaying.setText("Not Playing");
        trackDetails.setWrapText(true);
        trackDetails.setText("No track Playing.\na");
        BBSong a = new BBSong(1,"ABC","CDEF","FGH","GHI","loc");
        ObservableList<BBSong> _nowplayinglist = FXCollections.observableArrayList();
        for (int i = 0; i < 1; i++) {        
            _nowplayinglist.add(a);
        }
        ObservableList<BBSong> _allsongs = FXCollections.observableArrayList();
        for (int i = 0; i < 1; i++) {        
            _allsongs.add(a);
        }
        ObservableList<BBItem> _playlists = FXCollections.observableArrayList();
        for (int i = 0; i < 1; i++) {        
            _playlists.add(new BBItem(1,"ABC"));
        }
        allsongsListView.setItems(_allsongs);
        playlistListView.setItems(_playlists);
        nowPlayingListView.setItems(_nowplayinglist);
        trackDetails.setText(a.stringified());
//        playlistView.setMinWidth(Double.MAX_VALUE);
        /*-------------------playListView Single Click Handler-------------------*/
//        playlistListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BBItem>() {
//
//            @Override
//            public void changed(ObservableValue<? extends BBItem> observable, BBItem oldValue, BBItem newValue) {
//                // Your action here
//                System.out.println("Selected item: " + newValue.getId());
//            }
//        });
        /*-------------------nowplayingListView Double Click Handler---------------*/
        nowPlayingListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                   //Use ListView's getSelected Item
                   if(nowPlayingListView.getSelectionModel().getSelectedItem()==null)
                        System.out.println("empty1");//pass
                   else{
                        BBItem a = nowPlayingListView.getSelectionModel().getSelectedItem();
                        nowPlayingListView.getSelectionModel().select(-1);
                        //use this to do whatever you want to. Open Link etc.
                         System.out.println(a.getId());
                    }
                }
            }
        });
        /*-------------------allsongsListView Double Click Handler---------------*/
        allsongsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                   //Use ListView's getSelected Item
                   if(allsongsListView.getSelectionModel().getSelectedItem()==null)
                        System.out.println("empty2");//pass
                   else{
                        BBItem a = allsongsListView.getSelectionModel().getSelectedItem();
                        allsongsListView.getSelectionModel().select(-1);
                        //use this to do whatever you want to. Open Link etc.
                         System.out.println(a.getId());
                    }
                }
            }
        });
        
        /*-------------------playListView Double Click Handler-------------------*/
        playlistListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                   //Use ListView's getSelected Item
                   if(playlistListView.getSelectionModel().getSelectedItem()==null)
                        System.out.println("empty3");//pass
                   else{
                        BBItem a = playlistListView.getSelectionModel().getSelectedItem();
                        playlistListView.getSelectionModel().select(-1);
                        //use this to do whatever you want to. Open Link etc.
                         System.out.println(a.getId());
                    }
                }
            }
        });
    }    
    
}
