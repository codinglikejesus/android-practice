package com.example.myfirstapp.viewmodels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.myfirstapp.db.entities.Booking;
import com.example.myfirstapp.db.repositories.BookingRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

public class BookingsViewModel extends ViewModel {

    private MutableLiveData<Long> mutableBookingParam = new MutableLiveData<>();
    private BookingRepository bookingRepository;
    public LiveData<List<Booking>> bookings;
    public BookingsViewModel(@NonNull BookingRepository bookingRepository){
        super();
        this.bookingRepository = bookingRepository;
        bookings = Transformations.switchMap(mutableBookingParam, timestamp -> {
            long endDate =  timestamp + TimeUnit.DAYS.toMillis(1);
            return this.bookingRepository.getBookingsByDay(timestamp, endDate);
        });
        this.setSelectedDay(getStartOfDayInMillis());
    }

    public void setSelectedDay(long selectedDay){
        this.mutableBookingParam.setValue(selectedDay);
    }

    public long getStartOfDayInMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
}
