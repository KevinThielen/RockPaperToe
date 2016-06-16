package seng1.rockpapertoe.Remote;

import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import seng1.rockpapertoe.Highscore;

public class RockPaperToeServerStub {
    private RemoteManager remote;

    public RockPaperToeServerStub() {
        remote = new RemoteManager();
    }

    /*example usage*/
    boolean login(String name, String pw) {
        //calls method with name "login" with 2 string params on the server
        SoapObject response = remote.executeSoapAction("login", name, pw);

        //if return code in soap response == 0, success
        return 0 == Integer.parseInt(response.getPrimitivePropertySafelyAsString("returnCode"));
    }

    private final String x = "myAPP";

    public Highscore getMyHighscore(int myPlayerId){
        Highscore me = null;

        String methodName = "getMyHighscore";
        SoapObject response = remote.executeSoapAction(methodName, myPlayerId);
        if(response != null)
        Log.d(x, "MY HIGHSCORE RESPONSE: "+response.toString());
        else
        Log.d(x, "LEEEEEEEEER");

        if (0 == Integer.parseInt(response.getPrimitivePropertySafelyAsString("returnCode"))){
            SoapObject o = (SoapObject) response.getProperty(1);

            int id = Integer.parseInt(o.getProperty(0).toString());
            int playerId = Integer.parseInt(o.getProperty(1).toString());
            String playerName = o.getProperty(2).toString();
            int ranking = Integer.parseInt(o.getProperty(3).toString());
            int score = Integer.parseInt(o.getProperty(4).toString());
            me = new Highscore(id,playerName,playerId,score,ranking);
            Log.d(x, "Mein Highscoreobjekt me: "+ me.toString());
        }
        else{
            Log.d(x, "Das Response-Objekt ist null");
        }

        return me;
    }

    public ArrayList<Highscore> getTop10(){
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        ArrayList<Highscore> highscores = new ArrayList<>();

        String methodName = "getTop10";

        SoapObject response = remote.executeSoapAction(methodName);

        if (0 == Integer.parseInt(response.getPrimitivePropertySafelyAsString("returnCode"))){
            Log.d(x, "Hier kann die Logik weiter programmiert werden");

            int c = 0;
            for(int i = 1; i < response.getPropertyCount(); i++) {
                SoapObject o = (SoapObject) response.getProperty(i);

                int id = Integer.parseInt(o.getProperty(0).toString());
                int playerId = Integer.parseInt(o.getProperty(1).toString());
                String playerName = o.getProperty(2).toString();
                int ranking = Integer.parseInt(o.getProperty(3).toString());
                int score = Integer.parseInt(o.getProperty(4).toString());
                Highscore h = new Highscore(id,playerName,playerId,score,ranking);
                highscores.add(h);

                Log.d(x, "Größe des Arrays: "+highscores.size());
                Log.d(x, "Forschleife: "+i+" Objekt der Arraylist: "+highscores.get(c).toString());
                c++;
            }
        }
        else{
            Log.d(x, "Das Response-Objekt ist null");
        }

        return highscores;
    }
}
