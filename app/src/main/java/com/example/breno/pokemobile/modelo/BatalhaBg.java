package com.example.breno.pokemobile.modelo;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.breno.pokemobile.R;

import java.io.IOException;

/**
 * Created by User on 12/1/2016.
 */

public class BatalhaBg {
    private static MediaPlayer sound;
    public static void playLoop(Context ctx, int raw_id){
        sound = MediaPlayer.create(ctx, raw_id);
        sound.setLooping(true); // Set looping
        sound.start();
    }
    public static void stop(){
        sound.stop();
    }
    public static boolean isSoundPlaying(){
        if(sound == null){
            return false;
        }
        else{
            return sound.isPlaying();
        }
    }
}
