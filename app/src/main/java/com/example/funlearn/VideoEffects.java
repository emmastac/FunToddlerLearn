package com.example.funlearn;

import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoEffects {

    private VideoView videoView;

    public void createVideo(AppCompatActivity app){

        videoView = app.findViewById(R.id.videoView1);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.setVisibility(VideoView.INVISIBLE);
                app.finish();
            }
        });
        String path = "android.resource://" + app.getPackageName() + "/" + R.raw.confetti_white;
        videoView.setVideoURI(Uri.parse(path));
    }

    public void playVideo(){

        videoView.setVisibility(VideoView.VISIBLE);
        videoView.setZOrderOnTop(true);
        videoView.start();
    }

    public void stopVideo(){
        videoView.stopPlayback();
    }


}
