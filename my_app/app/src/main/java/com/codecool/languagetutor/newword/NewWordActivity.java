package com.codecool.languagetutor.newword;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.db.DictRoomDatabase;
import com.codecool.languagetutor.db.French;
import com.codecool.languagetutor.db.RoomRepository;

import java.util.List;

public class NewWordActivity extends AppCompatActivity implements NewWordContract {

    private DictRoomDatabase database;
    private RoomRepository roomRepository;
    private List<French> frenchDict;



    NewWordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newword);

        presenter = new NewWordPresenter(this);
        presenter.onAttach(this);



        //getFrench();

    }

    private void getFrench() {
        database = DictRoomDatabase.getDatabase(this);
        roomRepository = new RoomRepository(this);
        frenchDict = roomRepository.getAllFrench();
    }

    @Override
    public void successfullySaved() {

    }

    @Override
    public void successfullyLoaded() {

    }
}
