package seng1.rockpapertoe;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;

public class BoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        hmt = new HighscoreMockupTest();
    }

    HighscoreMockupTest hmt;

    public void getTop10(View v){

        for (HighscoreMockup hm: hmt.getTop10()) {
            Log.d("Datensatz:", hm.toString());
        }

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
