package com.codecool.languagetutor.quiz_layout.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codecool.languagetutor.R;

public class WrongAnswerFragment extends Fragment
{

    WrongAnswerInterface wrongAnswerInterface;
    TextView answer;
    Button okButton;

    public interface WrongAnswerInterface{
        void closeThisFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wrong_answer_fragment,container,false);
        answer = view.findViewById(R.id.the_good_answer);
        okButton = view.findViewById(R.id.ok_button);

        okButton.setOnClickListener(v -> {
            getView().setVisibility(View.INVISIBLE);
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        wrongAnswerInterface = (WrongAnswerInterface)context;
    }

    public void setAnswer(String answer) {
        getView().setVisibility(View.VISIBLE);
        this.answer.setText(answer);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getView().setVisibility(View.INVISIBLE);
    }
}
