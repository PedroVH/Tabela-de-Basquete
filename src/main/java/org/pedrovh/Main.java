package org.pedrovh;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Tabela de Basquete");
        stage.setMinHeight(450);
        stage.setMinWidth(800);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}