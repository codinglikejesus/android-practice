package com.example.myfirstapp.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myfirstapp.db.entities.Booking;

import java.util.List;

@Dao
public interface BookingDao {
    @Query("SELECT * FROM Booking")
    List<Booking> getAll();

    @Query("SELECT * FROM Booking WHERE timestampStart BETWEEN :daystart AND :dayend")
    LiveData<List<Booking>> loadAllByDay(long daystart, long dayend);

    @Query("SELECT * FROM Booking WHERE id = :id")
    LiveData<Booking> getById(int id);

    @Insert
    void insertAll(Booking... bookings);
}
