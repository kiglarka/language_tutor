package com.codecool.languagetutor.db;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "french_table")
public class French {

    @PrimaryKey
    @NonNull
    private String localWord;
    private String translation;

    public French(@NonNull String localWord, String translation) {
        this.localWord = localWord;
        this.translation = translation;
    }

    @NonNull
    public String getLocalWord() {
        return localWord;
    }

    public String getTranslation() {
        return translation;
    }
}
