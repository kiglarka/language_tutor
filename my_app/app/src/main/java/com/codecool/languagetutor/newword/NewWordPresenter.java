package com.codecool.languagetutor.newword;

import android.content.Context;
import android.os.AsyncTask;

import com.codecool.languagetutor.db.French;
import com.codecool.languagetutor.db.RoomRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class NewWordPresenter<V extends NewWordContract> {

    V view;
    RoomRepository roomRepository;

    public NewWordPresenter(Context context) {
        roomRepository = new RoomRepository(context);
    }

    void onAttach(V view){ this.view = view; }
    void onDetach() { this.view = null; }

    void saveWord(French french){
        new SaveWordAsyncTask().execute(french);
    }

    List<French> listAllWords(){
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
    }

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
