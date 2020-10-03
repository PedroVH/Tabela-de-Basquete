package org.pedrovh.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Ao rodar os testes do Game")
class GameTest {

    /*
    firstGame = jogo: 1, placar: 12, minTemp: 12, maxTemp: 12, recMin: 0, recMax: 0    no método setUp()
    secondGame= jogo: 2, placar: 24, minTemp: 12, maxTemp: 24, recMin: 0, recMax: 1    na classe maxTests{}
    thirdGame = jogo: 3, placar: 10, minTemp: 10, maxTemp: 24, recMin: 1, recMax: 1    na classe minTests{}
    */

    public Game emptyGame, firstGame;
    String errorMessage;

    @BeforeEach
    void setUp() {
        emptyGame = new Game();
        firstGame = new Game(emptyGame, 12);
        errorMessage = "não está sendo calculado corretamente.";
    }

    @Test
    @DisplayName("o número do jogo")
    void testSetJogo(){
        assertEquals(emptyGame.getJogo() + 1, firstGame.getJogo(), errorMessage);
    }

    @Nested
    @DisplayName("no teste dos mínimos")
    class minTests{
        Game thirdGame;

        @BeforeEach
        void setUp(){
            thirdGame = new Game(firstGame, 10);
        }

        @Test
        @DisplayName("o mínimo da temporada")
        void testSetMinTemp(){
            assertEquals(thirdGame.getPlacar(), thirdGame.getMinTemp(), errorMessage);
            assertNotEquals(thirdGame.getMinTemp(), firstGame.getMinTemp(), errorMessage);
        }

        @Test
        @DisplayName("o recorde mínimo")
        void testSetRecMin(){
            assertEquals(firstGame.getRecMin() + 1, thirdGame.getRecMin(), errorMessage);
        }
    }

    @Nested
    @DisplayName("no teste dos máximos")
    class maxTests{
        Game secondGame;

        @BeforeEach
        void setUp(){
            secondGame = new Game(firstGame, 24);
        }

        @Test
        @DisplayName("o máximo da temporada")
        void testSetMaxTemp(){
            assertEquals(secondGame.getPlacar(), secondGame.getMaxTemp(), errorMessage);

            assertNotEquals(secondGame.getMaxTemp(), firstGame.getMaxTemp(), errorMessage);
        }

        @Test
        @DisplayName("o recorde máximo")
        void testSetRecMax() {
            assertEquals(firstGame.getRecMax() + 1, secondGame.getRecMax(), errorMessage);
        }
    }
}