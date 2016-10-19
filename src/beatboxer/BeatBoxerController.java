/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
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
    private Label playlists;
    @FXML
    private Label playlistList;
    @FXML
    private ListView<String> playlistView;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timer.textProperty().bind(Bindings.format("%.2f",timeSlider.valueProperty()));
        nowPlaying.setText("Not Playing");
        trackDetails.setWrapText(true);
        trackDetails.setText("No track Playing.");
        ObservableList<String> _playlists = FXCollections.observableArrayList();
        for (int i = 0; i < 20; i++) {        
            _playlists.add("YOYO");
        }
        playlistView.setItems(_playlists);
    }    
    
}
