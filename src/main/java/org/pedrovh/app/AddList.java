package org.pedrovh.app;

import javafx.collections.ObservableList;

public class AddList {

    public static void addOnLastGame(ObservableList<Game> gameList, Game lastGame, int numPlacar){
        if (!gameList.isEmpty())
            lastGame = gameList.get(gameList.size() - 1);

        Game newGame = new Game(lastGame, numPlacar);
        gameList.add(newGame);
    }
}
