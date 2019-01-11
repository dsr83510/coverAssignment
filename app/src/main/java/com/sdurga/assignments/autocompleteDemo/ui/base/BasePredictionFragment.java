package com.sdurga.assignments.autocompleteDemo.ui.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import sdurga.assignments.com.autocompletedemo.R;


public abstract class BasePredictionFragment extends Fragment {

    protected AutoCompleteTextView autoCompleteTextView;
    protected TextView predictionQuestionTextView;
    protected Button nextButton;


    // region LIFECYCLE METHODS
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_prediction, container, false);
        autoCompleteTextView = rootView.findViewById(R.id.prediction_autocomplete_view);
        nextButton = rootView.findViewById(R.id.next_button);
        predictionQuestionTextView = rootView.findViewById(R.id.prediction_question_label);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextButtonClick();
            }
        });
        return rootView;
    }

    // endregion

    // region COMMON METHODS

    public abstract void setLabelsForViews();

    public abstract void onNextButtonClick();

    public void showNeutralAlertDialog(int titleResourceID, int messageID){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle( titleResourceID);
        alertDialogBuilder.setMessage(messageID);
        alertDialogBuilder.setNeutralButton(R.string.alert_ok_generic_button_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    // endregion



}
