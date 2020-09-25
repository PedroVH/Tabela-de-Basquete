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
        Scene scene = new Scene(loadFXML());
        stage.setScene(scene);
        stage.setTitle("Tabela de Basquete");
        stage.setMinHeight(450);
        stage.setMinWidth(800);
        stage.show();
    }

    private static Parent loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}