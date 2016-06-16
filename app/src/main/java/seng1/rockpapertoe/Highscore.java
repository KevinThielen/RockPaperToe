package seng1.rockpapertoe;

/**
 * Created by Antonio on 28.04.16.
 */
public class Highscore {

    private int id, score, ranking;
    private int playerId;
    private String playerName;

    public Highscore(int id, String playerName, int playerId,int score, int ranking){
        this.id = id;
        this.playerId = playerId;
        this.playerName = playerName;
        this.score = score;
        this.ranking = ranking;
    }

    /**
     * Getting the highscore of the player who demands it
     *
     * @author Antonios Kouklidis
     */

    public int getId(){
        return this.id;
    }

    public int getScore() { return this.score; }

    public int getPlayerId() { return this.playerId; }

    public int getRanking(){
        return this.ranking;
    }

    public String getName(){
        return this.playerName;
    }

    @Override
    public String toString(){
        return "ID: "+this.id+" Name: "+this.playerName+" Score: "+this.score+" Ranking: "+this.ranking;
    }
}
