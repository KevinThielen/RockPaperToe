package seng1.rockpapertoe;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by user on 28.04.16.
 */
public class HighscoreMockup {

    private Highscore a,b,c,d,e,f,g,h,i,j,k;

    private ArrayList<Highscore> highscores;

    /**
     * Creating some object to test with
     *
     * @author Antonios Kouklidis
     */
    public HighscoreMockup() {
        a = new Highscore();
        b = new Highscore();
        c = new Highscore();
        d = new Highscore();
        e = new Highscore();
        f = new Highscore();
        g = new Highscore();
        h = new Highscore();
        i = new Highscore();
        j = new Highscore();
        k = new Highscore();

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

    /**
     * An ArrayList with all objects will be sorted
     * The first 10 will be returned as an ArrayList
     *
     * @author Antonios Kouklidis
     */
    public ArrayList<Highscore> getTop10(){
        Collections.sort(highscores);

        ArrayList<Highscore> top10 = new ArrayList<>();

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

    /**
     * Get the current user highscore object
     *
     * @author Antonios Kouklidis
     */
    public Highscore getMyHighscore(int id){
        if(id > 0)
            for(int i=0; i < highscores.size(); i++){
                if(highscores.get(i).getId() == id)
                    return highscores.get(i);
            }
        return null;
    }
}
