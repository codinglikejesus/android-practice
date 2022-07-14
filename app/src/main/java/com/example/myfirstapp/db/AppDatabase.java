package com.example.myfirstapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myfirstapp.db.daos.BookingDao;
import com.example.myfirstapp.db.entities.Booking;

@Database(entities = {Booking.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookingDao getBookingDao();
    private static volatile AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if(instance == null){
            synchronized (AppDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context, AppDatabase.class, "calendar-db").build();
                }
            }
        }
        return instance;
    }
}
