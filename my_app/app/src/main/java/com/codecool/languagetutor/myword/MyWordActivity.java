package com.codecool.languagetutor.myword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.db.French;
import com.codecool.languagetutor.newword.NewWordActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWordActivity extends AppCompatActivity implements MyWordContract {

    @BindView(R.id.btn_add_new_word)
    Button buttonNewWord;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    MyWordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myword);
        ButterKnife.bind(this);

        presenter = new MyWordPresenter(this);
        presenter.onAttach(this);

        //presenter.loadAllWords();

        //showTextView.setText(presenter.listAllWords().toString());

        setClickListenerOnNewWordButton();

    }

    private void setClickListenerOnNewWordButton() {
        buttonNewWord.setOnClickListener(v -> {
            Intent intent = new Intent(MyWordActivity.this, NewWordActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadAllWords();

    }

    @Override
    public void successfullyLoaded(List<French> frenches) {
       // showTextView.setText(frenches.toString());
        MyWordAdapter adapter = new MyWordAdapter(this, frenches);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
