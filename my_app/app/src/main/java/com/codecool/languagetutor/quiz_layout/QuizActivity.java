package com.codecool.languagetutor.quiz_layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codecool.languagetutor.R;

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

    private List<ExampleWordClass> words = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        radioGroup = findViewById(R.id.radio_group);
        submitButton = findViewById(R.id.submit_btn);
        question = findViewById(R.id.question);
        generateQuestions();
        Collections.shuffle(words);

        if ( words.size() <= 9){
            countofQuestions = words.size();
        }else {
            countofQuestions = 10;
        }

        percentPerQuestion = 100/countofQuestions;

        updateQuestion();

    }

    protected void generateQuestions(){
        words.add(new ExampleWordClass("pénisz","penis"));
        words.add(new ExampleWordClass("kutya", "doggo"));
        words.add(new ExampleWordClass("cica","not doggo"));
        words.add(new ExampleWordClass("nyúl","not doggo and not not doggo but actually not doggo"));
    }

    public void onSubmitClick(View view) throws InterruptedException {
        int id = radioGroup.getCheckedRadioButtonId();
        if (id == -1){
            Toast errorToast = Toast.makeText(this, "Pick an answer!", Toast.LENGTH_SHORT);
            errorToast.show();
        }

        onSubmit();
    }

    protected void updateQuestion(){
        radioGroup.clearCheck();
        question.setText(words.get(currentQuestion).getName());

        List<String> answers = new ArrayList<>();
        answers.add(words.get(currentQuestion).getMeaning());
        List<ExampleWordClass> list = new ArrayList<>(words);
        Collections.shuffle(list);
        for ( int k = 0; k < list.size(); k++){
            if ( !list.get(k).getMeaning().equals(words.get(currentQuestion).getMeaning())){
                answers.add(list.get(k).getMeaning());
            }
            if ( answers.size() > 3 ){
                break;
            }
        }

        Collections.shuffle(answers);
        for (int i = 0; i <= 3; i++){
            RadioButton x = (RadioButton)radioGroup.getChildAt(i);
            x.setText(answers.get(i));
        }
    }

    public void onSubmit() throws InterruptedException {

        if ( currentQuestion >= words.size()){
            hideQuiz();
            return;
        }


        int id = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(id);

        if ( radioButton.getText().equals(words.get(currentQuestion).getMeaning())){
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
