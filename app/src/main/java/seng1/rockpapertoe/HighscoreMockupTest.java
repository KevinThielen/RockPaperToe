package seng1.rockpapertoe;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by user on 28.04.16.
 */
public class HighscoreMockupTest {

    private HighscoreMockup a,b,c;

    ArrayList<HighscoreMockup> highscores;

    public HighscoreMockupTest() {
        a = new HighscoreMockup();
        b = new HighscoreMockup();
        c = new HighscoreMockup();

        // Szenario a
        a.setId(1);
        a.setName("Killer");
        a.setRanking(1);
        a.setScore(10);

        // Szenario b
        b.setId(2);
        b.setName("Joo");
        b.setRanking(10);
        b.setScore(1);

        // Szenario c
        c.setId(3);
        c.setName("returrrn");
        c.setRanking(3);
        c.setScore(4);

        highscores = new ArrayList<>();

        highscores.add(c);
        highscores.add(b);
        highscores.add(a);

        //Collections.sort(highscores);
    }

    public ArrayList<HighscoreMockup> getTop10(){
        Collections.sort(highscores);

        ArrayList<HighscoreMockup> top10 = new ArrayList<>();

        for(int i = 0; i < highscores.size(); i++){
            if(i < 10)
                top10.add(highscores.get(i));
        }

        return top10;
    }

    public void addNewUser(HighscoreMockup hm){
        this.highscores.add(hm);
    }

}
