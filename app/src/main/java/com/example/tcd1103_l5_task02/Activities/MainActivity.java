package com.example.tcd1103_l5_task02.Activities;

// /activities/MainActivity.java
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tcd1103_l5_task02.Database.AppDatabase;
import com.example.tcd1103_l5_task02.Models.Person;
import com.example.tcd1103_l5_task02.R;

public class MainActivity extends AppCompatActivity {
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatabase = Room
                .databaseBuilder(getApplicationContext(), AppDatabase.class, "sqlite_example_db")
                .allowMainThreadQueries() // For simplicity, don't use this in production
                .build();

        Button saveDetailsButton = findViewById(R.id.saveDetailsButton);

        saveDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
            }
        });
    }

    private void saveDetails() {
        EditText nameTxt = findViewById(R.id.nameText);
        EditText dobTxt = findViewById(R.id.dobText);
        EditText emailTxt = findViewById(R.id.emailText);

        String name = nameTxt.getText().toString();
        String dob = dobTxt.getText().toString();
        String email = emailTxt.getText().toString();

        Person person = new Person();
        person.name = name;
        person.dob = dob;
        person.email = email;

        long personId = appDatabase.personDao().insertPerson(person);

        Toast.makeText(this, "Person has been created with id: " + personId,
                Toast.LENGTH_LONG
        ).show();


        // Launch Details Activity
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }
}