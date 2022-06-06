package com.example.funlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MyActivity";
    public static final String EXTRA_MESSAGE = "com.example.funlearn.MESSAGE";

    public static final String FRUITS = "fruits";
    public static final String ANIMALS = "animaux";
    public static final String LEGUMES = "legumes";

    ImageButton fruitsButton, animalsButton, legumesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fruitsButton = (ImageButton)findViewById(R.id.fruitsButton);
        fruitsButton.setOnClickListener(this);
        animalsButton = (ImageButton)findViewById(R.id.animalsButton);
        animalsButton.setOnClickListener(this);
        legumesButton = (ImageButton)findViewById(R.id.legumesButton);
        legumesButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.fruitsButton:
                Intent intent = new Intent(this, CollectionQuiz.class);
                intent.putExtra(EXTRA_MESSAGE, FRUITS);
                startActivity(intent);
                break;

            case R.id.animalsButton:
                intent = new Intent(this, CollectionQuiz.class);
                intent.putExtra(EXTRA_MESSAGE, ANIMALS);
                startActivity(intent);
                break;

            case R.id.legumesButton:
                intent = new Intent(this, CollectionQuiz.class);
                intent.putExtra(EXTRA_MESSAGE, LEGUMES);
                startActivity(intent);
                break;
        }
    }

}