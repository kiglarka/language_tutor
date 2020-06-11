package com.codecool.languagetutor.db;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "history_table")
public class History {

    @PrimaryKey(autoGenerate = true)
    private int id;


    @NonNull
    private String date;
    private int percentage;
    private String incorrectGuesses;

    public History(@NonNull String date, int percentage, String incorrectGuesses) {
        this.date = date;
        this.percentage = percentage;
        this.incorrectGuesses = incorrectGuesses;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public int getPercentage() {
        return percentage;
    }

    public String getIncorrectGuesses() {
        return incorrectGuesses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
