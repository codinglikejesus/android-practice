package com.example.myfirstapp.bookingform;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myfirstapp.databinding.ActivityBookingFormBinding;
import com.example.myfirstapp.db.entities.Booking;
import com.example.myfirstapp.db.repositories.BookingRepository;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BookingFormViewModel extends ViewModel {
    private BookingRepository bookingRepository;
    public MutableLiveData<Booking> booking;
    BookingFormViewModel(BookingRepository bookingRepository){
        super();
        this.bookingRepository = bookingRepository;
        this.booking = new MutableLiveData<>();

    }


    public void addBooking(){
        Booking bookingVal = booking.getValue();
        String name = bookingVal.getName();
        String location = bookingVal.getLocation();
        Long timestamp = bookingVal.getTimestampStart();
        bookingRepository.addBooking(name,null, location, timestamp, (timestamp + TimeUnit.DAYS.toMillis(1)));
    }


    public LiveData<Booking> getBookingById(int bookingId){
        return bookingRepository.getBookingById(bookingId);
    }
}
