package com.example.funlearn;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class CollectionQuiz extends AppCompatActivity implements View.OnClickListener
{
    ImageButton audio;
    ImageButton btn_one, btn_two, btn_three, btn_four;

    private String[] questions;

    private Question question;

    private String answer;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_quiz);

        Bundle extras = getIntent().getExtras();
        String collection = extras.getString(MainActivity.EXTRA_MESSAGE);
        loadCollection(collection);
        //random = new Random();

        btn_one = (ImageButton)findViewById(R.id.btn_one);
        btn_one.setOnClickListener(this);
        btn_two = (ImageButton)findViewById(R.id.btn_two);
        btn_two.setOnClickListener(this);
        btn_three = (ImageButton)findViewById(R.id.btn_three);
        btn_three.setOnClickListener(this);
        //btn_four = (ImageButton)findViewById(R.id.btn_four);
        //btn_four.setOnClickListener(this);
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
                if(btn_one.getDrawable().getConstantState().equals(this.getResources().getDrawable(getResourceId(this, "drawable", answer)).getConstantState())){
                    //Toast.makeText(CollectionQuiz.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                }else{
                    replayQuestion();
                }
                break;

            case R.id.btn_two:
                if(btn_two.getDrawable().getConstantState().equals(this.getResources().getDrawable(getResourceId(this, "drawable",answer)).getConstantState())){
                    //Toast.makeText(CollectionQuiz.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                }else{
                    replayQuestion();
                }
                break;

            case R.id.btn_three:
                if(btn_three.getDrawable().getConstantState().equals(this.getResources().getDrawable(getResourceId(this, "drawable",answer)).getConstantState())){
                    //Toast.makeText(CollectionQuiz.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                }else{
                    replayQuestion();
                }
                break;

           /* case R.id.btn_four:
                if(btn_four.getDrawable().getConstantState().equals(this.getResources().getDrawable(getResourceId(this, "drawable",answer)).getConstantState())){
                    Toast.makeText(FruitsQuiz.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                }else{
                    replayQuestion();
                }

                break;*/
        }
    }

    private void GameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CollectionQuiz.this);
        alertDialogBuilder
                .setMessage("Game Over")
                .setCancelable(false)
                .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
        alertDialogBuilder.show();

    }

    private void GameEnd(){
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

    private MediaPlayer music;

    private void replayQuestion(){
        if(music == null || !music.isPlaying()){
            music = MediaPlayer.create(CollectionQuiz.this, getResourceId(this,"raw" , answer));
            music.start();
        }
    }

    private void playQuestion(){
        while(music!=null && music.isPlaying()){
        }
        music = MediaPlayer.create(CollectionQuiz.this, getResourceId(this,"raw" , answer));
        music.start();

    }

    // Sets quiz for the next question
    private void NextQuestion(){
        if (shuffledQuestions.size()==num){
           // fillRemainingQuestions();
            GameEnd();
        }

        int shuffledNum = shuffledQuestions.get(num);
        num += 1;

        answer = this.question.getCorrectAnswer(shuffledNum);
        //tv_question.setText(question.getQuestion(shuffledNum));
        playQuestion();

        btn_one.setImageResource(getResourceId(this, "drawable", question.getchoice1(shuffledNum)));
        btn_two.setImageResource(getResourceId(this, "drawable",question.getchoice2(shuffledNum)));
        btn_three.setImageResource(getResourceId(this, "drawable",question.getchoice3(shuffledNum)));
        //btn_four.setImageResource(getResourceId(this, "drawable",question.getchoice4(shuffledNum)));

    }
}