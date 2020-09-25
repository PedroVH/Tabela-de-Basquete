package org.pedrovh;

public class Game {
    private Game lastGame;
    private int jogo;
    private int placar;
    private int minTemp;
    private int maxTemp;
    private int recMin;
    private int recMax;

    public Game(Game lastGame, int placar) {
        this.lastGame = lastGame;
        this.placar = placar;
        hasLastGame();
    }

    public Game() {
    }

    private void hasLastGame(){
        setMinTemp();
        setMaxTemp();
        setRecMin();
        setRecMax();
        setJogo();
    }

    public int getPlacar(){
        return placar;
    }

    private void setJogo(){
        jogo = lastGame.getJogo() + 1;
    }

    public int getJogo(){
        return jogo;
    }

    private void setMinTemp(){
        if(placar <= lastGame.getMinTemp() || lastGame.getMinTemp() == 0)
            minTemp = placar;
        else
            minTemp = lastGame.getMinTemp();
    }

    public int getMinTemp(){
        return minTemp;
    }

    private void setMaxTemp(){
        if(placar >= lastGame.getMaxTemp() || lastGame.getMaxTemp() == 0)
            maxTemp = placar;
        else
            maxTemp = lastGame.getMaxTemp();
    }

    public int getMaxTemp(){
        return maxTemp;
    }

    private void setRecMin(){
        if(placar < lastGame.getMinTemp())
            recMin = lastGame.getRecMin() + 1;
        else
            recMin = lastGame.getRecMin();
    }

    public int getRecMin(){
        return recMin;
    }

    private void setRecMax(){
        if(placar > lastGame.getMaxTemp() && lastGame.getMaxTemp() != 0)
            recMax = lastGame.getRecMax() + 1;
        else
            recMax = lastGame.getRecMax();
    }

    public int getRecMax(){
        return recMax;
    }
}
