package com.example.funlearn;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class CollectionQuiz extends AppCompatActivity implements View.OnClickListener
{
    private AudioEffects audioEffects ;

    private AudioManager audioManager;

    ImageButton audio;
    ImageButton btn_one, btn_two, btn_three;
    TextView questionView;
    public static int max_quest = 10;
    ImageView[] circleY = new ImageView[10];

    private boolean foundCorrect = false;
    private String[][] questions;

    private Question question;

    private String answer;
    private int num;

    private MediaPlayer mediaPlayer;
    private VideoEffects video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_quiz);

        audioEffects = new AudioEffects();
        Bundle extras = getIntent().getExtras();
        String collection = extras.getString(MainActivity.EXTRA_MESSAGE);
        loadCollection(collection);

        btn_one = (ImageButton)findViewById(R.id.btn_one);
        btn_one.setOnClickListener(this);
        btn_two = (ImageButton)findViewById(R.id.btn_two);
        btn_two.setOnClickListener(this);
        btn_three = (ImageButton)findViewById(R.id.btn_three);
        btn_three.setOnClickListener(this);

        for(int i =0; i<10; i++){
            circleY[i] = findViewById(getResourceId(this, "id", "circley"+(i+1)));
        }

        audio = (ImageButton)findViewById(R.id.audio);
        audio.setOnClickListener(this);

        questionView = (TextView)findViewById(R.id.question);

        video = new VideoEffects();
        video.createVideo(this);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        audioEffects = new AudioEffects();
        audioEffects.createAudio(this, audioManager);
        shuffleQuestions();
        num = 0;
        NextQuestion();
    }

    public void loadCollection(String collectionName){
        switch (collectionName){

            case MainActivity.FRUITS:
                this.questions = CollectionsUtils.fruits;
                this.question = new Question(this.questions);
                break;

            case MainActivity.ANIMALS:
                this.questions = CollectionsUtils.animals;
                this.question = new Question(this.questions);
                break;

            case MainActivity.LEGUMES:
                this.questions = CollectionsUtils.legumes;
                this.question = new Question(this.questions);
                break;

            case MainActivity.COURSES:
                this.questions = CollectionsUtils.courses;
                this.question = new Question(this.questions);
                break;

            case MainActivity.VISAGE:
                this.questions = CollectionsUtils.visage;
                this.question = new Question(this.questions);
                break;

            case MainActivity.VETEMENTS:
                this.questions = CollectionsUtils.vetements;
                this.question = new Question(this.questions);
                break;

            case MainActivity.CORPS:
                this.questions = CollectionsUtils.corps;
                this.question = new Question(this.questions);
                break;

//            case MainActivity.PLAGE:
//                this.questions = CollectionsUtils.plage;
//                this.question = new Question(this.questions);
//                break;

//            case MainActivity.COULEURS:
//                this.questions = CollectionsUtils.couleurs;
//                this.question = new Question(this.questions);
//                break;
        }
    }

    //  Set background red, disable clicks until audio replays, replay audio
    public void wrongAnswer(ImageButton btn){

        audioEffects.playError();
        btn.setBackground(getDrawable(R.drawable.btn_border_red));
        replayQuestion();
    }


    public void rightAnswer(ImageButton btn){
        foundCorrect = true;
        disableClicks();
        audioEffects.playCorrect();
        btn.setBackground(getDrawable(R.drawable.btn_border_green));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                NextQuestion();
            }
        }, 500);
    }

    public void handleAnswer(ImageButton btn){

        if(btn.getDrawable().getConstantState().equals(this.getResources().getDrawable(getResourceId(this, "drawable", answer)).getConstantState())){
            //Toast.makeText(CollectionQuiz.this, "You Are Correct", Toast.LENGTH_SHORT).show();
            //System.out.println(" "+btn_one.getDrawable().);
            rightAnswer(btn);
        }else{
            wrongAnswer(btn);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.audio:
                //Toast.makeText(CollectionQuiz.this, "Play question", Toast.LENGTH_SHORT).show();
                replayQuestion();
                break;

            case R.id.btn_one:

                handleAnswer(btn_one);
                break;

            case R.id.btn_two:

                handleAnswer(btn_two);
                break;

            case R.id.btn_three:

                handleAnswer(btn_three);
                break;

        }
    }

    private void GameEnd(){
        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = null;
        finish();

    }

    public static int getResourceId(Context context, String type, String name) {
        name = name.toLowerCase();
        return context.getResources().getIdentifier(name, type, context.getPackageName());
    }


    private ArrayList<Integer> shuffledQuestions = new ArrayList<Integer>();

    private void shuffleQuestions(){
        for(int i=0; i<this.questions.length; i++){
            shuffledQuestions.add(new Integer(i));
        }
        Collections.shuffle(shuffledQuestions);
    }

    private void enableClicks(){
        // enable all unless they are already chosen (wrong)
        if(!btn_one.getBackground().getConstantState().equals(getDrawable(R.drawable.btn_border_red).getConstantState())){
            //if(!btn_one.getBackground().equals(Color.RED)){
            btn_one.setEnabled(true);
        }
        if(!btn_two.getBackground().getConstantState().equals(getDrawable(R.drawable.btn_border_red).getConstantState())){

            btn_two.setEnabled(true);
        }
        if(!btn_three.getBackground().getConstantState().equals(getDrawable(R.drawable.btn_border_red).getConstantState())){

            btn_three.setEnabled(true);
        }
        audio.setEnabled(true);
//        clicksEnabled = true;
    }
    private void disableClicks(){
        btn_one.setEnabled(false);
        btn_two.setEnabled(false);
        btn_three.setEnabled(false);
        audio.setEnabled(false);
//        clicksEnabled = false;
    }

    private void replayQuestion(){
        if(mediaPlayer == null || !mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    private void playQuestion(){
        while(mediaPlayer !=null && mediaPlayer.isPlaying()){
        }
        // reset mediaplayer before calling create
        if(mediaPlayer!=null){
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaPlayer = MediaPlayer.create(CollectionQuiz.this, getResourceId(this,"raw" , answer));
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(!foundCorrect){
                    enableClicks();
                }

            }
        });
        mediaPlayer.start();
    }

    // Sets quiz for the next question
    private void NextQuestion(){
        foundCorrect = false;
        btn_one.setBackground(getDrawable(R.drawable.btn_border_black));
        btn_two.setBackground(getDrawable(R.drawable.btn_border_black));
        btn_three.setBackground(getDrawable(R.drawable.btn_border_black));
        // this must always follow the background reset
        enableClicks();

        if(num>0){
            circleY[(10-num)].setVisibility(View.VISIBLE);
        }

        if (shuffledQuestions.size()==num || num==max_quest){
            while(mediaPlayer !=null && mediaPlayer.isPlaying()){
            }
            video.playVideo();
            //this.finish();
        }else{
            int nextIndex = shuffledQuestions.get(num);
            num += 1;

            answer = this.question.getCorrectAnswer(nextIndex)[0];
            playQuestion();
            String visualCue = this.question.getCorrectAnswer(nextIndex)[1];
            questionView.setText(visualCue);
            btn_one.setImageResource(getResourceId(this, "drawable", question.getchoice1(nextIndex)));
            btn_two.setImageResource(getResourceId(this, "drawable",question.getchoice2(nextIndex)));
            btn_three.setImageResource(getResourceId(this, "drawable",question.getchoice3(nextIndex)));

        }


    }
}