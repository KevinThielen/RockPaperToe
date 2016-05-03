package seng1.rockpapertoe.Game;

/**
 * Created by kevin on 03.05.16.
 */

import android.util.Log;

import java.util.Random;

/**
 * Created by kevin on 24.04.16.
 */

//TODO: Runs on the server later
public class Game {
    Cell board[][];
    Player players[];
    Player currentPlayer;
    ECell currentValue;

    int currentTurn;
    boolean gameOver;
    public boolean ENABLE_AI = false;
    //TODO: Pass players to game
    public Game() {
        board = new Cell[3][3];


        currentPlayer = null;

        players = new Player[2];
        players[0] = new Player("Your");
        players[1] = new Player("Victim's");

        reset();
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void makeMove(Player player, int column, int row) {
        if(player == currentPlayer && !gameOver) {
            if(ECell.EMPTY == board[column][row].value) {
                Log.d("MOVE", "MOVE: "+column+"/"+row);
                changeCell(column, row);
                endTurn();
            }
        }
    }

    void changeCell(int column, int row) {
        board[column][row].setValue(currentValue);
        board[column][row].setOwner(currentPlayer);

        //check left neighbour
        if(column > 0 && board[column-1][row].getValue() == loseAgainst(currentValue)) {
            board[column-1][row].owner = currentPlayer;
        }
        //right neighbour
        if(column < 2 && board[column+1][row].getValue() == loseAgainst(currentValue)) {
            board[column+1][row].owner = currentPlayer;
        }
        //top neighbour
        if(row > 0 && board[column][row-1].getValue() == loseAgainst(currentValue)) {
            board[column][row-1].owner = currentPlayer;
        }
        //bottom neighbour
        if(row < 2 && board[column][row+1].getValue() == loseAgainst(currentValue)) {
            board[column][row+1].owner = currentPlayer;

        }
    }

    boolean checkWin() {

        for(int i = 0; i < 3; i++) {
            //horizontal
            Player owner = board[0][i].getOwner();
            if(owner != null && owner == board[1][i].getOwner() && owner == board[2][i].getOwner()) {
                Log.d("Game", "Player "+owner.getName()+" won!");
                return true;
            }
            //vertical
            owner = board[i][0].getOwner();
            if(owner != null && owner == board[i][1].getOwner() && owner == board[i][2].getOwner()) {
                Log.d("Game", "Player "+owner.getName()+" won!");
                return true;
            }
        }

        //diagonal
        Player owner = board[1][1].getOwner();
        if(owner != null && owner == board[0][0].getOwner() && owner == board[2][2].getOwner())
            return true;
        if(owner != null && owner == board[2][0].getOwner() && owner == board[0][2].getOwner())
            return true;


        return false;
    }

    ECell winsAgainst(ECell cell) {
        if(ECell.SCISSOR == cell) {
            return ECell.ROCK;
        }
        if(ECell.ROCK == cell) {
            return ECell.PAPER;
        }
        if(ECell.PAPER == cell) {
            return ECell.SCISSOR;
        }

        //TODO: throw exception?
        return ECell.EMPTY;
    }
    ECell loseAgainst(ECell cell) {
        if(ECell.SCISSOR == cell) {
            return ECell.PAPER;
        }
        if(ECell.ROCK == cell) {
            return ECell.SCISSOR;
        }
        if(ECell.PAPER == cell) {
            return ECell.ROCK;
        }

        //TODO: throw exception?
        return ECell.EMPTY;
    }
    void endTurn() {

        if(checkWin()) {
            gameOver = true;
            return;
        }

        if(currentPlayer == players[0])
            currentPlayer = players[1];
        else
            currentPlayer = players[0];
        currentTurn++;
        Log.d("Game", "Player "+currentPlayer.getName()+" turn.");

        currentValue = winsAgainst(currentValue);
        Log.d("VALUE", currentValue.toString());
        //TODO: simple ai hack occupies random cell on board
        if(ENABLE_AI && currentPlayer == players[1])
            aiMove();
    }

    void aiMove() {
        for(int x = 0; x<3; x++)
            for(int y = 0; y<3; y++)
                if(board[x][y].value == ECell.EMPTY) {
                    makeMove(players[1], x, y);
                    return;
                }
    }
    public Player getPlayer(int index) {
        return players[index];
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    private void reset() {
        Random random = new Random();
        int randomIndex = random.nextInt(2);
        currentPlayer = players[randomIndex];
        currentValue = ECell.SCISSOR;
        gameOver = false;

        resetBoard();
        endTurn();
    }
    private void resetBoard() {
        for(int x = 0; x<3; x++) {
            for(int y = 0; y<3; y++) {
                board[x][y] = new Cell();
            }
        }
    }
}