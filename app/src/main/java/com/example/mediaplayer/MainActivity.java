package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;
    int[] Song = {R.raw.stanza1,R.raw.stanza3,R.raw.harmoni,R.raw.simfonidanvokal};
    String[] Judul = {"Indonesia Raya - 1 Stanza","Indonesia Raya - 3 Stanza","Indonesia Raya - Harmoni","Indonesia Raya - Simfoni & Vokal"};
    int list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setMessage(String message){
        TextView t = (TextView) findViewById(R.id.title);
        t.setText(message);
    }

    public void play(View v) {
        if (player == null) {
            list = 0;
            player = MediaPlayer.create(this, Song[list]);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }

        player.start();
        setMessage(Judul[list]);
    }

    public void pause(View v) {
        if (player != null) {
            player.pause();
        }
    }

    public void next(View v) {
        if (player != null) {
            stopPlayer();
            if (list == Song.length-1){
                list = 0;
            }else{
                list++;
            }
            player = MediaPlayer.create(this, Song[list]);
            player.start();
            setMessage(Judul[list]);
        }
    }
    public void back(View v) {
        if (player != null) {
            stopPlayer();
            if (list == 0){
                list = Song.length-1;
            }else{
                list=list-1;
            }
            player = MediaPlayer.create(this, Song[list]);
            player.start();
            setMessage(Judul[list]);
        }
    }


    public void stop(View v) {
        stopPlayer();
    }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
            Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT).show();
            setMessage(Judul[0]);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}


