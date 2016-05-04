package seng1.rockpapertoe;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by user on 28.04.16.
 */
public class HighscoreMockupTest {

    private HighscoreMockup a,b,c,d,e,f,g,h,i,j,k;

    private ArrayList<HighscoreMockup> highscores;

    public HighscoreMockupTest() {
        a = new HighscoreMockup();
        b = new HighscoreMockup();
        c = new HighscoreMockup();
        d = new HighscoreMockup();
        e = new HighscoreMockup();
        f = new HighscoreMockup();
        g = new HighscoreMockup();
        h = new HighscoreMockup();
        i = new HighscoreMockup();
        j = new HighscoreMockup();
        k = new HighscoreMockup();

        // Szenario a
        a.setId(1);
        a.setName("Antonios");
        a.setRanking(1);
        a.setScore(100);

        // Szenario b
        b.setId(2);
        b.setName("Joe");
        b.setRanking(2);
        b.setScore(99);

        // Szenario c
        c.setId(3);
        c.setName("Julius");
        c.setRanking(3);
        c.setScore(90);

        d.setId(4);
        d.setName("Detlef");
        d.setRanking(4);
        d.setScore(85);

        e.setId(5);
        e.setName("Johann");
        e.setRanking(5);
        e.setScore(82);

        f.setId(6);
        f.setName("Max");
        f.setRanking(6);
        f.setScore(80);

        g.setId(7);
        g.setName("Tom");
        g.setRanking(7);
        g.setScore(78);

        h.setId(8);
        h.setName("Andre");
        h.setRanking(8);
        h.setScore(59);

        i.setId(9);
        i.setName("Kevin");
        i.setRanking(9);
        i.setScore(40);

        j.setId(10);
        j.setName("Terminator");
        j.setRanking(10);
        j.setScore(20);

        k.setId(11);
        k.setName("Ich bin nicht in der Top 10");
        k.setRanking(11);
        k.setScore(15);

        highscores = new ArrayList<>();

        highscores.add(c);
        highscores.add(b);
        highscores.add(a);
        highscores.add(j);
        highscores.add(i);
        highscores.add(g);
        highscores.add(d);
        highscores.add(f);
        highscores.add(e);
        highscores.add(h);
        highscores.add(k);
    }

    public ArrayList<HighscoreMockup> getTop10(){
        Collections.sort(highscores);

        ArrayList<HighscoreMockup> top10 = new ArrayList<>();

        int size = 0;

        if(highscores.size() < 10)
            size = highscores.size();
        else
            size = 10;

        for(int i = 0; i < size; i++){
            if(i < 10)
                top10.add(highscores.get(i));
        }

        return top10;
    }

    public HighscoreMockup getMyHighscore(int id){
        if(id > 0)
            for(int i=0; i < highscores.size(); i++){
                if(highscores.get(i).getId() == id)
                    return highscores.get(i);
            }
        return null;
    }
}
