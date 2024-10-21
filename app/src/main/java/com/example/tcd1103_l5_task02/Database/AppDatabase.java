package com.example.tcd1103_l5_task02.Database;

// /database/AppDatabase.java
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tcd1103_l5_task02.Models.Person;
import com.example.tcd1103_l5_task02.dao.PersonDao;

@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
}
