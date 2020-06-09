package com.codecool.languagetutor.db;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity (tableName = "french_table")
public class French {

    @PrimaryKey
    @NonNull
    private UUID id;
    private String localWord;
    private String translation;

    public French(@NonNull UUID id, String localWord, String translation) {
        this.id = UUID.randomUUID();
        this.localWord = localWord;
        this.translation = translation;
    }

    public String getLocalWord() {
        return localWord;
    }

    public String getTranslation() {
        return translation;
    }
}
