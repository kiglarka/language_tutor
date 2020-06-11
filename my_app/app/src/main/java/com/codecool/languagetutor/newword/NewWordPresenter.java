package com.codecool.languagetutor.newword;

import android.content.Context;
import android.os.AsyncTask;

import com.codecool.languagetutor.db.French;
import com.codecool.languagetutor.db.RoomRepository;

public class NewWordPresenter<V extends NewWordContract> {

    V view;
    RoomRepository roomRepository;

    public NewWordPresenter(Context context) { this.roomRepository = new RoomRepository(context); }

    void onAttach(V view){ this.view = view; }
    void onDetach(){ this.view = null; }

    void saveWord(French french){
        new SaveWordAsyncTask().execute(french);
    }

    private class SaveWordAsyncTask extends AsyncTask<French,Void,Void> {

        @Override
        protected Void doInBackground(French... frenches) {
            roomRepository.saveWord(frenches[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            view.successfullySaved();
        }
    }
}
