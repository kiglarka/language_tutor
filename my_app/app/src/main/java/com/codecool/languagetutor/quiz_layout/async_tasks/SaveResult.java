package com.codecool.languagetutor.quiz_layout.async_tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.util.Log;

import com.codecool.languagetutor.db.DictRoomDatabase;
import com.codecool.languagetutor.db.History;
import com.codecool.languagetutor.db.RoomRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SaveResult extends AsyncTask<Void,Void,Void> {

    Context context;
    int percent;
    RoomRepository repository;
  //  Date date;
    String date;
    String incorrectList;

    public SaveResult(Context ct, int perc, String incorrectList){
        percent = perc;
        context = ct;
        this.incorrectList = incorrectList;
        repository = new RoomRepository(context);
        date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
    }

    @Override
    protected Void doInBackground(Void... voids) {
        // SAVE TO DB THE RESULTS
        History history = new History(date,percent,incorrectList);
        repository.saveHistory(history);
        return null;
    }



}
