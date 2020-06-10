package com.codecool.languagetutor.newword;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codecool.languagetutor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewWordActivity extends AppCompatActivity implements NewWordContract {

    @BindView(R.id.btn_add_new_word)
    Button buttonNewWord;
    @BindView(R.id.show_dict)
    TextView showTextView;

    NewWordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newword);
        ButterKnife.bind(this);

        presenter = new NewWordPresenter(this);
        presenter.onAttach(this);

        showTextView.setText(presenter.getAllFrench().toString());



    }



    @Override
    public void successfullySaved() {
        Toast.makeText(this,"Word successfully saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successfullyLoaded() {
        Toast.makeText(this,presenter.getAllFrench().toString(),Toast.LENGTH_LONG).show();
    }
}
