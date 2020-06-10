package com.codecool.languagetutor.quiz_layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.codecool.languagetutor.R;

public class QuizActivity extends AppCompatActivity {

    public static String ENGLISH = "eng";
    public static String FRANCE = "fr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    public void setStatus(String lang){
        if ( lang.equals(ENGLISH)){

        }
    }
}
