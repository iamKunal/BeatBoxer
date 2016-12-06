/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kunal
 */
public class ItemDeleterController implements Initializable {

    @FXML
    private Label detailsLabel;
    @FXML
    private HBox actionParent;
    @FXML
    private Button cancel;
    @FXML
    private HBox okParent;
    private boolean isSong;
    private BBSong song;
    private BBItem playList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initSong(BBSong song) {
        isSong = true;
        this.song = song;
        detailsLabel.setText(detailsLabel.getText() + " song ?\n" + song.getName() + " by " + song.getArtist());
    }

    public void initPlayList(BBItem playList) {
        isSong = false;
        this.playList = playList;
        detailsLabel.setText(detailsLabel.getText() + " playlist ?\n" + playList.getName());
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage currentStage = (Stage) cancel.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void okExecute(ActionEvent event) {
        try {
            if (isSong) {
                Track track = new Track();
                track.DeleteTrack(song.getId());
                if (BeatBoxer.nowPlaying.size() == 1 && BeatBoxer.nowPlaying.contains(song)) {
                    BeatBoxer.play(BeatBoxer.defaultSong);
                    BeatBoxer.mediaPlayer.stop();
                } else if (new Track().ShowAllTracks().isEmpty()) {
                    BeatBoxer.play(BeatBoxer.defaultSong);
                    BeatBoxer.mediaPlayer.stop();
                } else if (BeatBoxer.nowPlaying.get(BeatBoxer.currentIndex).equals(song)) {
                    int size = BeatBoxer.nowPlaying.size();
                    BeatBoxer.play(BeatBoxer.nowPlaying.get((BeatBoxer.currentIndex + 1) % size));
                }
                if (BeatBoxer.nowPlaying.contains(song)) {
                    BBSong newSong = BeatBoxer.nowPlaying.get(BeatBoxer.currentIndex);
                    BeatBoxer.nowPlaying.remove(song);
                    BeatBoxer.currentIndex = BBGenerator.find(BeatBoxer.nowPlaying, newSong);
                }
                File file = new File(song.getLocation());
                file.delete();
            } else {
                PlayList d = new PlayList();
                d.delete(playList.getId());
            }
            cancel(new ActionEvent());
        } catch (Exception e) {

        }

    }

}
