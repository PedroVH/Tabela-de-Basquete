package org.pedrovh.app;

import javafx.collections.ObservableList;

public class ChangeGameList {
    public static void onAlterarJogoButtonPushed(ObservableList<Game> gameList, Game selectedGame, int numPlacar){

        int currentIndex = 0;
        int selectedGameIndex = gameList.indexOf(selectedGame);
        Game lastGame = new Game();

        //se não for o primeiro jogo
        if(selectedGameIndex > 0)
            lastGame = gameList.get(selectedGameIndex - 1);

        gameList.set(selectedGameIndex, new Game(lastGame, numPlacar));

        for(Game game : gameList){
            if(gameList.indexOf(game) <= 0)
                lastGame = new Game();
            else
                lastGame = gameList.get(gameList.indexOf(game) - 1);
            gameList.set(currentIndex++, new Game(lastGame, game.getPlacar()));
        }
    }
}
