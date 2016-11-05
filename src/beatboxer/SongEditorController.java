/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kunal
 */

public class SongEditorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField songField;
    @FXML
    private TextField albumField;
    @FXML
    private TextField artistField;
    @FXML
    private TextField genreField;
    private BBSong song;
    @FXML
    private Button ok;
    @FXML
    private Button cancel;
    @FXML
    private void okExecute(){
        try{
            Update u = new Update();
            u.updateTrack(song.getId(), songField.getText());
            u.updateAlbum(song.getId(), albumField.getText());
            u.updateArtist(song.getId(), artistField.getText());
            u.updateGenre(song.getId(), genreField.getText());
            song.setName(songField.getText());
            song.setAlbum(albumField.getText());
            song.setArtist(artistField.getText());
            song.setGenre(genreField.getText());
            int index = BBGenerator.find(BeatBoxer.nowPlaying, song);
            BeatBoxer.nowPlaying.set(index, song);
            cancel();
        }
        catch(Exception e){
            ;
        }
    }
    @FXML
    private void cancel(){
        Stage currentStage = (Stage) cancel.getScene().getWindow();
        currentStage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void initData(BBSong song){
        this.song = song;
        this.songField.setText(song.getName());
        this.albumField.setText(song.getAlbum());
        this.artistField.setText(song.getArtist());
        this.genreField.setText(song.getGenre());
    }
    
}
