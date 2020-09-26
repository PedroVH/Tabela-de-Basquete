package org.pedrovh.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class SaveFile {
    private static final String filepath = "save.txt";

    public static void saveRecord(ObservableList<Game> gameList){
        try
        {
            FileWriter fileWriter = new FileWriter(filepath, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            gameList.forEach(game -> printWriter.println(game.getJogo() + "," + game.getPlacar() + ","
                    + game.getMinTemp() + "," + game.getMaxTemp() + "," + game.getRecMin() + "," + game.getRecMax()));

            //printWriter.println(gameList);
            printWriter.flush();
            printWriter.close();

            JOptionPane.showMessageDialog(null, "Tabela salva.");
        }
        catch (Exception E)
        {
            JOptionPane.showMessageDialog(null, "Tabela não foi salva.");
        }
    }

    public static ObservableList<Game> readSavedRecord(){
        ObservableList<Game> gameList = FXCollections.observableArrayList();
        try
        {
            Scanner scanner = new Scanner(new File(filepath));

            while(scanner.hasNext()){
                String data = scanner.next();
                String[] values = data.split(",");

                gameList.add(new Game(values));
            }

            scanner.close();
        }
        catch (Exception E)
        {
            E.printStackTrace();
        }

        return gameList;
    }
}