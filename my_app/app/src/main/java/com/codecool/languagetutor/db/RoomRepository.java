package com.codecool.languagetutor.db;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RoomRepository implements FrenchRepository {


    DictRoomDatabase db;
    private FrenchDao frenchDao;
    private List<French> allFrench;

    public RoomRepository(Context context){
        db = DictRoomDatabase.getDatabase(context);
    }


    @Override
    public void insert(French french) {
        DictRoomDatabase.databaseWriteExecutor.execute(() -> db.frenchDao().insert(french));
    }

    @Override
    public List<French> getAllFrench() {
        try {
            return new GetFrenchAsyncTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class GetFrenchAsyncTask extends AsyncTask<Void,Void,List<French>>{

        @Override
        protected List<French> doInBackground(Void... voids) {
            return db.frenchDao().getAlphabetizedTrips();
        }
    }

    @Override
    public void clearAllFrench() {
        if (allFrench != null){
            frenchDao.deleteAll();
        }

    }
}
