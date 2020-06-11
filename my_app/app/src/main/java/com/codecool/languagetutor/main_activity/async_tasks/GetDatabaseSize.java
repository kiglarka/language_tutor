package com.codecool.languagetutor.main_activity.async_tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.codecool.languagetutor.db.DictRoomDatabase;
import com.codecool.languagetutor.main_activity.MainActivity;

public class GetDatabaseSize extends AsyncTask<Void,Void,Integer> {
    Context context;
    MainActivity activity;

    public GetDatabaseSize(Context ct)
    {
        context = ct;
        activity = (MainActivity)ct;
    }

    @Override
    protected void onPostExecute(Integer integer)
    {
        super.onPostExecute(integer);
        activity.provideQuizActivity(integer);
    }

    @Override
    protected Integer doInBackground(Void... voids)
    {
        DictRoomDatabase database = DictRoomDatabase.getDatabase(context);
        return database.frenchDao().getAlphabetizedTrips().size();
    }
}
