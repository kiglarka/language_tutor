package com.codecool.languagetutor.main_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Query;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.history.HistoryActivity;
import com.codecool.languagetutor.main_activity.async_tasks.GetDatabaseSize;
import com.codecool.languagetutor.main_activity.async_tasks.GetDatabaseSize;
import com.codecool.languagetutor.myword.MyWordActivity;
import com.codecool.languagetutor.quiz_layout.QuizActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    @BindView(R.id.btn_list)
    Button buttonMyWords;
    @BindView(R.id.btn_quiz)
    Button startQuizButton;
    @BindView(R.id.btn_history)
    Button historyButton;

    MainActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainActivityPresenter();


        setClickListenerOnMyWordsButton();
        setClickListenerOnStartQuizButton();
        setClickListenerOnHistoryButton();

    }

    @Override
    public void provideQuizActivity(int tableSize){
        if ( tableSize >= 4){
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        }else{
            Toast toast = Toast.makeText(this,"You should have at least 5 words to start quick quiz!",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void setClickListenerOnHistoryButton() {
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }


    private void setClickListenerOnStartQuizButton() {
        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetDatabaseSize(MainActivity.this).execute();
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
