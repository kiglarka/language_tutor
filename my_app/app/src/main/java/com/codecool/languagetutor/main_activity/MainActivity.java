package com.codecool.languagetutor.main_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.codecool.languagetutor.quiz_layout.QuizActivity;
import com.codecool.languagetutor.R;

public class MainActivity extends AppCompatActivity implements MainActivityView{

    MainActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter();
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

}
