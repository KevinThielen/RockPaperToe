package com.example.juliusschengber1.rockpapertoe;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * Created by JuliusSchengber1 on 08.04.16.
 */
public class BackgroundService extends Service {

    MediaPlayer background;

    @Override
    public IBinder onBind(Intent arg0){
        return null;
    }

    @Override
    public void onCreate(){
        background = MediaPlayer.create(this, R.raw.backgroundmusic);
        background.setLooping(true);
    }

    @Override
    public void onStartCommand(){
        background.start();
    }



}
