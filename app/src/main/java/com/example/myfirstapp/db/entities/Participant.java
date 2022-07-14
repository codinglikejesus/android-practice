package com.example.myfirstapp.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Participant {
    @PrimaryKey
    public int id;

    public String name;
}
