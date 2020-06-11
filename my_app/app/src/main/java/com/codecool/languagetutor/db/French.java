package com.codecool.languagetutor.db;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "french_table")
public class French {

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true) private int id;

    @NonNull private String localWord;
    @NonNull private String translation;

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

    public int getId() { return id; }
}
