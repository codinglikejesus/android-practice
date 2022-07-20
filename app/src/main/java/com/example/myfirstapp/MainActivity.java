package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.Update;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import com.example.myfirstapp.databinding.TextRowItemBinding;
import com.example.myfirstapp.db.AppDatabase;
import com.example.myfirstapp.db.daos.BookingDao;
import com.example.myfirstapp.db.entities.Booking;
import com.example.myfirstapp.recyclerview.BookingsFragment;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    BookingsFragment.UpdateSelectedDay updateSelectedDay;
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
        Intent intent = new Intent(this, DayActivity.class);
        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);
        long selectedDate = calendar.getDate();
        Log.e("MainActivity", Long.toString(selectedDate));
        intent.putExtra("TIMESTAMP", selectedDate);
        startActivity(intent);

    }

    public void setUpdateSelectedDay(BookingsFragment.UpdateSelectedDay listener){
        updateSelectedDay = listener;
    }
}