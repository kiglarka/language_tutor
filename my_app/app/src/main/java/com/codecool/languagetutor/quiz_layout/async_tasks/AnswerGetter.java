package com.codecool.languagetutor.quiz_layout.async_tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import androidx.room.Room;

import com.codecool.languagetutor.db.DictRoomDatabase;
import com.codecool.languagetutor.db.French;
import com.codecool.languagetutor.db.RoomRepository;
import com.codecool.languagetutor.quiz_layout.QuizActivity;

import java.util.List;

public class AnswerGetter extends AsyncTask<Integer, Integer, List<French>> {

    Context context;
    QuizActivity activity;
    RoomRepository repository;

    public AnswerGetter(Context ct)
    {
        context = ct;
        activity = (QuizActivity)ct;
        repository = new RoomRepository(ct);
    }
    
    @Override
    protected List<French> doInBackground(Integer... integers) {
        int id = integers[0];
        return repository.getAllWordsExcept(id);
    }

    @Override
    protected void onPostExecute(List<French> frenchList) {
        super.onPostExecute(frenchList);
        activity.fillAnswers(frenchList);
    }
}
