package org.pedrovh.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import org.pedrovh.app.*;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField placarTextField;

    @FXML
    private Button adicionarJogoButton;

    @FXML
    private Button alterarJogoButton;

    @FXML
    private Button removerJogoButton;

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
        assert tableView != null : "fx:id=\"tableView\" não foi injetado: verifique seu arquivo FXML 'main.fxml'.";
        assert jogoColumn != null : "fx:id=\"jogoColumn\" não foi injetado: verifique seu arquivo FXML 'main.fxml'.";
        assert placarColumn != null : "fx:id=\"placarColumn\" não foi injetado: verifique seu arquivo FXML 'main.fxml'.";
        assert minTempColumn != null : "fx:id=\"minTempColumn\" não foi injetado: verifique seu arquivo FXML 'main.fxml'.";
        assert maxTempColumn != null : "fx:id=\"maxTempColumn\" não foi injetado: verifique seu arquivo FXML 'main.fxml'.";
        assert recMinColumn != null : "fx:id=\"recMinColumn\" não foi injetado: verifique seu arquivo FXML 'main.fxml'.";
        assert recMaxColumn != null : "fx:id=\"recMaxColumn\" não foi injetado: verifique seu arquivo FXML 'main.fxml'.";

        //Inicializa as colunas da tabela
        jogoColumn.setCellValueFactory(new PropertyValueFactory<>("jogo"));
        placarColumn.setCellValueFactory(new PropertyValueFactory<>("placar"));
        minTempColumn.setCellValueFactory(new PropertyValueFactory<>("minTemp"));
        maxTempColumn.setCellValueFactory(new PropertyValueFactory<>("maxTemp"));
        recMinColumn.setCellValueFactory(new PropertyValueFactory<>("recMin"));
        recMaxColumn.setCellValueFactory(new PropertyValueFactory<>("recMax"));

        //faz os botões não serem mais focáveis (para quando se apertar tab)
        adicionarJogoButton.setFocusTraversable(false);
        alterarJogoButton.setFocusTraversable(false);
        removerJogoButton.setFocusTraversable(false);

        try {
            gameList = SaveFile.readSavedRecord();
        }
        catch (FileNotFoundException e) {
            System.out.println("Ainda não tem nenhum save!");
        }
        catch (Exception e) {
            JOPMessages.cantReadSaveMessage(SaveFile.filepath);
        }

        tableView.setItems(gameList);
    }

    //adiciona um jogo
    public void onAdicionarJogoButtonPushed(){
        Game lastGame = new Game();

        if(validInputPlacar()){
            AddList.addOnLastGame(gameList, lastGame, numPlacar);
            SaveFile.saveRecord(gameList);
        }
    }

    //altera as informações da linha selecionada e atualiza a tabela
    public void onAlterarJogoButtonPushed(){
        Game selectedGame = tableView.getSelectionModel().getSelectedItem();

        if(selectedGame != null && validInputPlacar())
            ChangeGameList.onAlterarJogoButtonPushed(gameList, selectedGame, numPlacar);

        if(selectedGame == null)
            JOPMessages.nullSelectionMessage();
    }

    //remove linha selecionada
    public void onRemoverLinhaButtonPushed(){
        ObservableList<Game> allGames = tableView.getItems();
        Game selectedGame = tableView.getSelectionModel().getSelectedItem();

        RemList.remListTableView(allGames, selectedGame);
        SaveFile.saveRecord(gameList);
    }

    //checa se o usuário inseriu um número, maior que 0 e menor que 1000
    public boolean validInputPlacar(){
        try {
            numPlacar = Integer.parseInt(placarTextField.getText());
            placarTextField.clear();
            JOPMessages.numberBoundsMessage(numPlacar, 0, 1000);
            return true;
        }
        catch (NumberFormatException e){
            JOPMessages.numberFormatMessage();
            return false;
        }
    }

    //checa se o input do usuário no textField ou na tableView
    public void onKeyPressed(javafx.scene.input.KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            if(tableView.getSelectionModel().isEmpty())
                onAdicionarJogoButtonPushed();
            else
                onAlterarJogoButtonPushed();
        }
        else if(tableView.isFocused() && keyEvent.getCode() == KeyCode.BACK_SPACE)
            onRemoverLinhaButtonPushed();
        //checa se o usuário apertou ctrl + s e salva a tabela
        else if(keyEvent.getCode() == KeyCode.S && keyEvent.isControlDown())
            SaveFile.saveRecord(gameList);

        else if(keyEvent.getCode() == KeyCode.ESCAPE)
            tableView.getSelectionModel().clearSelection();
    }
}
