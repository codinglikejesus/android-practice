package com.example.myfirstapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

    private String location;
    private String participants;

    public String getLocation() {
        return location;
    }

    private String date;
    private String eventName;

    private LiveData<Booking> booking;

    BookingFormViewModel(BookingRepository bookingRepository){
        super();
        this.bookingRepository = bookingRepository;
    }

    public void addBooking(){
        LocalDate dateToAdd;
        try{
             dateToAdd = LocalDate.parse(this.date);

        }
        catch(Exception e){
            Log.e("BookingFormViewModel: ",
                "Failed to parse date set in BookingFormViewModel\n Date: "
                        + this.date);
            return;
        }
        Instant dateInstant = dateToAdd.atStartOfDay(ZoneOffset.UTC).toInstant();
        bookingRepository.addBooking(this.eventName, "idols", this.location, dateInstant.toEpochMilli(), (dateInstant.toEpochMilli() + TimeUnit.DAYS.toMillis(1)));
    }

    public LiveData<Booking> getBookingById(int bookingId){
        return bookingRepository.getBookingById(bookingId);
    }

    public void setDate(String dateString) {
        date = dateString;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEventName(String name) {
        this.eventName = name;
    }
}
