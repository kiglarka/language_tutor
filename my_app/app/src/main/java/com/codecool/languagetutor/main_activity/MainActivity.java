package com.codecool.languagetutor.main_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.myword.MyWordActivity;
import com.codecool.languagetutor.quiz_layout.QuizActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    @BindView(R.id.btn_list)
    Button buttonMyWords;
    @BindView(R.id.btn_quiz)
    Button startQuizButton;

    MainActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainActivityPresenter();


        setClickListenerOnMyWordsButton();
        setClickListenerOnStartQuizButton();

    }

    private void setClickListenerOnStartQuizButton() {
        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setClickListenerOnMyWordsButton() {
        buttonMyWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyWordActivity.class);
                startActivity(intent);
            }
        });
    }



}
