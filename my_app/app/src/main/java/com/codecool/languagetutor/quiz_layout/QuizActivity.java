package com.codecool.languagetutor.quiz_layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.room.Room;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
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

public class QuizActivity extends FragmentActivity {

    RadioGroup radioGroup;
    Button submitButton;

    TextView question;

    private int currentQuestion = 0;
    private int countofQuestions = 0;
    private int percent = 0;
    private int percentPerQuestion;

    private List<French> words = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        radioGroup = findViewById(R.id.radio_group);
        submitButton = findViewById(R.id.submit_btn);
        question = findViewById(R.id.question);

        new DatabaseGetter().execute(this);
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
    
    public void updateQuestion(){
        radioGroup.clearCheck();

        French currentWord = words.get(currentQuestion);
        question.setText(currentWord.getLocalWord());

        new AnswerGetter(this).execute(currentWord.getId());
    }

    public void fillAnswers(List<French> different_words){

        List<French> answers = new ArrayList<>();
        answers.add(words.get(currentQuestion));
        Collections.shuffle(different_words);
        for ( int i = 0; i < 3; i++){
            answers.add(different_words.get(i));
        }

        Collections.shuffle(answers);

        for (int i = 0; i <= 3; i++){
            RadioButton x = (RadioButton)radioGroup.getChildAt(i);
            x.setText(answers.get(i).getTranslation());
        }
    }

    public void onSubmitClick(View view) throws InterruptedException {
        int id = radioGroup.getCheckedRadioButtonId();
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
            hideQuiz();
        }else{
            updateQuestion();
        }
    }

    protected void hideQuiz() throws InterruptedException {
        radioGroup.setVisibility(View.INVISIBLE);
        question.setText( Integer.toString(percent) + " %");
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
