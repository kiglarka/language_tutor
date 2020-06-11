package com.codecool.quiz_sample;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codecool.languagetutor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentB extends Fragment {

    private FragmentBListener listener;

    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.button_ok)
    Button button;

    public interface FragmentBListener {
        void onInputBSent(CharSequence input);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_b,container,false);
        ButterKnife.bind(this,container);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence input = editText.getText();
                listener.onInputBSent(input);
            }
        });
        return v;

    }

    public void updateEditText(CharSequence newText) {
        editText.setText(newText);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentBListener) {
            listener = ((FragmentBListener) context);
        } else {
            throw new RuntimeException(context.toString()
            + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
