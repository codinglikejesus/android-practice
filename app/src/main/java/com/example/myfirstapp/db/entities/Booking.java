package com.example.myfirstapp.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Booking {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private long timestampStart;
    private long timestampEnd;
    private String location;

    public void setId(int id) {
        this.id = id;
    }

    public Booking(@NonNull long timestampStart, long timestampEnd, String location){
        this.id = id;
        this.timestampStart = timestampStart;
        this.timestampEnd = timestampEnd;
        this.location = location;
    }

    public int getId(){
        return this.id;
    }

    public long getTimestampStart(){ return this.timestampStart; }

    public long getTimestampEnd(){ return this.timestampEnd; }

    public String getName(){ return this.name; }

    public void setName(String name){ this.name = name; }

    public String getLocation() { return this.location; }

}
