package com.codecool.languagetutor.myword;

import android.content.Context;
import android.os.AsyncTask;

import com.codecool.languagetutor.db.French;
import com.codecool.languagetutor.db.RoomRepository;

import java.util.List;

public class MyWordPresenter<V extends MyWordContract> {

    V view;
    RoomRepository roomRepository;

    public MyWordPresenter(Context context) {
        roomRepository = new RoomRepository(context);
    }

    /*
    public List<French> getAllFrench() {
        return allFrench;
    }

     */

    void onAttach(V view){ this.view = view; }
    void onDetach() { this.view = null; }



    void loadAllWords(){
            new ListAllFrenchAsyncTask().execute();
    }


    private class ListAllFrenchAsyncTask extends AsyncTask<Void,Void,List<French>> {

        @Override
        protected List<French> doInBackground(Void... voids) {
            return roomRepository.getAllWords();
        }

        @Override
        protected void onPostExecute(List<French> frenches) {
            super.onPostExecute(frenches);
            view.successfullyLoaded(frenches);
        }
    }


    public class ListAllHistory {
    }
}
