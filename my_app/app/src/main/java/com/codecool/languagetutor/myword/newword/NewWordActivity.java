package com.codecool.languagetutor.myword.newword;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.db.French;
import com.codecool.languagetutor.db.RoomRepository;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewWordActivity extends AppCompatActivity implements NewWordContract {

    NewWordPresenter presenter;
    RoomRepository roomRepository;

    @BindView(R.id.btn_save)
    Button saveButton;
    @BindView(R.id.local_word)
    EditText localWordView;
    @BindView(R.id.translation)
    EditText tranlationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        ButterKnife.bind(this);

        presenter = new NewWordPresenter(this);
        presenter.onAttach(this);

        setClickListenerOnSaveButton();
    }

    private void setClickListenerOnSaveButton() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String localWord = localWordView.getText().toString();
                String translation = tranlationView.getText().toString();

                French newFrench = new French(localWord,translation);
                presenter.saveWord(newFrench);

            }
        });
    }


    @Override
    public void successfullySaved() {
        Toast.makeText(this,"Word successfully saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}