package com.example.juliusschengber1.rockpapertoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.media.MediaPlayer;



public class StartActivity extends AppCompatActivity {


    MediaPlayer background;


    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements

            mControlsView.setVisibility(View.INVISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        setupButtons();
        //startService(new Intent(getBaseContext(),BackgroundService.class));
        background = MediaPlayer.create(this, R.raw.backgroundmusic);
        background.setLooping(true);
        turnOn();

    }



    protected void setupButtons() {
    //initialize Buttons and their behaviour after clicking on them

        final Button play = (Button) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View w) {
                startActivity(new Intent(StartActivity.this, PlayActivity.class));
            }
        });

        final Button board = (Button) findViewById(R.id.board);
        board.setOnClickListener(new View.OnClickListener() {
            public void onClick(View x) {
                startActivity(new Intent(StartActivity.this, BoardActivity.class));
            }
        });

        final Button prefs = (Button) findViewById(R.id.prefs);
        prefs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View y) {
                startActivity(new Intent(StartActivity.this, PrefsActivity.class));
            }
        });
        /*final Button sound = (Button) findViewById(R.id.sound);
        sound.setOnClickListener(new View.OnClickListener() {
            public void onClick(View y) {
                if (background.isPlaying()) {
                    turnOff();
                }
                else {
                    turnOn();
                }
            }
        });
        */
    }

    protected void turnOff(){
        background.pause();
    }

    protected void turnOn(){
        background.start();

   }

}
