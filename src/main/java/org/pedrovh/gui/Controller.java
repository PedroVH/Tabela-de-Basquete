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

import javax.swing.*;
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

    private int numPlacar;

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

        //Inicializa as colunas da tabela
        jogoColumn.setCellValueFactory(new PropertyValueFactory<>("jogo"));
        placarColumn.setCellValueFactory(new PropertyValueFactory<>("placar"));
        minTempColumn.setCellValueFactory(new PropertyValueFactory<>("minTemp"));
        maxTempColumn.setCellValueFactory(new PropertyValueFactory<>("maxTemp"));
        recMinColumn.setCellValueFactory(new PropertyValueFactory<>("recMin"));
        recMaxColumn.setCellValueFactory(new PropertyValueFactory<>("recMax"));

        try {
            gameList = SaveFile.readSavedRecord();
        }
        catch (FileNotFoundException e) {
            System.out.println("Ainda não tem nenhum save!");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "O arquivo \"save.txt\" não pôde ser lido.");
        }

        tableView.setItems(gameList);
    }

    public void onAdicionarJogoButtonPushed(){
        Game lastGame;

        if(validInputPlacar()){
            if (!gameList.isEmpty()) {
                lastGame = gameList.get(gameList.size() - 1);
            }
            else {
                lastGame = new Game();
            }
            Game newGame = new Game(lastGame, numPlacar);
            gameList.add(newGame);
        }
    }
    //altera as informações da linha selecionada e atualiza a tabela
    public void onAlterarJogoButtonPushed(){
        Game selectedGame = tableView.getSelectionModel().getSelectedItem();

        if(selectedGame != null && validInputPlacar()){
            int currentIndex = 0;
            int selectedGameIndex = gameList.indexOf(selectedGame);
            Game lastGame = new Game();

            if(selectedGameIndex > 0)
                lastGame = gameList.get(selectedGameIndex - 1);

            gameList.set(selectedGameIndex, new Game(lastGame, numPlacar));

            tableView.getSelectionModel().clearSelection();

            for(Game game : gameList){
                if(gameList.indexOf(game) <= 0)
                    lastGame = new Game();
                else
                    lastGame = gameList.get(gameList.indexOf(game) - 1);
                gameList.set(currentIndex++, new Game(lastGame, game.getPlacar()));
            }
        }
        if(selectedGame == null)
            JOptionPane.showMessageDialog(null, "Selecione uma linha primeiro!");
    }

    //remove linha selecionada
    public void onRemoverLinhaButtonPushed(){
        ObservableList<Game> allGames = tableView.getItems();
        Game selectedGame = tableView.getSelectionModel().getSelectedItem();

        allGames.removeAll(selectedGame);
    }

    //salva a lista
    public void onSalvarButtonPushed(){
        SaveFile.saveRecord(gameList);
    }

    //checa se o usuário inseriu um número, invés de uma String, e devolve o input do usuário.
    private boolean validInputPlacar(){
        try {
            numPlacar = Integer.parseInt(placarTextField.getText());
            if(numPlacar < 0 || numPlacar > 1_000)
                throw new NumberFormatException();
            return true;
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Insira um número, entre 0 e 1000!");
            return false;
        }
    }
}
