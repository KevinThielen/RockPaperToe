package seng1.rockpapertoe;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Antonio on 28.04.16.
 */
public class HighscoreMockup implements Comparable<HighscoreMockup> {

    private int id, score, ranking;
    private String name;

    public HighscoreMockup(){

    }

    /*
     * Getting the highscore of the player who demands it
     */
    public int getMyHighscore(int myId){
        return getId();
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }

    public void setRanking(int ranking){
        this.ranking = ranking;
    }

    public int getRanking(){
        return this.ranking;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return "ID: "+this.id+" Name: "+this.name+" Score: "+this.score+" Ranking: "+this.ranking;
    }

    @Override
    public int compareTo(HighscoreMockup another) {
        if(this.getRanking() > another.getRanking())
            return 1;
        else if(this.getRanking() == (another.getRanking()))
            return 0;
        else
            return -1;
    }
}
