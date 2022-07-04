package com.example.funlearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
    public static final String COURSES = "courses";
    public static final String VISAGE = "visage";
    public static final String CORPS = "corps";
    public static final String VETEMENTS = "vetements";
    public static final String PLAGE = "plage";
    public static final String COULEURS = "couleurs";


    ImageButton fruitsBtn, animalsBtn, legumesBtn, coursesBtn, visageBtn, corpsBtn, vetementsBtn,
            plageBtn;
    CardView cvFruits, cvAnimals, cvLegumes, cvCourses, cvVisage, cvCorps, cvVetements, cvPlage;

    private void initiateButton(ImageButton btn, int id){
        btn = (ImageButton)findViewById(id);
        btn.setOnClickListener(this);
    }

    private void initiateCardView(CardView cv, int id){
        cv = (CardView) findViewById(id);
        cv.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiateButton(fruitsBtn, R.id.fruitsBtn);
        initiateButton(animalsBtn, R.id.animalsBtn);
        initiateButton(legumesBtn, R.id.legumesBtn);
        initiateButton(coursesBtn, R.id.coursesBtn);
        initiateButton(visageBtn, R.id.visageBtn);
        initiateButton(vetementsBtn, R.id.vetementsBtn);
        initiateButton(corpsBtn, R.id.corpsBtn);
        initiateButton(plageBtn, R.id.plageBtn);

        initiateCardView(cvFruits, R.id.cvFruits);
        initiateCardView(cvAnimals, R.id.cvAnimals);
        initiateCardView(cvLegumes, R.id.cvLegumes);
        initiateCardView(cvCourses, R.id.cvCourses);
        initiateCardView(cvVisage, R.id.cvVisage);
        initiateCardView(cvVetements, R.id.cvVetements);
        initiateCardView(cvCorps, R.id.cvCorps);
        initiateCardView(cvPlage, R.id.cvPlage);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.cvFruits:
            case R.id.fruitsBtn:
                Intent intent = new Intent(this, CollectionQuiz.class);
                intent.putExtra(EXTRA_MESSAGE, FRUITS);
                startActivity(intent);
                break;

            case R.id.cvAnimals:
            case R.id.animalsBtn:
                intent = new Intent(this, CollectionQuiz.class);
                intent.putExtra(EXTRA_MESSAGE, ANIMALS);
                startActivity(intent);
                break;

            case R.id.legumesBtn:
            case R.id.cvLegumes:
                intent = new Intent(this, CollectionQuiz.class);
                intent.putExtra(EXTRA_MESSAGE, LEGUMES);
                startActivity(intent);
                break;

            case R.id.cvCourses:
            case R.id.coursesBtn:
                intent = new Intent(this, CollectionQuiz.class);
                intent.putExtra(EXTRA_MESSAGE, COURSES);
                startActivity(intent);
                break;

            case R.id.cvVisage:
            case R.id.visageBtn:
                intent = new Intent(this, CollectionQuiz.class);
                intent.putExtra(EXTRA_MESSAGE, VISAGE);
                startActivity(intent);
                break;

            case R.id.cvCorps:
            case R.id.corpsBtn:
                intent = new Intent(this, CollectionQuiz.class);
                intent.putExtra(EXTRA_MESSAGE, CORPS);
                startActivity(intent);
                break;

            case R.id.cvVetements:
            case R.id.vetementsBtn:
                intent = new Intent(this, CollectionQuiz.class);
                intent.putExtra(EXTRA_MESSAGE, VETEMENTS);
                startActivity(intent);
                break;

//            case R.id.cvPlage:
//            case R.id.plageBtn:
//                intent = new Intent(this, CollectionQuiz.class);
//                intent.putExtra(EXTRA_MESSAGE, PLAGE);
//                startActivity(intent);
//                break;

            case R.id.cvCouleurs:
            case R.id.couleursBtn:
                intent = new Intent(this, CollectionQuiz.class);
                intent.putExtra(EXTRA_MESSAGE, COULEURS);
                startActivity(intent);
                break;
        }
    }

}