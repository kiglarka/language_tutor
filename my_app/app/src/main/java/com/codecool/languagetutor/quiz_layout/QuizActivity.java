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
import com.codecool.languagetutor.quiz_layout.async_tasks.SaveResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuizFragment.QuizInterface, EndSceneFragment.EndSceneInterface {

    QuizFragment quizFragment;
    EndSceneFragment endSceneFragment;
    List<French> words;
    private float countofQuestions;
    private float percentPerQuestion;
    private float currentQuestion;
    private float percent = 0;
    private int goodSolutions = 0;

    TextView progressText;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        new SaveResult(this,0);

        quizFragment = new QuizFragment();
        endSceneFragment = new EndSceneFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.quiz_fragment, quizFragment)
                .commit();

        new DatabaseGetter().execute(this);

        progressText = findViewById(R.id.test);
        progressBar = findViewById(R.id.progress);


    }

    private void loadEndScene(){

        progressText.setVisibility(View.INVISIBLE);

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
        updateProgressText();
    }

    public void updateQuestion()
    {
        French currentWord = words.get((int) currentQuestion);
        new AnswerGetter(this).execute(currentWord.getId());
    }

    public void fillAnswers(List<French> differentWords)
    {
        List<French> answers = new ArrayList<>();
        answers.add(words.get((int) currentQuestion));
        Collections.shuffle(differentWords);

        for ( int i = 0; i < 3; i++){
            answers.add(differentWords.get(i));
        }

        Collections.shuffle(answers);

        quizFragment.updateQuestion(answers, words.get((int) currentQuestion).getLocalWord());
    }

    void updateProgressText(){
        String progressText_ = ((int)currentQuestion + 1) + "/" + (int)countofQuestions;
        progressText.setText(progressText_);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onRadioSubmit(int id) {

        RadioButton radioButton = (RadioButton)findViewById(id);

        if ( radioButton.getText().equals(words.get((int) currentQuestion).getTranslation())){
            Toast errorToast = Toast.makeText(this, "Good", Toast.LENGTH_SHORT);
            goodSolutions += 1;
            percent += percentPerQuestion;
            errorToast.show();
        }else{
            Toast errorToast = Toast.makeText(this, "not good", Toast.LENGTH_SHORT);
            errorToast.show();
        }



        if ( currentQuestion + 1 >= countofQuestions){
            loadEndScene();
            progressBar.setVisibility(View.INVISIBLE);
        }else{
            currentQuestion += 1;
            updateProgressText();
            updateQuestion();
            float progressPercent = (100/(float)countofQuestions)*(currentQuestion+1);
            progressBar.setProgress((int)progressPercent,true);
        }
    }

    @Override
    public void sceneCreated() {
        endSceneFragment.setPercent(percent);
    }

    @Override
    public void exitToMenu() {
        new SaveResult(getApplicationContext(),(int)percent).execute();
        finish();
    }
}
