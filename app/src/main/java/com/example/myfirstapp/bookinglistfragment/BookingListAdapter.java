package com.example.myfirstapp.bookinglistfragment;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.bookingform.BookingFormActivity;
import com.example.myfirstapp.databinding.TextRowItemBinding;
import com.example.myfirstapp.db.entities.Booking;

public class BookingListAdapter extends ListAdapter<Booking, BookingListAdapter.ViewHolder> {

    public BookingListAdapter() {
        super(new BookingDiffCallback());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view.
        TextRowItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.text_row_item, parent, false);
        return new ViewHolder(binding);
    }



    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d("test", "Element " + position + " set.");
        Booking booking = this.getItem(position);
        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.bind(createOnClickListener(booking.getId()), booking);

    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    private View.OnClickListener createOnClickListener(int bookingId){
        return (v -> {
            Intent intent = new Intent(v.getContext(), BookingFormActivity.class);
            intent.putExtra("BOOKING_ID", bookingId);
            v.getContext().startActivity(intent);
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextRowItemBinding binding;

        public ViewHolder(@NonNull TextRowItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(View.OnClickListener listener, Booking booking){
            binding.setBooking(booking);
            binding.setClickListener(listener);
            binding.executePendingBindings();
        }
    }

    static class BookingDiffCallback extends DiffUtil.ItemCallback<Booking> {


        @Override
        public boolean areItemsTheSame(@NonNull Booking oldItem, @NonNull Booking newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Booking oldItem, @NonNull Booking newItem) {
            return oldItem.getId() == newItem.getId();
        }
    }

}
