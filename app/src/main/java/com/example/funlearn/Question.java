package com.example.funlearn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Question {

    public String[] correctAnswer;
    public String[][] choices;
    public static int choiceSize = 3;

    public Question(String[] q){
        this.correctAnswer = q;

        // Construct choices array by randomly picking items from collection
        this.choices = new String[q.length][choiceSize];
        Random rand = new Random();
        for(int i =0; i<q.length; i++){
            ArrayList<String> iChoice = new ArrayList<String>(choiceSize);
            iChoice.add(q[i]);

            while(iChoice.size()<choiceSize){
                int next = rand.nextInt(this.correctAnswer.length);
                if(!iChoice.contains(q[next])){
                    iChoice.add(q[next]);
                }
            }

            Collections.shuffle(iChoice);
            this.choices[i] = iChoice.toArray(this.choices[i]);
        }

    }

//    public String[] questions = {
//            "Where is the apple?",
//            "Where is the banane?",
//            "Where is the cherry?"
//    };

/*    public String[][] choices = {
            {"apple", "banane", "cherry"},
            {"abricot", "cherry", "banane"},
            {"apple", "banane", "cherry"},
            {"apple", "banane", "abricot"}
    };*/

//    public String[] correctAnswer = {
//            "apple",
//            "banane",
//            "cherry",
//            "abricot"
//    };

/*    public String getQuestion(int a){
        return questions[a];
    }*/

    public String getchoice1(int a){
        return choices[a][0];
    }

    public String getchoice2(int a){
        return choices[a][1];
    }

    public String getchoice3(int a){
        return choices[a][2];
    }

    public String getchoice4(int a){
        return choices[a][3];
    }

    public String getCorrectAnswer(int a){
        return correctAnswer[a];
    }
}
