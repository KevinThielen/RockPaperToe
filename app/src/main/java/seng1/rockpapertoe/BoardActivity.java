package seng1.rockpapertoe;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import seng1.rockpapertoe.Remote.RockPaperToeServerStub;
import seng1.rockpapertoe.Remote.Session;


public class BoardActivity extends AppCompatActivity {

    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        //Async-Task execution
        new HighscoreDataLoadingTask().execute();

        //this.sessionID = new Random().nextInt(11) +1;
        //this.sessionID = new Random().nextInt(10) +1;
        session = new Session(getApplicationContext());

        this.sessionID = session.getSessionId();

        stub = new RockPaperToeServerStub();
    }

    //Database ID of current user
    private static int sessionID;
    //Top10 List of gamers in app
    private ArrayList<Highscore> top10;
    //ServerStub-Object to get Data from Server
    private RockPaperToeServerStub stub;


    /**
     * Getting the Top10 as a ArrayList from Highscore
     * Checking if current Client/Player/User is in Top10 ArrayList
     * Then calling next function in dependence of the result (if user is in Top10)
     *
     * @param top10     An ArrayList with top 10 objects
     * @author          Antonios Kouklidis
     */
    private void checkViewForUsers(ArrayList<Highscore> top10){
        this.top10 = top10;
        int size = 0;
        Boolean ownHighscoreInTop10 = false;

        if(this.top10.size() < 10)
            size = top10.size();
        else
            size = 10;

        /*
        for(int i = 0; i < size; i++) {
            if (top10.get(i).getId() == sessionID)
                ownHighscoreInTop10 = true;
        }*/

        for(int i = 0; i < size; i++) {
            if (top10.get(i).getPlayerId() == sessionID)
                ownHighscoreInTop10 = true;
        }

        createViewForUsers(size, true);

        if(!ownHighscoreInTop10) {
            new MyHTask().execute(sessionID);
        }
    }

    /**
     * This function is responsible for the view
     * Top 10 player information will be added on the view
     * If current user is in top 10 list then the row where he is will be highlighted in red
     * Else the top 10 will be displayed and additional the current user under the headline My Highscore
     * @param users          => If user is in top 10 then users = 10 else user = 1
     * @param ownHighscore   => If user is in top 10 then true else false
     * @author Antonios Kouklidis
     */
    private void createViewForUsers(int users, boolean ownHighscore){
        //Finding the Layout for the view
        TableLayout tl = (TableLayout) findViewById(R.id.fullscreen_content);
        Boolean highscoreInTop10 = ownHighscore;
        // me = null;

        for(int i = 0; i < users; i++) {
            TableRow tr = new TableRow(this);

            if (top10.get(i).getPlayerId() == sessionID && highscoreInTop10) {
            //if (top10.get(i).getName().contains("Max") && highscoreInTop10) {
                tr.setBackgroundColor(Color.RED);
                tr.getBackground().setAlpha(127);
            }
            else if(!highscoreInTop10){
                TextView tv = new TextView(this);
                tv.setTextSize(40);
                tv.setText("My Highscore");
                tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                tl.addView(tv);

                //The need of the user highscore object if he is not in the top 10

                tr.setBackgroundColor(Color.RED);
                tr.getBackground().setAlpha(127);
            }

            TextView tv = new TextView(this);
            tv.setTextSize(20);
            if(highscoreInTop10)
                tv.setText("" + top10.get(i).getRanking());
            else
                tv.setText("" + me.getRanking());

            tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

            TextView tv2 = new TextView(this);
            tv2.setTextSize(20);
            if(highscoreInTop10)
                tv2.setText(top10.get(i).getName());
            else
                tv2.setText("" + me.getName());
            tv2.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

            TextView tv3 = new TextView(this);
            tv3.setTextSize(20);
            if(highscoreInTop10)
                tv3.setText("" + top10.get(i).getScore());
            else
                tv3.setText("" + me.getScore());
            tv3.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

            //Adding TextViews to TableRows
            tr.addView(tv);
            tr.addView(tv2);
            tr.addView(tv3);
            //Adding the TableRow to TableLayout
            tl.addView(tr);
        }
    }

    private static Highscore me = null;


    class MyHTask extends AsyncTask<Integer, Void, Highscore>{

        @Override
        protected Highscore doInBackground(Integer... params){
            Log.d("myAPP", "sessionID in der doInBackground-Methode: "+ sessionID);
            Highscore me = stub.getMyHighscore(sessionID);
            Log.d("myAPP", "ME Objekt: "+me.toString());
            BoardActivity.me = me;
            return me;
        }

        @Override
        protected void onPostExecute(Highscore me){
            super.onPostExecute(me);
            createViewForUsers(1, false);
        }
    }

    /**
     * Inner Class for Asyn-Task
     * onPreExecute displays a loading Element to ensure that the app is loading data
     * doInBackground is loading Data from the Database (Mockup) and returns an ArrayList with top 10 objects
     * onPostExecute hides the loading Element and displays the user (top10) and maybe an additional user(current)
     *
     * @author Antonios Kouklidis
     */
    class HighscoreDataLoadingTask extends AsyncTask<String, Void, ArrayList>{

        private ProgressDialog pd = new ProgressDialog(BoardActivity.this);

        @Override
        protected void onPreExecute(){
            this.pd.setMessage("Loading Highscores");
            this.pd.setIndeterminate(false);
            this.pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            this.pd.setCancelable(true);
            this.pd.show();
        }

        protected ArrayList<Highscore> doInBackground(String... params){

            try {
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            //return hmt.getTop10();
            return stub.getTop10();
        }

        protected void onPostExecute(ArrayList top10){
            this.pd.dismiss();
            checkViewForUsers(top10);
        }
    }
}
