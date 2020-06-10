package com.codecool.languagetutor.myword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.myword.newword.NewWordActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWordActivity extends AppCompatActivity implements MyWordContract {

    @BindView(R.id.btn_add_new_word)
    Button buttonNewWord;
    @BindView(R.id.show_dict)
    TextView showTextView;

    MyWordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myword);
        ButterKnife.bind(this);

        presenter = new MyWordPresenter(this);
        presenter.onAttach(this);

        showTextView.setText(presenter.getAllFrench().toString());

        setClickListenerOnNewWordButton();

    }

    private void setClickListenerOnNewWordButton() {
        buttonNewWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyWordActivity.this, NewWordActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showTextView.setText(presenter.getAllFrench().toString());


    }


    @Override
    public void successfullyLoaded() {
        Toast.makeText(this,presenter.getAllFrench().toString(),Toast.LENGTH_LONG).show();
    }
}
