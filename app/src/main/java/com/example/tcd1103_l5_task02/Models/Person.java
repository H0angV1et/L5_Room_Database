package com.example.tcd1103_l5_task02.Models;

// /models/Person.java
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "persons")
public class Person {
    @PrimaryKey(autoGenerate = true)
    public long person_id;
    public String name;
    public String dob;
    public String email;
}
