package com.example.funlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    public static final String EXTRA_MESSAGE = "com.example.funlearn.MESSAGE";

    public static final String FRUITS = "fruits";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton fruitsButton = (ImageButton)findViewById(R.id.fruitsButton);

        fruitsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Lion", Toast.LENGTH_LONG).show();//display the text on image click event
                openFruitsQuiz(view);
            }

        });
    }

    /** Called when the user taps the Fruits button */
    public void openFruitsQuiz(View view) {
        Intent intent = new Intent(this, CollectionQuiz.class);
        //EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_MESSAGE, FRUITS);
        startActivity(intent);
    }



}