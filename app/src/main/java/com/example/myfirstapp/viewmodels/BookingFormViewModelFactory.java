package com.example.myfirstapp.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfirstapp.db.repositories.BookingRepository;

import java.util.Date;

public class BookingFormViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private BookingRepository repository;
    private long selectedDate;
    public BookingFormViewModelFactory(@NonNull BookingRepository repository){
        super();
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        return (T) new BookingFormViewModel(repository);
    }
}
