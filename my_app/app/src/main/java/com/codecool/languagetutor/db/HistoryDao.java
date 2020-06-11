package com.codecool.languagetutor.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)

    void insert(History history);

    @Query("SELECT * from history_table ORDER BY date DESC")
    List<History> getAllHistory();
}
