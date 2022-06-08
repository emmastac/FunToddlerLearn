package com.example.funlearn;

import static android.content.Context.AUDIO_SERVICE;

import android.app.Application;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

public class AudioEffects {

    private SoundPool soundPool;
    // Maximum sound stream.
    private static final int MAX_STREAMS = 5;
    // Stream type.
    private static final int streamType = AudioManager.STREAM_MUSIC;
    private boolean loaded;
    private int soundIdCorrect;
    private int soundIdError;
    private float volume;

    public void createAudio(AppCompatActivity app, AudioManager audioManager) {

        // AudioManager audio settings for adjusting the volume
//        this.audioManager = audioManager;

        // Current volumn Index of particular stream type.
        float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);

        // Get the maximum volume index for a particular stream type.
        float maxVolumeIndex  = (float) audioManager.getStreamMaxVolume(streamType);

        // Volumn (0 --> 1)
        this.volume = currentVolumeIndex / maxVolumeIndex;

        // Suggests an audio stream whose volume should be changed by
        // the hardware volume controls.
        app.setVolumeControlStream(streamType);

        // For Android SDK >= 21
        if (Build.VERSION.SDK_INT >= 21 ) {
            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder= new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

            this.soundPool = builder.build();
        }
        // for Android SDK < 21
        else {
            // SoundPool(int maxStreams, int streamType, int srcQuality)
            this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }

        // When Sound Pool load complete.
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });

        // Load sound file (destroy.wav) into SoundPool.
        this.soundIdCorrect = this.soundPool.load(app, R.raw.correct,1);

        // Load sound file (gun.wav) into SoundPool.
        this.soundIdError = this.soundPool.load(app, R.raw.error,1);
    }


    public void playCorrect(){
        if(loaded)  {
            int streamId = this.soundPool.play(this.soundIdCorrect,volume, volume, 1, 0, 1f);
        }
    }

    public void playError(){
        if(loaded)  {
            int streamId = this.soundPool.play(this.soundIdError,volume, volume, 1, 0, 1f);
        }
    }
}
