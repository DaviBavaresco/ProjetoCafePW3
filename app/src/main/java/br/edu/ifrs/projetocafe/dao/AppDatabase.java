package br.edu.ifrs.projetocafe.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.edu.ifrs.projetocafe.entity.Cafe;

@Database(entities = {Cafe.class}, version = 1,exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;

    public abstract CafeDAO createCafeDAO();
    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"driver_database").allowMainThreadQueries().build();
        }
        return appDatabase;
    }
}
