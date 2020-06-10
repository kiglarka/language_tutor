package com.codecool.languagetutor.quiz_layout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codecool.languagetutor.R;

public class QuizFrame extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View customView = inflater.inflate(R.layout.language_chooser_fragment, container,false);
        customView.findViewById(R.id.btn_eng).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizActivity activity = (QuizActivity)QuizFrame.this.getActivity();
                activity.setStatus(QuizActivity.ENGLISH);
            }
        });
        return customView;
    }


}
