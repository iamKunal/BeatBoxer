/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author kunal
 */
public class FolderChooserController implements Initializable {

    @FXML
    private ListView<String> folderChooserListView;
    @FXML
    private Button select;
    @FXML
    private Button remove;
    @FXML
    private Button ok;
    @FXML
    private Button cancel;
    private ObservableList<String> directories;
    private ArrayList<Pair<Integer, String>> instructions;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void chooseFolder() {
        ObservableList<String> folderList = FXCollections.observableArrayList();
        folderList = folderChooserListView.getItems();
        String newFolder = BeatBoxer.getDirectory();
        if (!folderList.contains(newFolder) && !newFolder.equals("")) {
            folderList.add(newFolder);
            instructions.add(new Pair(1, newFolder));
            folderChooserListView.setItems(folderList);
        }
    }

    @FXML
    private void cancel() {
        instructions.clear();
        Stage currentStage = (Stage) cancel.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void okExecute() {
        try {
            Directory d = new Directory();
            for (Pair<Integer, String> p : instructions) {
                switch (p.getKey()) {
                    case 1:
                        d.add(p.getValue());
                        break;
                    case 2:
                        d.delete(p.getValue());
                        break;
                }
            }
        } catch (Exception e) {
            ;
        }
        scanNow();
        cancel();
    }

    @FXML
    private void remove() {
        String remDir = folderChooserListView.getSelectionModel().getSelectedItem();
        ObservableList<String> folderList = FXCollections.observableArrayList();
        folderList = folderChooserListView.getItems();
        if (folderList.contains(remDir)) {
            folderList.remove(remDir);
            instructions.add(new Pair(2, remDir));
        }
    }

    private void scanNow() {
        try {
            Directory con = new Directory();
            ArrayList<String> folders = con.Show();
            BBScanner scanner = new BBScanner();
            ObservableList<BBSong> songList;
            ArrayList<String> pathList;
            for (String folder : folders) {
                scanner.scan(folder);
            }
            pathList = scanner.getList();
            songList = scanner.getMeta(pathList);
            for (BBSong song : songList) {
                new Track().AddTrack(song.getName(), song.getArtist(), song.getAlbum(), song.getLocation(), song.getGenre());
            }
        } catch (Exception e) {
            ;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instructions = new ArrayList<>();
        directories = FXCollections.observableArrayList();
        try {
            Directory con = new Directory();
            directories.addAll(con.Show());
            folderChooserListView.setItems(directories);
        } catch (Exception e) {
            ;
        }
        folderChooserListView.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() == -1) {
                remove.setDisable(true);
            } else {
                remove.setDisable(false);
            }
        });
    }

}
