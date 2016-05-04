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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;


public class BoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        // Highscore Object for every Client
        hmt = new HighscoreMockupTest();

        //Asynchron Task
        new HighscoreDataLoadingTask().execute();

        // Database Id of current User
        this.sessionID = new Random().nextInt(11) +1;
    }

    private int sessionID;
    private HighscoreMockupTest hmt;
    private ArrayList<HighscoreMockup> top10;

    private void checkViewForUsers(ArrayList<HighscoreMockup> top10){
        this.top10 = top10;
        int size = 0;
        Boolean ownHighscoreInTop10 = false;

        if(this.top10.size() < 10)
            size = top10.size();
        else
            size = 10;

        for(int i = 0; i < size; i++) {
            if (top10.get(i).getId() == sessionID)
                ownHighscoreInTop10 = true;
        }

        createViewForUsers(size, true);

        if(!ownHighscoreInTop10)
            createViewForUsers(1, false);

    }

    private void createViewForUsers(int users, boolean ownHighscore){
        TableLayout tl = (TableLayout) findViewById(R.id.fullscreen_content);
        Boolean highscoreInTop10 = ownHighscore;
        HighscoreMockup me = null;

        for(int i = 0; i < users; i++) {
            TableRow tr = new TableRow(this);

            if (top10.get(i).getId() == sessionID && highscoreInTop10) {
                tr.setBackgroundColor(Color.RED);
                tr.getBackground().setAlpha(127);
            }
            else if(!highscoreInTop10){
                TextView tv = new TextView(this);
                tv.setTextSize(40);
                tv.setText("My Highscore");
                tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                tl.addView(tv);

                me = hmt.getMyHighscore(sessionID);

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

            tr.addView(tv);
            tr.addView(tv2);
            tr.addView(tv3);
            tl.addView(tr);
        }
    }

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

        protected ArrayList<HighscoreMockup> doInBackground(String... params){

            try {
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            return hmt.getTop10();
        }

        protected void onPostExecute(ArrayList top10){
            this.pd.dismiss();
            checkViewForUsers(top10);
        }
    }
}
