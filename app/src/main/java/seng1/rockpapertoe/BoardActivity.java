package seng1.rockpapertoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


public class BoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        hmt = new HighscoreMockupTest();
    }

    private void hierVersucheIchRowsZuErstellen(View v, ArrayList<HighscoreMockup> top10){
        TableLayout tl = (TableLayout) findViewById(R.id.fullscreen_content);

        //szenario 1
        //-------------------------------------


        for(int i = 0; i < top10.size(); i++){
            TableRow tr = new TableRow(this);

            TextView tv = new TextView(this);
            tv.setTextSize(20);
            tv.setText("" + top10.get(i).getRanking());
            tv.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);

            //tl.addView(tr,0);
            //tl.removeView(tr);

            TextView tv2 = new TextView(this);
            tv2.setTextSize(20);
            tv2.setText(top10.get(i).getName());
            tv2.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

            //tl.addView(tr,1);
            //tl.removeView(tr);

            TextView tv3 = new TextView(this);
            tv3.setTextSize(20);
            tv3.setText("" + top10.get(i).getScore());
            tv3.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

            if(top10.get(i).getId() == 1) {
                tr.setBackgroundColor(Color.RED);
            }

            tr.addView(tv);
            tr.addView(tv2);
            tr.addView(tv3);
            tl.addView(tr);

            //tl.removeView(tr);
        }

        /*
        TextView tv = new TextView(this);
        tv.setText("Teeeeeeeeest");
        tv.setTextSize(20);
        tr.addView(tv);
        tl.addView(tr);
        //löschen
        tl.removeView(tr);


        //szenario 2
        TextView tv2 = new TextView(this);
        tv2.setText("Teeeeeeeeest2");
        tv2.setTextSize(20);
        tr.addView(tv2);
        tl.addView(tr);
        tl.removeView(tr);


        //szenario 3
        TextView tv3 = new TextView(this);
        tv3.setText("Teeeeeeeeest2");
        tv3.setTextSize(20);
        tr.addView(tv3);
        tl.addView(tr);
        //tl.removeView(tr);
        //-------------------------------------
        */

    }

    HighscoreMockupTest hmt;

    public void getTop10(View v){

        for (HighscoreMockup hm: hmt.getTop10()) {
            Log.d("Datensatz:", hm.toString());
        }

        hierVersucheIchRowsZuErstellen(v, hmt.getTop10());
    }

    public void addUser(View v){
        HighscoreMockup hm = new HighscoreMockup();
        hm.setId(4);
        hm.setName("UuUuUuUuU");
        hm.setScore(5);
        hm.setRanking(2);
        hmt.addNewUser(hm);
    }
}
