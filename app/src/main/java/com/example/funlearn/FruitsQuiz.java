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

public class FruitsQuiz extends AppCompatActivity implements View.OnClickListener
{
    ImageButton audio;
    ImageButton btn_one, btn_two, btn_three, btn_four;
    TextView tv_question;

    private Question question = new Question();

    private String answer;
    private int questionLength = question.questions.length;
    private int answersLength = question.choices.length;
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
        btn_four = (ImageButton)findViewById(R.id.btn_four);
        btn_four.setOnClickListener(this);
        audio = (ImageButton)findViewById(R.id.audio);
        audio.setOnClickListener(this);

        //tv_question = (TextView)findViewById(R.id.tv_question);

        shuffleQuestions();
        num = 0;
        NextQuestion();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.audio:
                Toast.makeText(FruitsQuiz.this, "Play question", Toast.LENGTH_SHORT).show();
                playQuestion();
                break;

            case R.id.btn_one:
                if(btn_one.getBackground().getConstantState().equals(this.getResources().getDrawable(getResourceId(this, "drawable", answer)).getConstantState())){
                    Toast.makeText(FruitsQuiz.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                }else{
                    playQuestion();
                }

                break;

            case R.id.btn_two:
                if(btn_two.getBackground().getConstantState().equals(this.getResources().getDrawable(getResourceId(this, "drawable",answer)).getConstantState())){
                    Toast.makeText(FruitsQuiz.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                }else{
                    playQuestion();
                }

                break;

            case R.id.btn_three:
                if(btn_three.getBackground().getConstantState().equals(this.getResources().getDrawable(getResourceId(this, "drawable",answer)).getConstantState())){
                    Toast.makeText(FruitsQuiz.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                }else{
                    playQuestion();
                }

                break;

            case R.id.btn_four:
                if(btn_four.getBackground().getConstantState().equals(this.getResources().getDrawable(getResourceId(this, "drawable",answer)).getConstantState())){
                    Toast.makeText(FruitsQuiz.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                }else{
                    playQuestion();
                }

                break;
        }
    }

    private void GameEnd(){
        //TODO
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

    public static int getResourceId(Context context, String type, String name) {
        name = name.toLowerCase();
        return context.getResources().getIdentifier(name, type, context.getPackageName());
    }


    private ArrayList<Integer> shuffledQuestions = new ArrayList<Integer>();

    private void shuffleQuestions(){
        for(int i=0; i<question.questions.length; i++){

            shuffledQuestions.add(new Integer(i));
        }
        Collections.shuffle(shuffledQuestions);
    }

    private void playQuestion(){

        MediaPlayer music = MediaPlayer.create(FruitsQuiz.this, getResourceId(this,"raw" , answer));
        music.start();
    }

    // Sets quiz for the next question
    private void NextQuestion(){
        if (shuffledQuestions.size()==num){
           // fillRemainingQuestions();
            GameOver();
        }

        int shuffledNum = shuffledQuestions.get(num);
        num += 1;

        answer = question.getCorrectAnswer(shuffledNum);
        //tv_question.setText(question.getQuestion(shuffledNum));
        playQuestion();

        btn_one.setBackground(this.getResources().getDrawable(getResourceId(this, "drawable", question.getchoice1(shuffledNum))));
        btn_two.setBackground(this.getResources().getDrawable(getResourceId(this, "drawable",question.getchoice2(shuffledNum))));
        btn_three.setBackground(this.getResources().getDrawable(getResourceId(this, "drawable",question.getchoice3(shuffledNum))));
        btn_four.setBackground(this.getResources().getDrawable(getResourceId(this, "drawable",question.getchoice4(shuffledNum))));

    }
}