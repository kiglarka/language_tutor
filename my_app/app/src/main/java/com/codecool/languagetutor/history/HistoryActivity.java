package com.codecool.languagetutor.history;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.db.History;

import java.util.List;

public class HistoryActivity extends AppCompatActivity implements HistoryContract {

    HistoryPresenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        presenter = new HistoryPresenter(this);
        presenter.onAttach(this);


    }

    @Override
    public void successfullyLoaded(List<History> histories) {

    }
}