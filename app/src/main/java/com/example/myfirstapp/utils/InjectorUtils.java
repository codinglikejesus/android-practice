package com.example.myfirstapp.utils;

import android.content.Context;

import com.example.myfirstapp.db.AppDatabase;
import com.example.myfirstapp.db.repositories.BookingRepository;
import com.example.myfirstapp.viewmodels.BookingFormViewModelFactory;
import com.example.myfirstapp.viewmodels.BookingsViewModelFactory;

public class InjectorUtils {

    private static BookingRepository getBookingRepository(Context context){
        return BookingRepository.getInstance(AppDatabase.getInstance(context.getApplicationContext()).getBookingDao());
    }

    public static BookingsViewModelFactory provideBookingsViewModelFactory(Context context){
        return new BookingsViewModelFactory(getBookingRepository(context));
    }

    public static BookingFormViewModelFactory provideBookingFormViewModelFactory(Context context){
        return new BookingFormViewModelFactory(getBookingRepository(context));
    }
}
