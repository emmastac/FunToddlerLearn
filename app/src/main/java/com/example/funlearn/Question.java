package com.example.funlearn;

public class Question {

    public String[] questions = {
            "Where is the apple?",
            "Where is the banane?",
            "Where is the cherry?"
    };

    public String[][] choices = {
            {"apple", "banane", "cherry", "fruits"},
            {"apple", "banane", "cherry", "fruits"},
            {"apple", "banane", "cherry", "fruits"}
    };

    public String[] correctAnswer = {
            "apple",
            "banane",
            "cherry"
    };

    public String getQuestion(int a){
        return questions[a];
    }

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
