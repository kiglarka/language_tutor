package com.codecool.languagetutor.quiz_layout.async_tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.codecool.languagetutor.db.DictRoomDatabase;
import com.codecool.languagetutor.db.French;
import com.codecool.languagetutor.db.RoomRepository;
import com.codecool.languagetutor.quiz_layout.QuizActivity;

import java.util.List;

public class DatabaseGetter extends AsyncTask<Void,Void, List<French>> {

    Context ct;
    RoomRepository repository;

    public DatabaseGetter(Context ct){
        this.ct = ct;
        repository = new RoomRepository(ct);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<French> doInBackground(Void... voids) {
        List<French> list = repository.getAllWords();
        return list;
    }

    @Override
    protected void onPostExecute(List<French> frenches) {
        super.onPostExecute(frenches);
        QuizActivity activity = (QuizActivity) ct;
        activity.generateQuestions(frenches);
    }
}
