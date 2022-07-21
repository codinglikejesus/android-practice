package com.example.myfirstapp.bookinglistfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfirstapp.MainActivity;
import com.example.myfirstapp.R;
import com.example.myfirstapp.db.entities.Booking;
import com.example.myfirstapp.utils.InjectorUtils;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookingListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingListFragment extends Fragment {


    private BookingsViewModel viewModel;

    protected RecyclerView mRecyclerView;
    protected BookingListAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;


    public BookingListFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static BookingListFragment newInstance() {
        BookingListFragment fragment = new BookingListFragment();
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
        mAdapter = new BookingListAdapter();
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