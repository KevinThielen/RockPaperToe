package seng1.rockpapertoe;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
        new HighscoreDataTask().execute();

        sessionID = new Random().nextInt(12);
    }

    int sessionID;

    private void hierVersucheIchRowsZuErstellen(ArrayList<HighscoreMockup> top10){

        TableLayout tl = (TableLayout) findViewById(R.id.fullscreen_content);
        Boolean highscoreInTop10 = false;

        int size = 0;

        if(top10.size() < 10)
            size = top10.size();
        else
            size = 10;

        for(int i = 0; i < size; i++) {
            TableRow tr = new TableRow(this);

            TextView tv = new TextView(this);
            tv.setTextSize(20);
            tv.setText("" + top10.get(i).getRanking());
            tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

            TextView tv2 = new TextView(this);
            tv2.setTextSize(20);
            tv2.setText(top10.get(i).getName());
            tv2.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

            TextView tv3 = new TextView(this);
            tv3.setTextSize(20);
            tv3.setText("" + top10.get(i).getScore());
            tv3.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

            if (top10.get(i).getId() == sessionID) {
                highscoreInTop10 = true;
                tr.setBackgroundColor(Color.RED);
                tr.getBackground().setAlpha(127);
            }

            tr.addView(tv);
            tr.addView(tv2);
            tr.addView(tv3);
            tl.addView(tr);
        }

        if(!highscoreInTop10) {
            Log.d("Meine Randomzahl: ", ""+sessionID);
            createMyHighscore();
        }
        
    }

    private void createMyHighscore(){
        TableLayout tl = (TableLayout) findViewById(R.id.fullscreen_content);

        TextView tv = new TextView(this);
        tv.setTextSize(40);
        tv.setText("My Highscore");
        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        TableRow tr = new TableRow(this);
        tr.setBackgroundColor(Color.RED);
        tr.getBackground().setAlpha(127);
        Log.d("Meine Randomzahl: ", "" + sessionID);
        HighscoreMockup me = hmt.getMyHighscore(sessionID);

        TextView tv2 = new TextView(this);
        tv2.setTextSize(20);
        tv2.setText("" + me.getRanking());
        tv2.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        TextView tv3 = new TextView(this);
        tv3.setTextSize(20);
        tv3.setText(me.getName());
        tv3.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        TextView tv4 = new TextView(this);
        tv4.setTextSize(20);
        tv4.setText("" + me.getScore());
        tv4.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        tl.addView(tv);
        tr.addView(tv2);
        tr.addView(tv3);
        tr.addView(tv4);
        tl.addView(tr);
    }

    HighscoreMockupTest hmt;

    /*
    public void getTop10(View v){

        for (HighscoreMockup hm: hmt.getTop10()) {
            Log.d("Datensatz:", hm.toString());
        }

        hierVersucheIchRowsZuErstellen(hmt.getTop10());
    }

    public void addUser(View v){
        HighscoreMockup hm = new HighscoreMockup();
        hm.setId(4);
        hm.setName("UuUuUuUuU");
        hm.setScore(5);
        hm.setRanking(2);
        hmt.addNewUser(hm);
    }
    */

    /* 02.05.2016 */
    class HighscoreDataTask extends AsyncTask<String, Void, ArrayList>{

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
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            return hmt.getTop10();
        }

        protected void onPostExecute(ArrayList top10){
            //super.onPostExecute(result);
            this.pd.dismiss();
            hierVersucheIchRowsZuErstellen(top10);
        }
    }

}
