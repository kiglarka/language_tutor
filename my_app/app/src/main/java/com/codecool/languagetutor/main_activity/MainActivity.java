package com.codecool.languagetutor.main_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.codecool.languagetutor.R;

public class MainActivity extends AppCompatActivity implements MainActivityView{

    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter();
    }

}
