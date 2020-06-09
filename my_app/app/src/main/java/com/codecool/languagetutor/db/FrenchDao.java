package com.codecool.languagetutor.db;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

public interface FrenchDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(French french);

    @Query("DELETE FROM french_table")
    void deleteAll();

    @Query("SELECT * from french_table ORDER BY localWord ASC")
    List<French> getAlphabetizedTrips();
}
