package org.pedrovh.app;

import javafx.collections.ObservableList;
import org.pedrovh.gui.JOPMessages;

public class RemList {
    public static void remListTableView(ObservableList<Game> allGames, Game selectedGame){
        if(selectedGame != null)
            allGames.removeAll(selectedGame);
        else
            JOPMessages.nullSelectionMessage();
    }
}
