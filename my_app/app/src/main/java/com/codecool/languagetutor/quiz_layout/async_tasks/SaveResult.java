package com.codecool.languagetutor.quiz_layout.async_tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.codecool.languagetutor.db.RoomRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SaveResult extends AsyncTask<Void,Void,Void> {

    Context context;
    int percent;
    RoomRepository repository;
  //  Date date;
    String date;

    public SaveResult(Context ct, int perc){
        percent = perc;
        context = ct;
        repository = new RoomRepository(context);
        date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
    }

    @Override
    protected Void doInBackground(Void... voids) {
        // SAVE TO DB THE
        return null;
    }
}
