package com.example.myfirstapp.bookingform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.myfirstapp.R;
import com.example.myfirstapp.databinding.ActivityBookingFormBinding;
import com.example.myfirstapp.databinding.TextRowItemBinding;
import com.example.myfirstapp.db.entities.Booking;
import com.example.myfirstapp.utils.InjectorUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingFormActivity extends AppCompatActivity {

    private BookingFormViewModel viewModel;
    private ActivityBookingFormBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_booking_form);
        binding.setLifecycleOwner(this);
        BookingFormViewModelFactory factory = InjectorUtils.provideBookingFormViewModelFactory(getApplicationContext());
        setContentView(R.layout.activity_booking_form);
        binding.setViewmodel(new ViewModelProvider(this, factory).get(BookingFormViewModel.class));
        Intent intent = getIntent();
        long timestamp = intent.getLongExtra("TIMESTAMP", 0);
        int bookingId = intent.getIntExtra("BOOKING_ID", -1);

        if(bookingId != -1){
            binding.getViewmodel().getBookingById(bookingId).observe(this, booking -> {
                binding.getViewmodel().booking.setValue(booking);
                binding.notifyChange();
            });
            return;
        }

        if(timestamp != 0) {
            binding.getViewmodel().booking.setValue(new Booking(timestamp, 0, null));
            binding.notifyChange();
            return;
        }
    }

    public void addEvent(View view){
        binding.getViewmodel().addBooking();
        finish();
    }
}