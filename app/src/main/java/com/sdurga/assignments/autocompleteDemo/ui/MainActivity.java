package com.sdurga.assignments.autocompleteDemo.ui;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sdurga.assignments.autocompleteDemo.ui.base.BasePredictionFragment;

import sdurga.assignments.com.autocompletedemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(PlacePredictionFragment.newInstance());
    }

    public void replaceFragment(BasePredictionFragment basePredictionFragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,basePredictionFragment);
        fragmentTransaction.commit();
    }
}
