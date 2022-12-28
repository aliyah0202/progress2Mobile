package com.example.project.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.project.database.dao.DatabaseDao;
import com.example.project.model.ModelDatabase;

@Database(entities = {ModelDatabase.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DatabaseDao databaseDao();
}

