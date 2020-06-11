package com.codecool.languagetutor.quiz_layout.async_tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import androidx.room.Room;

import com.codecool.languagetutor.db.DictRoomDatabase;
import com.codecool.languagetutor.db.French;
import com.codecool.languagetutor.quiz_layout.QuizActivity;

import java.util.List;

public class AnswerGetter extends AsyncTask<Integer, Integer, List<French>> {

    Context context;
    QuizActivity activity;

    public AnswerGetter(Context ct)
    {
        context = ct;
        activity = (QuizActivity)ct;
    }


    @Override
    protected List<French> doInBackground(Integer... integers) {
        int id = integers[0];
        DictRoomDatabase database = DictRoomDatabase.getDatabase(context);
        return database.frenchDao().getWordsExcept(id);
    }

    @Override
    protected void onPostExecute(List<French> frenchList) {
        super.onPostExecute(frenchList);
        activity.fillAnswers(frenchList);
    }
}
