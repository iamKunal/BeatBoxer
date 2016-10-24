/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.net.URL;
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
    private ListView<String> nowPlayingListView;
    @FXML
    private ListView<String> allsongsListView;
    @FXML
    private ListView<BBItem> playlistListView;
    @FXML
    private Label playlists;
    @FXML
    private Label playlistList;
    @FXML
    private BorderPane player;
    @FXML
    private Slider timeSlider;
    @FXML
    private Label timer;
    @FXML
    private ToggleButton playButton;
    @FXML
    public void playMusic(){
        BeatBoxer.hey();
    }
    public void onClick(){
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timer.textProperty().bind(Bindings.format("%.2f",timeSlider.valueProperty()));
        nowPlaying.setText("Not Playing");
        trackDetails.setWrapText(true);
        trackDetails.setText("No track Playing.");
        ObservableList<BBItem> _playlists = FXCollections.observableArrayList();
        for (int i = 0; i < 1; i++) {        
            _playlists.add(new BBItem(1,"ABC"));
        }
        playlistListView.setItems(_playlists);
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
        
        /*-------------------playListView Double Click Handler-------------------*/
        playlistListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if(playlistListView.getSelectionModel().getSelectedItem()==null){
                        //Do Nothing
                }
                else if (click.getClickCount() == 2) {
                   //Use ListView's getSelected Item
                   BBItem a = playlistListView.getSelectionModel().getSelectedItem();
                   playlistListView.getSelectionModel().select(-1);
                   //use this to do whatever you want to. Open Link etc.
                    System.out.println(a.getId());
                }
            }
        });
    }    
    
}
