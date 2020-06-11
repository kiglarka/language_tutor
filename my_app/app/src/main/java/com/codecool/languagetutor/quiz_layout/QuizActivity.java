package com.codecool.languagetutor.quiz_layout;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.db.French;
import com.codecool.languagetutor.quiz_layout.async_tasks.AnswerGetter;
import com.codecool.languagetutor.quiz_layout.async_tasks.DatabaseGetter;
import com.codecool.languagetutor.quiz_layout.async_tasks.SaveResult;
import com.codecool.languagetutor.quiz_layout.fragments.EndSceneFragment;
import com.codecool.languagetutor.quiz_layout.fragments.QuantityChangerFragment;
import com.codecool.languagetutor.quiz_layout.fragments.QuizFragment;
import com.codecool.languagetutor.quiz_layout.fragments.WrongAnswerFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizActivity extends AppCompatActivity implements QuizFragment.QuizInterface, EndSceneFragment.EndSceneInterface, WrongAnswerFragment.WrongAnswerInterface, QuantityChangerFragment.QuantityChangerInterface {

    QuizFragment quizFragment;
    EndSceneFragment endSceneFragment;
    WrongAnswerFragment wrongAnswerFragment;
    QuantityChangerFragment quantityChangerFragment;

    List<French> words;
    private float countofQuestions;
    private float percentPerQuestion;
    private float currentQuestion;
    private float percent = 0;
    private int goodSolutions = 0;

    @BindView(R.id.test)
    TextView progressText;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);

        new SaveResult(this,0);

        quizFragment = new QuizFragment();
        endSceneFragment = new EndSceneFragment();
        wrongAnswerFragment = new WrongAnswerFragment();
        quantityChangerFragment = new QuantityChangerFragment();

        getSupportFragmentManager().beginTransaction()
               // .replace(R.id.quiz_fragment, quizFragment)
                .replace(R.id.wrong_answer_fragment,wrongAnswerFragment)
                .replace(R.id.quantity_fragment,quantityChangerFragment)
                .commit();

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

        if ( words.size() <= countofQuestions - 1){
            countofQuestions = words.size();
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
            wrongAnswerFragment.setMenuVisibility(true);
            wrongAnswerFragment.setAnswer(words.get((int) currentQuestion).getTranslation());
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

    @Override
    public void closeThisFragment() {
        wrongAnswerFragment.setMenuVisibility(false);
    }

    @Override
    public void setQuantity(int quantity) {
        countofQuestions = quantity;
        new DatabaseGetter(this).execute();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.quiz_fragment,quizFragment)
                .remove(getSupportFragmentManager().findFragmentById(R.id.quantity_fragment))
                .commit();
    }
}
