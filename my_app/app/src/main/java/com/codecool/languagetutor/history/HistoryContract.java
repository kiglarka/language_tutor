package com.codecool.languagetutor.history;

import com.codecool.languagetutor.db.History;

import java.util.List;

public interface HistoryContract {

    void successfullyLoaded(List<History> histories);

}
