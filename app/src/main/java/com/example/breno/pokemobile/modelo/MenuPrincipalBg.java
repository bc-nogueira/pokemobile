package com.example.breno.pokemobile.modelo;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by User on 12/1/2016.
 */

public class MenuPrincipalBg {
    private static MediaPlayer sound;
    private static boolean stopped=false;
    public static void playLoop(Context ctx, int raw_id){
        if(sound == null){
            sound = MediaPlayer.create(ctx, raw_id);
            sound.setLooping(true); // Set looping
            sound.start();
        }else{
            if (stopped){
                sound = MediaPlayer.create(ctx, raw_id);
                sound.setLooping(true); // Set looping
                stopped = false;
            }
            sound.start();
        }
    }
    public static boolean isSoundPlaying(){
        if(sound == null){
            return false;
        }
        else{
            return sound.isPlaying();
        }
    }
    public static void stop(){ sound.stop(); stopped = true; }
    public static void pause(){ sound.pause(); }
    public static void play() { sound.start(); }

    public static void reset() {
        sound.reset();
    }

    public static void release() {
        sound.release();
    }
}
