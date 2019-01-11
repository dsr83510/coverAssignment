package com.sdurga.assignments.autocompleteDemo.base;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.BaseAdapter;

import com.sdurga.assignments.autocompleteDemo.contracts.AutocompleteTextChangeObserver;

public class BaseAutoCompleteTextChangeNotifier implements TextWatcher {

    AutocompleteTextChangeObserver autocompleteTextChangeObserver;


    public BaseAutoCompleteTextChangeNotifier(AutocompleteTextChangeObserver autocompleteTextChangeObserver) {
        this.autocompleteTextChangeObserver = autocompleteTextChangeObserver;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        autocompleteTextChangeObserver.onTextChanged(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
