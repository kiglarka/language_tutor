package com.codecool.languagetutor.db;

import androidx.room.Query;

import java.util.List;

public interface FrenchRepository {
    void insert(French french);
    List<French> getAllFrench();
    void clearAllFrench();

}
