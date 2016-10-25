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
//        Duration total=new Duration(100000);
//        BeatBoxer.mediaPlayer.currentTimeProperty().addListener();
        timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {

                Double newV = newValue.doubleValue();
                if(BeatBoxer.mediaPlayer.getStatus().equals(MediaPlayer.Status.UNKNOWN)==true){
                    timer.setText("-- : --");
                    totalTimer.setText("-- : --");
                }
                else{
                    double secs = BeatBoxer.mediaPlayer.getTotalDuration().toSeconds() * newV/100;
                    double mins = secs/60;
                    secs = secs%60;
                    timer.setText(String.format("%02.0f:%02.0f", mins,secs));
                    BeatBoxer.mediaPlayer.seek(Duration.seconds(secs));
                    secs = BeatBoxer.mediaPlayer.getTotalDuration().toSeconds();
                    mins = secs/60;
                    secs%=60;
                    totalTimer.setText(String.format("%02.0f:%02.0f", mins,secs));
                }
                
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
