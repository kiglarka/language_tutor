package com.codecool.languagetutor.quiz_layout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.room.Room;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.db.DictRoomDatabase;
import com.codecool.languagetutor.db.French;
import com.codecool.languagetutor.db.FrenchDao;
import com.codecool.languagetutor.quiz_layout.async_tasks.AnswerGetter;
import com.codecool.languagetutor.quiz_layout.async_tasks.DatabaseGetter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuizFragment.QuizInterface, EndSceneFragment.EndSceneInterface {

    QuizFragment quizFragment;
    EndSceneFragment endSceneFragment;
    List<French> words;
    private int countofQuestions;
    private int percentPerQuestion;
    private int currentQuestion;
    private int percent = 0;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quizFragment = new QuizFragment();
        endSceneFragment = new EndSceneFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.quiz_fragment, quizFragment)
                .commit();

        new DatabaseGetter().execute(this);

        progressBar = findViewById(R.id.progress);

    }

    private void loadEndScene(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.quiz_fragment, endSceneFragment)
                .commit();

    }

    public void generateQuestions(List<French> frenchList){
        words = frenchList;
        Collections.shuffle(words);

        if ( words.size() <= 9){
            countofQuestions = words.size();
        }else{
            countofQuestions = 10;
        }

        percentPerQuestion = 100/countofQuestions;

        updateQuestion();
    }

    public void updateQuestion()
    {
        French currentWord = words.get(currentQuestion);
        new AnswerGetter(this).execute(currentWord.getId());
    }

    public void fillAnswers(List<French> differentWords)
    {
        List<French> answers = new ArrayList<>();
        answers.add(words.get(currentQuestion));
        Collections.shuffle(differentWords);

        for ( int i = 0; i < 3; i++){
            answers.add(differentWords.get(i));
        }

        Collections.shuffle(answers);

        quizFragment.updateQuestion(answers, words.get(currentQuestion).getLocalWord());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onRadioSubmit(int id) {

        RadioButton radioButton = (RadioButton)findViewById(id);

        if ( radioButton.getText().equals(words.get(currentQuestion).getTranslation())){
            Toast errorToast = Toast.makeText(this, "Good", Toast.LENGTH_SHORT);
            percent += percentPerQuestion;
            errorToast.show();
        }else{
            Toast errorToast = Toast.makeText(this, "not good", Toast.LENGTH_SHORT);
            errorToast.show();
        }

        currentQuestion += 1;

        if ( currentQuestion >= countofQuestions){
            loadEndScene();
            progressBar.setVisibility(View.INVISIBLE);
        }else{
            updateQuestion();
            progressBar.setProgress((100/countofQuestions)*(currentQuestion+1),true);
        }
    }

    @Override
    public void sceneCreated() {
        endSceneFragment.setPercent(percent);
    }

    @Override
    public void exitToMenu() {
        finish();
    }
}
