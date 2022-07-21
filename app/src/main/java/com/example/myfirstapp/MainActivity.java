package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;

import com.example.myfirstapp.bookingform.BookingFormActivity;
import com.example.myfirstapp.bookinglistfragment.BookingListFragment;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;


public class MainActivity extends AppCompatActivity {
    BookingListFragment.UpdateSelectedDay updateSelectedDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            LocalDate date = LocalDate.of(year, month+1, dayOfMonth);
            Instant dateInstant = date.atStartOfDay(ZoneOffset.UTC).toInstant();
            Log.d("setOnDateChangeListener", Long.toString(dateInstant.toEpochMilli()));
            updateSelectedDay.updateSelectedDay(dateInstant.toEpochMilli());
            calendar.setDate(dateInstant.toEpochMilli());
        });

    }

    public void openDate(View view){
        Intent intent = new Intent(this, BookingFormActivity.class);
        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);
        long selectedDate = calendar.getDate();
        Log.e("MainActivity", Long.toString(selectedDate));
        intent.putExtra("TIMESTAMP", selectedDate);
        startActivity(intent);

    }

    public void setUpdateSelectedDay(BookingListFragment.UpdateSelectedDay listener){
        updateSelectedDay = listener;
    }
}