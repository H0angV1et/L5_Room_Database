package com.example.tcd1103_l5_task02;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcd1103_l5_task02.Models.Person;

import java.util.List;

// ContactAdapter.java
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Person> persons;
    private OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(Person person);
    }

    public ContactAdapter(List<Person> persons) {
        this.persons = persons;
    }

    public ContactAdapter(List<Person> persons, OnDeleteClickListener onDeleteClickListener) {
        this.persons = persons;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(com.example.tcd1103_l5_task02.R.layout.item_contact_card, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Person person = persons.get(position);
        holder.personName.setText(person.name);
        holder.personDetails.setText(person.person_id + " " + person.dob + " " + person.email);

        holder.itemView.setOnClickListener(v -> {
            if (onDeleteClickListener != null) {
                onDeleteClickListener.onDeleteClick(persons.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView personName, personDetails;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            personName = itemView.findViewById(R.id.personName);
            personDetails = itemView.findViewById(R.id.personDetails);
        }
    }
}
