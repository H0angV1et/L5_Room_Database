package com.example.tcd1103_l5_task02.dao;

// /dao/PersonDao.java
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tcd1103_l5_task02.Models.Person;

import java.util.List;

@Dao
public interface PersonDao {
    @Insert
    long insertPerson(Person person);

    @Query("SELECT * FROM persons ORDER BY name")
    List<Person> getAllPersons();

    @Delete
    void deletePerson(Person person);
}

