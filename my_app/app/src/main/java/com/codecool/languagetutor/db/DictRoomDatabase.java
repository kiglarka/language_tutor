package com.codecool.languagetutor.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {French.class}, version = 1, exportSchema = false)
public abstract class DictRoomDatabase extends RoomDatabase {

    public abstract FrenchDao frenchDao();


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                FrenchDao dao = INSTANCE.frenchDao();

                /*
                French french = new French("man", "homme");
                dao.insert(french);
                french = new French("woman", "femme");
                dao.insert(french);*/



            });
        }
    };

    private static volatile DictRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static DictRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DictRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DictRoomDatabase.class, "dict_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}


