package seng1.rockpapertoe.Game;

/**
 * Created by Andre Tegeder
 */
public class GameStatus {
    // The unique ID from the Game
    int gameId;
    // If your turn is True the player is an order to make a move
    boolean yourTourn;
    // This is your victim at the game
    Player opponent;
    // Number of your current round
    int round;

    public GameStatus(int gameId, boolean yourTourn,Player opponent, int round ) {
        this.gameId = gameId;
        this.yourTourn = yourTourn;
        this.opponent = opponent;
        this.round = round;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public boolean isYourTourn() {
        return yourTourn;
    }

    public void setYourTourn(boolean yourTourn) {
        this.yourTourn = yourTourn;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

}

