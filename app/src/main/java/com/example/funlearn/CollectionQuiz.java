package com.example.funlearn;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

public class CollectionQuiz extends AppCompatActivity implements View.OnClickListener
{
    ImageButton audio;
    ImageButton btn_one, btn_two, btn_three;
    ImageView[] circleY = new ImageView[10];

    private boolean foundCorrect = false;
    private String[] questions;
    private boolean clicksEnabled = true;

    private Question question;

    private String answer;
    private int num;

    public static int max_quest = 10;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_quiz);

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

        //tv_question = (TextView)findViewById(R.id.tv_question);

        shuffleQuestions();
        num = 0;
        NextQuestion();
    }

    public void loadCollection(String collectionName){
        switch (collectionName){

            case MainActivity.FRUITS:
                this.questions = new String[]{"pomme", "banane", "cerise", "abricot", "ananas", "citron",
                        "fraise", "framboise", "orange", "poire", "pasteque", "raisin"};
                this.question = new Question(this.questions);
                break;

            case MainActivity.ANIMALS:
                this.questions = new String[]{"serpent", "elephant", "chevre", "poisson", "chameau",
                        "grenouille", "girafe", "ours", "herisson", "hibou", "cerf", "loup",
                        "oiseau", "kangourou", "ecureuil", "renard", "cheval", "poule", "coq",
                        "canard", "cochon", "mouton", "vache", "perroquet", "souris", "tortue",
                        "lapin", "chat", "chien"};
                this.question = new Question(this.questions);
                break;

            case MainActivity.LEGUMES:
                this.questions = new String[]{"betterave", "champignons", "haricots", "poivron",
                        "oignon", "carotte", "courgette", "aubergine", "concombre", "pommedeterre",
                        "tomate", "epinards", "brocoli", "mais"};
                this.question = new Question(this.questions);
                break;

        }
    }

    @Override
    protected void onStop (){
        super.onStop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    //  Set background red, disable clicks until audio replays, replay audio
    public void wrongAnswer(ImageButton btn){

        btn.setBackgroundColor(Color.RED);
        disableClicks();
        replayQuestion();
    }


    public void rightAnswer(ImageButton btn){
        foundCorrect = true;
        btn.setBackgroundColor(Color.GREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                NextQuestion();
            }
        }, 1500);
    }

    public void handleAnswer(ImageButton btn){

        btn.setEnabled(false);
        if(mediaPlayer.isPlaying()){
            disableClicks();
        }
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
        //System.out.print(clicksEnabled);
//        if(!clicksEnabled || foundCorrect){
//            return;
//        }
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
        if(!btn_one.getBackground().equals(Color.RED)){
            btn_one.setEnabled(true);
        }
        if(!btn_two.getBackground().equals(Color.RED)){
            btn_two.setEnabled(true);
        }
        if(!btn_three.getBackground().equals(Color.RED)){
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
        btn_one.setBackgroundColor(Color.GRAY);
        btn_two.setBackgroundColor(Color.GRAY);
        btn_three.setBackgroundColor(Color.GRAY);
        enableClicks();

        if(num>0){
            circleY[(10-num)].setVisibility(View.VISIBLE);
        }

        if (shuffledQuestions.size()==num || num==max_quest){
            this.finish();
        }else{
            int nextIndex = shuffledQuestions.get(num);
            num += 1;

            answer = this.question.getCorrectAnswer(nextIndex);
            playQuestion();

            btn_one.setImageResource(getResourceId(this, "drawable", question.getchoice1(nextIndex)));
            btn_two.setImageResource(getResourceId(this, "drawable",question.getchoice2(nextIndex)));
            btn_three.setImageResource(getResourceId(this, "drawable",question.getchoice3(nextIndex)));

        }


    }
}