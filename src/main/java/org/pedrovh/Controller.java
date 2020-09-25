package org.pedrovh;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField placarTextField;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?>jogoColumn;

    @FXML
    private TableColumn<?, ?> placarColumn;

    @FXML
    private TableColumn<?, ?> minTempColumn;

    @FXML
    private TableColumn<?, ?> maxTempColumn;

    @FXML
    private TableColumn<?, ?>recMinColumn;

    @FXML
    private TableColumn<?, ?> recMaxColumn;


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
    }
}
