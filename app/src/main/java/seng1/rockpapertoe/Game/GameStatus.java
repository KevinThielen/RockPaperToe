package seng1.rockpapertoe.Game;

/**
 * Created by andre on 14.06.2016.
 */
public class GameStatus {

    int gameId;
    boolean yourTourn;
    Player opponent;
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

