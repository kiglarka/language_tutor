package com.codecool.languagetutor.quiz_layout.async_tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.codecool.languagetutor.db.DictRoomDatabase;
import com.codecool.languagetutor.db.French;
import com.codecool.languagetutor.quiz_layout.QuizActivity;

import java.util.List;

public class DatabaseGetter extends AsyncTask<Context,Context, List<French>> {

    Context ct;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<French> doInBackground(Context... contexts) {
        ct = contexts[0];
        DictRoomDatabase database = DictRoomDatabase.getDatabase(ct);
        List<French> list = database.frenchDao().getAlphabetizedTrips();
        return list;
    }

    @Override
    protected void onPostExecute(List<French> frenches) {
        super.onPostExecute(frenches);
        QuizActivity activity = (QuizActivity) ct;
        activity.generateQuestions(frenches);
    }
}
