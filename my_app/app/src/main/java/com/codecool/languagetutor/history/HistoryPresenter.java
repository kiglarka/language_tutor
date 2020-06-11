package com.codecool.languagetutor.history;

import android.content.Context;
import android.os.AsyncTask;

import com.codecool.languagetutor.db.History;
import com.codecool.languagetutor.db.RoomRepository;

import java.util.List;

public class HistoryPresenter<V extends HistoryContract> {

    V view;
    RoomRepository roomRepository;

    public HistoryPresenter(Context context) {
        roomRepository = new RoomRepository(context);
    }

    void onAttach(V view){ this.view = view; }
    void onDetach() { this.view = null; }



    void loadAllHistory(){
        new ListAllHistory().execute();
    }


    private class ListAllHistory extends AsyncTask<Void,Void, List<History>> {

        @Override
        protected List<History> doInBackground(Void... voids) {
            return roomRepository.getAllHistory();
        }

        @Override
        protected void onPostExecute(List<History> histories) {
            super.onPostExecute(histories);
            view.successfullyLoaded(histories);
        }
    }

}
