package com.codecool.languagetutor.myword;

import android.content.Context;
import android.os.AsyncTask;

import com.codecool.languagetutor.db.French;
import com.codecool.languagetutor.db.RoomRepository;

import java.util.List;

public class MyWordPresenter<V extends MyWordContract> {

    V view;
    RoomRepository roomRepository;
    private List<French> allFrench;

    public MyWordPresenter(Context context) {
        roomRepository = new RoomRepository(context);
        // load DB
        this.allFrench = roomRepository.getAllFrench();
    }

    public List<French> getAllFrench() {
        return allFrench;
    }

    void onAttach(V view){ this.view = view; }
    void onDetach() { this.view = null; }

    void saveWord(French french){
        new SaveWordAsyncTask().execute(french);
    }

    /*
    public List<French> listAllWords(){
        try {
            return new ListAllFrenchAsyncTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    private class ListAllFrenchAsyncTask extends AsyncTask<Void,Void,List<French>>{

        @Override
        protected List<French> doInBackground(Void... voids) {
            return roomRepository.getAllFrench();
        }

        @Override
        protected void onPostExecute(List<French> frenches) {
            super.onPostExecute(frenches);
            view.successfullyLoaded();
        }
    }

     */

    private class SaveWordAsyncTask extends AsyncTask<French,Void,Void> {

        @Override
        protected Void doInBackground(French... frenches) {
            roomRepository.insert(frenches[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            view.successfullySaved();
        }
    }
}
