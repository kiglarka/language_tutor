package com.codecool.languagetutor.quiz_layout.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.db.French;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizFragment extends Fragment
{

    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.submit_btn)
    Button submitButton;
    @BindView(R.id.question)
    TextView question;

    QuizInterface quizInterface;

    private Context context;

    public interface QuizInterface {
        void onRadioSubmit(int id);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_fragment, container,false);
        ButterKnife.bind(this,container);
        boolean quizCompleted = false;


        submitButton.setOnClickListener(v -> {
            if ( !quizCompleted )
            {
                int id = radioGroup.getCheckedRadioButtonId();
                if (id >= 0) {
                    quizInterface.onRadioSubmit(id);
                } else {
                    Toast errorMSG = Toast.makeText(context, "Choose an answer!", Toast.LENGTH_SHORT);
                    errorMSG.show();
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        if ( context instanceof QuizInterface){
            quizInterface = (QuizInterface)context;
        }else{
            throw new RuntimeException(context.toString()
            + " must implement 'QuizInterface' interface!");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
        quizInterface = null;
    }

    public void updateQuestion(List<French> answers, String question){
        this.question.setText(question);
        radioGroup.clearCheck();
        for (int i = 0; i < answers.size(); i++){
            RadioButton radioButton = (RadioButton)radioGroup.getChildAt(i);
            radioButton.setText(answers.get(i).getTranslation());
        }
    }

    public void hide(){
        radioGroup.setVisibility(View.INVISIBLE);
        question.setText("QUIZ COMPLETED!");
    }
}
