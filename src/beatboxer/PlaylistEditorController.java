/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author kunal
 */
public class PlaylistEditorController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private Label errorLabel;
    @FXML
    private ListView<BBSong> allSongsListView;
    @FXML
    private Button ok;
    @FXML
    private Button cancel;
    @FXML
    private Button right;
    @FXML
    private Button left;
    @FXML
    private Button up;
    @FXML
    private Button down;
    @FXML
    private ListView<BBSong> playListListView;
    private ArrayList<Pair<Integer, Integer>> instructions; //1-4 = R,L,U,D
    private BBItem playlist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instructions = new ArrayList<>();
        allSongsListView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() == -1) {
                    right.setDisable(true);
                } else {
                    right.setDisable(false);
                }
            }
        });
        playListListView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() == -1) {
                    left.setDisable(true);
                    up.setDisable(true);
                    down.setDisable(true);
                } else {
                    left.setDisable(false);
                    up.setDisable(false);
                    down.setDisable(false);
                    if (newValue.intValue() == 0) {
                        up.setDisable(true);
                    }
                    if (newValue.intValue() == playListListView.getItems().size() - 1) {
                        down.setDisable(true);
                    }
                }
            }

        });
    }

    public void initData(BBItem playlist) {
        this.playlist = playlist;
        this.nameField.setText(playlist.toString());
        ObservableList<BBSong> playlistList;
        ObservableList<BBSong> allsongList;
        playlistList = new PlayList().ShowAllTracksinPlayList(playlist.getId());
        allsongList = new Track().ShowAllTracks();
        allsongList.removeAll(playlistList);
        allSongsListView.setItems(allsongList);
        playListListView.setItems(playlistList);
    }

    @FXML
    private void checkName(KeyEvent event) {
        String newPlaylist = nameField.getText();
        newPlaylist = newPlaylist.trim();
        if (newPlaylist.equals("")) {
            ok.setDisable(true);
            errorLabel.setText("");
        } else {
            PlayList pl = new PlayList();
            ObservableList<BBItem> list = pl.ShowAllPlayLists();
            for (BBItem playList : list) {
                if (playList.getName().equalsIgnoreCase(newPlaylist) && !newPlaylist.equalsIgnoreCase(playList.getName())) {
                    ok.setDisable(true);
                    errorLabel.setText("A playlist with that name already exists. Please enter \na different name.");
                    return;
                }
            }
            if (newPlaylist.equalsIgnoreCase("All Songs") || newPlaylist.equalsIgnoreCase("Favourites")) {
                ok.setDisable(true);
                errorLabel.setText("A playlist with that name already exists. Please enter \na different name.");
                return;
            }
            errorLabel.setText("");
            ok.setDisable(false);
        }
    }

    @FXML
    private void okExecute(ActionEvent event) {
        try {
            PlayList p = new PlayList();
            for (Pair<Integer, Integer> k : instructions) {
                switch (k.getKey()) {
                    case 1:
                        p.addtrack(playlist.getId(), k.getValue());
                        break;
                    case 2:
                        p.deletetrack(playlist.getId(), k.getValue());
                        break;
                    case 3:
                        p.move(playlist.getId(), k.getValue(), "up");
                        break;
                    case 4:
                        p.move(playlist.getId(), k.getValue(), "down");
                        break;
                }
            }
            cancel(new ActionEvent());
            String playlistString = nameField.getText().trim();
            new PlayList().update(playlist.getId(), playlistString);
        } catch (Exception e) {
            ;
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage currentStage = (Stage) cancel.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void right(ActionEvent event) {
        BBSong song = allSongsListView.getSelectionModel().getSelectedItem();
        ObservableList<BBSong> list = allSongsListView.getItems();
        int index = BBGenerator.find(list, song);
        list.remove(song);
        allSongsListView.setItems(list);
        if (list.size() != index) {
            allSongsListView.getSelectionModel().clearAndSelect(index);
        }
        list = playListListView.getItems();
        list.add(song);
        playListListView.setItems(list);
        instructions.add(new Pair(1, song.getId()));
    }

    @FXML
    private void left(ActionEvent event) {
        BBSong song = playListListView.getSelectionModel().getSelectedItem();
        ObservableList<BBSong> list = playListListView.getItems();
        int index = BBGenerator.find(list, song);
        list.remove(song);
        playListListView.setItems(list);
        if (list.size() != index) {
            playListListView.getSelectionModel().clearAndSelect(index);
        }
        list = allSongsListView.getItems();
        list.add(song);
        FXCollections.sort(list, (a, b) -> a.getName().compareToIgnoreCase(b.getName()));
        allSongsListView.setItems(list);
        if (allSongsListView.getSelectionModel().getSelectedIndex() == -1) {
            allSongsListView.getSelectionModel().select(song);
        }
        instructions.add(new Pair(2, song.getId()));
    }

    @FXML
    private void up(ActionEvent event) {
        int index = playListListView.getSelectionModel().getSelectedIndex();
        BBSong song = playListListView.getSelectionModel().getSelectedItem();
        ObservableList<BBSong> list = playListListView.getItems();
        list.remove(song);
        list.add(index - 1, song);
        playListListView.setItems(list);
        playListListView.getSelectionModel().clearAndSelect(index - 1);
        instructions.add(new Pair(3, song.getId()));
    }

    @FXML
    private void down(ActionEvent event) {
        int index = playListListView.getSelectionModel().getSelectedIndex();
        BBSong song = playListListView.getSelectionModel().getSelectedItem();
        ObservableList<BBSong> list = playListListView.getItems();
        list.remove(song);
        list.add(index + 1, song);
        playListListView.setItems(list);
        playListListView.getSelectionModel().clearAndSelect(index + 1);
        instructions.add(new Pair(4, song.getId()));
    }

}
