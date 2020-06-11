package com.codecool.languagetutor.quiz_layout;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codecool.languagetutor.R;

public class EndSceneFragment extends Fragment {

    TextView percentView;
    Button submitButton;
    EndSceneInterface endSceneInterface;

    public interface EndSceneInterface{
        public void sceneCreated();
        public void exitToMenu();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.quiz_end_scene,container,false);
        percentView = v.findViewById(R.id.percent_txt);
        submitButton = v.findViewById(R.id.final_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endSceneInterface.exitToMenu();
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        endSceneInterface = (EndSceneInterface)context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        endSceneInterface.sceneCreated();
    }

    public void setPercent(int percent){
        String percent_text = percent + " %";
        percentView.setText(percent_text);
    }
}
