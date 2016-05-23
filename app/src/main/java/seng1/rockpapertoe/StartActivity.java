package seng1.rockpapertoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;



public class StartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setupButtons();
    }



    protected void setupButtons() {
        //initialize Buttons and their behaviour after clicking on them

        final Button play = (Button) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            //start Play Activity
            public void onClick(View w) {
                startActivity(new Intent(StartActivity.this, GameActivity.class));
            }
        });

        final Button board = (Button) findViewById(R.id.board);
        board.setOnClickListener(new View.OnClickListener() {
            //start Leaderboard Activity
            public void onClick(View x) {
                startActivity(new Intent(StartActivity.this, BoardActivity.class));
            }
        });

        final Button prefs = (Button) findViewById(R.id.prefs);
        prefs.setOnClickListener(new View.OnClickListener() {
            //start Preferences Activity
            public void onClick(View y) {
                startActivity(new Intent(StartActivity.this, PrefsActivity.class));
            }
        });

    }

}
