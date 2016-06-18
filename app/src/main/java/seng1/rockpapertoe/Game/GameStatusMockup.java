package seng1.rockpapertoe.Game;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andre Tegeder on 14.06.2016.
 */
public class GameStatusMockup {

    private GameStatus a,b,c,d,e,f,g,h,i,j,k,l,m,o,p,q,r,s,t,x,y,z;
    private Player pla,plb,plc,pld,ple,plf,plg,plh,pli,plj,plk;

    private ArrayList<GameStatus> gameStatuses;

    /**
     * Creats some test data for testing gamestatus methods.
     * @author  Andre Tegeder
     */
    public GameStatusMockup() {

        pla = new Player("Antonios");
        plb = new Player("Joe");
        plc = new Player("Julius");
        pld = new Player("Detlef");
        ple = new Player("Johann");
        plf = new Player("Max");
        plg = new Player("Andre");
        plh = new Player("Kevin");
        pli = new Player("Moritz");
        plj = new Player("Horst");
        plk = new Player("Tom");


        a = new GameStatus(1,true,pla,2);
        b = new GameStatus(2,false,plb,4);
        c = new GameStatus(4,true,plc,2);
        d = new GameStatus(3,false,pld,7);
        e = new GameStatus(16,true,ple,10);
        f = new GameStatus(11,true,plf,7);
        g = new GameStatus(1,false,plg,1);
        h = new GameStatus(19,true,plh,7);
        i = new GameStatus(8,false,pli,8);
        j = new GameStatus(6,true,plj,3);
        k = new GameStatus(5,false,plk,2);
        l = new GameStatus(1,true,pla,2);
        m = new GameStatus(2,false,plb,4);
        o = new GameStatus(4,true,plc,2);
        p = new GameStatus(3,false,pld,7);
        q = new GameStatus(16,true,ple,10);
        r = new GameStatus(11,true,plf,7);
        s = new GameStatus(1,false,plg,1);
        t = new GameStatus(19,true,plh,7);
        x = new GameStatus(8,false,pli,8);
        y = new GameStatus(6,true,plj,3);
        z = new GameStatus(5,false,plk,2);

        gameStatuses = new ArrayList<GameStatus>();

        gameStatuses.add(c);
        gameStatuses.add(b);
        gameStatuses.add(a);
        gameStatuses.add(j);
        gameStatuses.add(i);
        gameStatuses.add(g);
        gameStatuses.add(d);
        gameStatuses.add(f);
        gameStatuses.add(e);
        gameStatuses.add(h);
        gameStatuses.add(k);
        gameStatuses.add(l);
        gameStatuses.add(m);
        gameStatuses.add(o);
        gameStatuses.add(p);
        gameStatuses.add(q);
        gameStatuses.add(r);
        gameStatuses.add(s);
        gameStatuses.add(t);
        gameStatuses.add(x);
        gameStatuses.add(y);
        gameStatuses.add(z);




    }
    /**
     * Get an arraylist of gamestatus object
     * @author Antonios Kouklidis
    */
    public ArrayList<GameStatus> getGameStatuses(){

       return gameStatuses;
    }
}
