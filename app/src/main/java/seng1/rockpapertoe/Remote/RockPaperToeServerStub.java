package seng1.rockpapertoe.Remote;

import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import seng1.rockpapertoe.Game.Cell;
import seng1.rockpapertoe.Game.ECell;
import seng1.rockpapertoe.Game.GameState;
import seng1.rockpapertoe.Game.GameStatus;
import seng1.rockpapertoe.Game.Player;
import seng1.rockpapertoe.Highscore;

public class RockPaperToeServerStub {
    private RemoteManager remote;

    public RockPaperToeServerStub() {
        remote = new RemoteManager();
    }

    /*example usage*/
    public SessionResponse login(String id) {

        SoapObject response = remote.executeSoapAction("login", id);

        int returnCode = Integer.parseInt(response.getPrimitivePropertySafelyAsString("returnCode"));

        if(returnCode == 0) {
            String userName = response.getPrimitivePropertySafelyAsString("userName");
            int sessionId = Integer.parseInt(response.getPrimitivePropertySafelyAsString("sessionId"));
            return new SessionResponse(sessionId, userName);
        }
        return null;
    }

    public SessionResponse register(String id, String userName) {
        SoapObject response = remote.executeSoapAction("register", id, userName);

        int returnCode = Integer.parseInt(response.getPrimitivePropertySafelyAsString("returnCode"));

        Log.d("Response", response.toString());
        if(returnCode == 0) {
            String name = response.getPrimitivePropertySafelyAsString("userName");
            int sessionId = Integer.parseInt(response.getPrimitivePropertySafelyAsString("sessionId"));
            return new SessionResponse(sessionId, name);
        }
        return null;
    }

    public ArrayList<GameStatus> findNewGame(int sessionId) {
        ArrayList<GameStatus> games = new ArrayList<>();

        SoapObject response = remote.executeSoapAction("newGame", sessionId);
        Log.d("Response", response.toString());

        int returnCode = Integer.parseInt(response.getPrimitivePropertyAsString("returnCode"));
        if(returnCode == 0) {
            for(int i = 1; i < response.getPropertyCount(); i++) {
                SoapObject gameState = (SoapObject) response.getProperty(i);


                int gameId = Integer.parseInt(gameState.getPrimitivePropertySafelyAsString("gameId"));
                String opponent = gameState.getPrimitivePropertySafelyAsString("opponent");
                int currentTurn = Integer.parseInt(gameState.getPrimitivePropertySafelyAsString("currentTurn"));
                boolean yourTurn = !Boolean.parseBoolean(gameState.getPrimitivePropertySafelyAsString("opponentsTurn"));

                GameStatus gameStatus = new GameStatus(gameId, yourTurn, new Player(opponent), currentTurn );
                games.add(gameStatus);
            }
        }
        return games;
    }

    public GameState getGameState(int sessionId) {
        SoapObject response = remote.executeSoapAction("getGameState", sessionId);
        Log.d("Response", response.toString());

        String opponent = response.getPrimitivePropertySafelyAsString("opponent");
        boolean opponentsTurn = Boolean.parseBoolean(response.getPrimitivePropertyAsString("opponentsTurn"));
        boolean gameOver = Boolean.parseBoolean(response.getPrimitivePropertyAsString("gameOver"));
        String currentValue = response.getPrimitivePropertyAsString("currentValue");
        boolean won = Boolean.parseBoolean(response.getPrimitivePropertyAsString("won"));

        Cell[][] board = new Cell[3][3];
        for(int x = 0; x<3; x++) {
            for(int y = 0; y<3; y++) {
                board[x][y] = new Cell();
            }
        }
        return new GameState(opponent, opponentsTurn,  ECell.valueOf(currentValue), gameOver, won, board);

    }
    public ArrayList<GameStatus> getGames(int sessionId) {
        ArrayList<GameStatus> games = new ArrayList<>();

        SoapObject response = remote.executeSoapAction("getGames", sessionId);
        Log.d("Response", response.toString());

        int returnCode = Integer.parseInt(response.getPrimitivePropertyAsString("returnCode"));
        if(returnCode == 0) {
            Log.d("GameSTati", "Number of properties: "+response.getPropertyCount());

            for(int i = 1; i < response.getPropertyCount(); i++) {

                SoapObject gameState = (SoapObject) response.getProperty(i);


                int gameId = Integer.parseInt(gameState.getPrimitivePropertySafelyAsString("gameId"));
                Log.d("Game id ", "Id: "+gameId);
                String opponent = gameState.getPrimitivePropertySafelyAsString("opponent");
                int currentTurn = Integer.parseInt(gameState.getPrimitivePropertySafelyAsString("currentTurn"));
                boolean yourTurn = !Boolean.parseBoolean(gameState.getPrimitivePropertySafelyAsString("opponentsTurn"));

                GameStatus gameStatus = new GameStatus(gameId, yourTurn, new Player(opponent), currentTurn );
                games.add(gameStatus);
            }
        }
        return games;
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
