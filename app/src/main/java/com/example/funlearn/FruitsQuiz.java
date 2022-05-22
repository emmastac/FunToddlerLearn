package com.example.funlearn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class FruitsQuiz extends AppCompatActivity implements View.OnClickListener
{
    ImageButton audio;
    ImageButton btn_one, btn_two, btn_three, btn_four;

    private String[] questions = {"pomme", "banane", "cerise", "abricot", "ananas", "citron",
            "fraise", "framboise", "orange", "poire", "pasteque", "raisin"};

    private Question question = new Question(questions);

    private String answer;
    private int questionLength = questions.length;
    private int num;

    //Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits_quiz);

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

    private void disableClicks(){
        audio.setOnClickListener(null);
        btn_one.setOnClickListener(null);
        btn_two.setOnClickListener(null);
        btn_three.setOnClickListener(null);
    }

    private void enableClicks(){
        audio.setOnClickListener(this);
        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        disableClicks();
        switch (v.getId()){

            case R.id.audio:

                Toast.makeText(FruitsQuiz.this, "Play question", Toast.LENGTH_SHORT).show();
                replayQuestion();
                break;

            case R.id.btn_one:
                if(btn_one.getDrawable().getConstantState().equals(this.getResources().getDrawable(getResourceId(this, "drawable", answer)).getConstantState())){
                    Toast.makeText(FruitsQuiz.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                }else{
                    replayQuestion();
                }

                break;

            case R.id.btn_two:
                if(btn_two.getDrawable().getConstantState().equals(this.getResources().getDrawable(getResourceId(this, "drawable",answer)).getConstantState())){
                    Toast.makeText(FruitsQuiz.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                }else{
                    replayQuestion();
                }

                break;

            case R.id.btn_three:
                if(btn_three.getDrawable().getConstantState().equals(this.getResources().getDrawable(getResourceId(this, "drawable",answer)).getConstantState())){
                    Toast.makeText(FruitsQuiz.this, "You Are Correct", Toast.LENGTH_SHORT).show();
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

        enableClicks();
    }

    private void GameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(FruitsQuiz.this);
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
        for(int i=0; i<questions.length; i++){

            shuffledQuestions.add(new Integer(i));
        }
        Collections.shuffle(shuffledQuestions);
    }

    private MediaPlayer music;

    private void replayQuestion(){
        if(music == null || !music.isPlaying()){
            music = MediaPlayer.create(FruitsQuiz.this, getResourceId(this,"raw" , answer));
            music.start();
        }else{
            //TODO:
        }
    }

    private void playQuestion(){
        while(music!=null && music.isPlaying()){
        }
        music = MediaPlayer.create(FruitsQuiz.this, getResourceId(this,"raw" , answer));
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

        answer = question.getCorrectAnswer(shuffledNum);
        //tv_question.setText(question.getQuestion(shuffledNum));
        playQuestion();

        btn_one.setImageResource(getResourceId(this, "drawable", question.getchoice1(shuffledNum)));
        btn_two.setImageResource(getResourceId(this, "drawable",question.getchoice2(shuffledNum)));
        btn_three.setImageResource(getResourceId(this, "drawable",question.getchoice3(shuffledNum)));
        //btn_four.setImageResource(getResourceId(this, "drawable",question.getchoice4(shuffledNum)));

    }
}