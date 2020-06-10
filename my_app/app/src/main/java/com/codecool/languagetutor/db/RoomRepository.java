package com.codecool.languagetutor.db;

import android.content.Context;

import java.util.List;

public class RoomRepository {

    private FrenchDao frenchDao;


    public RoomRepository(Context context) {
        this.frenchDao = DictRoomDatabase.getDatabase(context).frenchDao();
    }

    public void insert(French french) {
        frenchDao.insert(french);
    }

    public List<French> getAllWords() {
        return frenchDao.getAlphabetizedTrips();
    }

}
