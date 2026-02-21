package com.my.televip;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

public class audio {

    public static boolean playing = false;
    private static MediaPlayer mediaPlayer;

    public static void init(){
            mediaPlayer = new MediaPlayer();

            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            mediaPlayer.setAudioAttributes(audioAttributes);
    }

    public static void start(){
        int num = (int) (Math.random() * 3);
        String audioUrl;
        if (num == 0) {
            audioUrl = "https://qurango.net/radio/abdulbasit_abdulsamad_mojawwad";
        } else if (num == 1) {
            audioUrl = "https://qurango.net/radio/yasser_aldosari";
        } else {
            audioUrl = "https://backup.qurango.net/radio/maher";
        }

        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(audioUrl);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(mp -> {
                mediaPlayer.start();
                playing = true;
            });
        } catch (IllegalArgumentException |
                 IllegalStateException |
                 IOException e) {
            playing = false;
            init();
        }
    }

    public static void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }

        if (mediaPlayer != null) {
            mediaPlayer.release();
            init();
        }
        playing = false;
    }

}
