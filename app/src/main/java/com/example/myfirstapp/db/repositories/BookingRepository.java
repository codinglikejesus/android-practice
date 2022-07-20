package com.example.myfirstapp.db.repositories;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.myfirstapp.db.daos.BookingDao;
import com.example.myfirstapp.db.entities.Booking;

import java.util.List;

public class BookingRepository {
    private static BookingRepository instance;
    private BookingDao bookingDao;

    private BookingRepository(BookingDao bookingDao){
        this.bookingDao = bookingDao;
    }

    public static BookingRepository getInstance(BookingDao bookingDao){
        if(instance == null){
            synchronized (BookingRepository.class){
                if(instance == null){
                    instance = new BookingRepository(bookingDao);
                }
            }
        }
        return instance;
    }

    public LiveData<List<Booking>> getBookingsByDay(long daystart, long dayend){
        return this.bookingDao.loadAllByDay(daystart, dayend);
    }

    public void addBooking(String eventName, String participants, String location, long beginTimestamp, long endTimestamp){
        Booking bookingToAdd = new Booking(beginTimestamp, endTimestamp, location);
        AsyncTask.execute(() -> {
            this.bookingDao.insertAll(bookingToAdd);
        });

    }
    public LiveData<Booking> getBookingById(int bookingId){
        return this.bookingDao.getById(bookingId);
    }
}
