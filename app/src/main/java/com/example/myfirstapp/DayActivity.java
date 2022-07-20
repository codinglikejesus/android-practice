package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myfirstapp.utils.InjectorUtils;
import com.example.myfirstapp.viewmodels.BookingFormViewModel;
import com.example.myfirstapp.viewmodels.BookingFormViewModelFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DayActivity extends AppCompatActivity {

    private BookingFormViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        BookingFormViewModelFactory factory = InjectorUtils.provideBookingFormViewModelFactory(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        this.viewModel = new ViewModelProvider(this, factory).get(BookingFormViewModel.class);
        Intent intent = getIntent();
        long timestamp = intent.getLongExtra("TIMESTAMP", 0);
        int bookingId = intent.getIntExtra("BOOKING_ID", -1);
        EditText dateEdit = (EditText) findViewById(R.id.editTextDate3);
        EditText locationEdit = (EditText) findViewById(R.id.event_location_input);
        EditText nameEdit = (EditText) findViewById(R.id.event_name_input);

        if(bookingId != -1){
            this.viewModel.getBookingById(bookingId).observe(this, booking -> {
                Date date = new Date(booking.getTimestampStart());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = sdf.format(date);
                dateEdit.setText(dateString);
                locationEdit.setText(booking.getLocation());
                nameEdit.setText(booking.getName());
            });
            return;
        }

        if(timestamp != 0) {
            Date date = new Date(timestamp);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = sdf.format(date);
            dateEdit.setText(dateString);
            return;
        }


        dateEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    viewModel.setDate(dateEdit.getText().toString());
                }
            }
        });

        locationEdit.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    viewModel.setLocation(locationEdit.getText().toString());
                }
            }
        });
        nameEdit.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    viewModel.setEventName(nameEdit.getText().toString());
                }
            }
        });
    }

    public void addEvent(View view){
        viewModel.addBooking();
        finish();
    }
}