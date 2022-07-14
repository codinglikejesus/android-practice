package com.example.myfirstapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myfirstapp.db.repositories.BookingRepository;

import java.text.SimpleDateFormat;
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

    BookingFormViewModel(BookingRepository bookingRepository){
        super();
        this.bookingRepository = bookingRepository;
    }

    public void addBooking(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateString;
        try{
             dateString = sdf.parse(this.date);
        }
        catch(Exception e){
            Log.e("BookingFormViewModel: ",
                "Failed to parse date set in BookingFormViewModel\n Date: "
                        + this.date);
            return;
        }
        ;
        bookingRepository.addBooking(this.eventName, "idols", this.location, dateString.getTime(), dateString.getTime() + TimeUnit.DAYS.toMillis(1));
    }

    public void setDate(String dateString) {
        date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEventName(String name) {
        this.eventName = name;
    }
}
