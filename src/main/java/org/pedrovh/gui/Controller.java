package org.pedrovh.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.pedrovh.app.Game;
import org.pedrovh.app.SaveFile;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField placarTextField;

    @FXML
    private TableView<Game> tableView;

    @FXML
    private TableColumn<Game, Integer> jogoColumn;

    @FXML
    private TableColumn<Game, Integer> placarColumn;

    @FXML
    private TableColumn<Game, Integer> minTempColumn;

    @FXML
    private TableColumn<Game, Integer> maxTempColumn;

    @FXML
    private TableColumn<Game, Integer> recMinColumn;

    @FXML
    private TableColumn<Game, Integer> recMaxColumn;

    //
    ObservableList<Game> gameList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'sample.fxml'.";
        assert jogoColumn != null : "fx:id=\"jogoColumn\" was not injected: check your FXML file 'sample.fxml'.";
        assert placarColumn != null : "fx:id=\"placarColumn\" was not injected: check your FXML file 'sample.fxml'.";
        assert minTempColumn != null : "fx:id=\"minTempColumn\" was not injected: check your FXML file 'sample.fxml'.";
        assert maxTempColumn != null : "fx:id=\"maxTempColumn\" was not injected: check your FXML file 'sample.fxml'.";
        assert recMinColumn != null : "fx:id=\"recMinColumn\" was not injected: check your FXML file 'sample.fxml'.";
        assert recMaxColumn != null : "fx:id=\"recMaxColumn\" was not injected: check your FXML file 'sample.fxml'.";

        //setup the columns in the table
        jogoColumn.setCellValueFactory(new PropertyValueFactory<>("jogo"));
        placarColumn.setCellValueFactory(new PropertyValueFactory<>("placar"));
        minTempColumn.setCellValueFactory(new PropertyValueFactory<>("minTemp"));
        maxTempColumn.setCellValueFactory(new PropertyValueFactory<>("maxTemp"));
        recMinColumn.setCellValueFactory(new PropertyValueFactory<>("recMin"));
        recMaxColumn.setCellValueFactory(new PropertyValueFactory<>("recMax"));

        try {
            gameList = SaveFile.readSavedRecord();
        } catch (FileNotFoundException e) {
            System.out.println("Ainda não tem nenhum save!");
        }

        tableView.setItems(gameList);
    }

    public void onAdicionarJogoButtonPushed(){
        Game lastGame;
        int numPlacar = Integer.parseInt(placarTextField.getText());

        if (gameList != null && !gameList.isEmpty()) {
            lastGame = gameList.get(gameList.size()-1);
        }
        else {
            lastGame = new Game();
        }

        Game newGame = new Game(lastGame, numPlacar);
        gameList.add(newGame);
    }

    //removes selected roll
    public void onRemoverLinhaButtonPushed(){
        ObservableList<Game> selectedRolls, allGames;
        allGames = tableView.getItems();

        //gives selected roll
        selectedRolls = tableView.getSelectionModel().getSelectedItems();

        for(Game game: selectedRolls){
            allGames.remove(game);
        }
    }
    //saves file
    public void onSalvarButtonPushed(){
        SaveFile.saveRecord(gameList);
    }
}
