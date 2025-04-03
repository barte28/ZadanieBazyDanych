package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Zawodnik.class}, version = 1, exportSchema = false)
public abstract class DataBaseZawodnicy extends RoomDatabase {
    public abstract DaoZawodnicy getDaoZawodnicy();
}
