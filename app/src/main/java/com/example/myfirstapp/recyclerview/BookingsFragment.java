package com.example.myfirstapp.recyclerview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.myfirstapp.MainActivity;
import com.example.myfirstapp.R;
import com.example.myfirstapp.db.AppDatabase;
import com.example.myfirstapp.db.daos.BookingDao;
import com.example.myfirstapp.db.entities.Booking;
import com.example.myfirstapp.utils.InjectorUtils;
import com.example.myfirstapp.viewmodels.BookingsViewModel;
import com.example.myfirstapp.viewmodels.BookingsViewModelFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingsFragment extends Fragment {


    private BookingsViewModel viewModel;

    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;


    public BookingsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static BookingsFragment newInstance() {
        BookingsFragment fragment = new BookingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        BookingsViewModelFactory factory = InjectorUtils.provideBookingsViewModelFactory(getContext());
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_bookings, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        this.viewModel = new ViewModelProvider(this, factory).get(BookingsViewModel.class);
        mAdapter = new CustomAdapter();
        subscribeUi(mAdapter);
        mRecyclerView.setAdapter(mAdapter);
        MainActivity parentActivity = (MainActivity) this.getActivity();
        parentActivity.setUpdateSelectedDay((timestamp) -> {
            viewModel.setSelectedDay(timestamp);
            mAdapter.notifyDataSetChanged();
        });
        return rootView;
    }
    private void subscribeUi(ListAdapter adapter) {
        this.viewModel.bookings.observe(getViewLifecycleOwner(), bookings -> {
            Log.d("subscribeUI", "called");
            if (bookings != null) {
                adapter.submitList((ArrayList<Booking>) bookings);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public interface UpdateSelectedDay {
        void updateSelectedDay(long timestamp);
    }
}