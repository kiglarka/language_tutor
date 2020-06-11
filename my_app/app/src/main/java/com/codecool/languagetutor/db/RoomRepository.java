package com.codecool.languagetutor.db;

import android.content.Context;

import java.util.List;

public class RoomRepository {

    private FrenchDao frenchDao;
    private HistoryDao historyDao;

    public RoomRepository(Context context) {
        this.frenchDao = DictRoomDatabase.getDatabase(context).frenchDao();
        this.historyDao = DictRoomDatabase.getDatabase(context).historyDao();
    }

    public void saveWord(French french) {
        frenchDao.insert(french);
    }

    public void saveHistory(History history) {
        historyDao.insert(history);
    }

    public List<French> getAllWords() {
        return frenchDao.getAlphabetizedWords();
    }

    public List<History> getAllHistory() {
        return historyDao.getAllHistory();
    }

    public List<French> getAllWordsExcept(int id){
        return frenchDao.getWordsExcept(id);
    }

}
