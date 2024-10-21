package com.example.tcd1103_l5_task02.Activities;

// /activities/DetailsActivity.java
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tcd1103_l5_task02.ContactAdapter;
import com.example.tcd1103_l5_task02.Database.AppDatabase;
import com.example.tcd1103_l5_task02.Models.Person;
import com.example.tcd1103_l5_task02.R;

import java.util.List;

// DetailsActivity.java
public class DetailsActivity extends AppCompatActivity implements ContactAdapter.OnDeleteClickListener {
    private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    List<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        appDatabase = Room
                .databaseBuilder(getApplicationContext(), AppDatabase.class, "sqlite_example_db")
                .allowMainThreadQueries() // For simplicity, don't use this in production
                .build();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Person> persons = appDatabase.personDao().getAllPersons();

        adapter = new ContactAdapter(persons);
        recyclerView.setAdapter(adapter);

        persons = appDatabase.personDao().getAllPersons();
        adapter = new ContactAdapter(persons, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDeleteClick(Person person) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Contact")
                .setMessage("Are you sure you want to delete this contact?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    // Remove from the database
                    appDatabase.personDao().deletePerson(person);

                    // Update the list
                    persons.remove(person);
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}