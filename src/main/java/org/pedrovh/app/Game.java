package org.pedrovh.app;

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

    //usado pra ler o save.txt
    public Game(String[] values){
        this.jogo = Integer.parseInt(values[0]);
        this.placar = Integer.parseInt(values[1]);
        this.minTemp = Integer.parseInt(values[2]);
        this.maxTemp = Integer.parseInt(values[3]);
        this.recMin = Integer.parseInt(values[4]);
        this.recMax = Integer.parseInt(values[5]);
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

    public void setJogo(int jogo){
        this.jogo = jogo;
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
