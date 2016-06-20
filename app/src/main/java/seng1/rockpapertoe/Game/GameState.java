package seng1.rockpapertoe.Game;

import android.util.Log;

/**
 * Created by kevin on 19.06.16.
 */
public class GameState {
    String opponent;
    int currentTurn;
    boolean opponentsTurn;
    int gameId;
    boolean gameOver;
    Cell board[][];
    ECell currentValue;
    boolean won;

    public GameState(String opponent, boolean opponentsTurn, ECell currentValue, boolean gameOver, boolean won, Cell[][] b) {
        this.opponent = opponent;
        this.opponentsTurn = opponentsTurn;
        this.currentValue = currentValue;
        this.gameOver = gameOver;
        board = new Cell[3][3];
        for(int x = 0; x<3; x++)
            for(int y = 0; y<3; y++) {
                Log.d("cell", b[x][y].toString());
                board[x][y] = new Cell(b[x][y].getOwner(), b[x][y].getValue());
            }
        this.won = won;
    }



    public boolean isGameOver() {
        return gameOver;
    }



    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }



    public Cell[][] getBoard() {
        return board;
    }



    public void setBoard(Cell[][] board) {
        this.board = board;
    }



    public ECell getCurrentValue() {
        return currentValue;
    }



    public void setCurrentValue(ECell currentValue) {
        this.currentValue = currentValue;
    }



    public boolean isWon() {
        return won;
    }


    public void setWon(boolean won) {
        this.won = won;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public boolean isOpponentsTurn() {
        return opponentsTurn;
    }

    public void setOpponentsTurn(boolean opponentsTurn) {
        this.opponentsTurn = opponentsTurn;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

}
