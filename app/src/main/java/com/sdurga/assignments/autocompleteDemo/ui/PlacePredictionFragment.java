package com.sdurga.assignments.autocompleteDemo.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.sdurga.assignments.autocompleteDemo.adapters.PlacePredictionsListAdapter;
import com.sdurga.assignments.autocompleteDemo.base.BaseAutoCompleteTextChangeNotifier;
import com.sdurga.assignments.autocompleteDemo.contracts.AutocompleteTextChangeObserver;
import com.sdurga.assignments.autocompleteDemo.contracts.PlacePredictionsMVPContracts;
import com.sdurga.assignments.autocompleteDemo.models.viewModels.PlaceViewModel;
import com.sdurga.assignments.autocompleteDemo.presenter.PlacePresenter;
import com.sdurga.assignments.autocompleteDemo.ui.base.BasePredictionFragment;
import com.sdurga.assignments.autocompleteDemo.utility.Debouncer;

import java.util.List;
import java.util.concurrent.TimeUnit;

import sdurga.assignments.com.autocompletedemo.R;

public class PlacePredictionFragment extends BasePredictionFragment implements PlacePredictionsMVPContracts.ViewContract, AutocompleteTextChangeObserver, AdapterView.OnItemClickListener {

    private PlacePredictionsMVPContracts.PresenterContract presenterContract;
    private PlacePredictionsListAdapter placePredictionsListAdapter;
    private Debouncer debouncer = new Debouncer();
    private boolean hasUserSelected;


    public static PlacePredictionFragment newInstance() {
        return new PlacePredictionFragment();
    }

    public PlacePredictionFragment() {
        presenterContract = new PlacePresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        placePredictionsListAdapter = new PlacePredictionsListAdapter(getLayoutInflater());
    }

    @Override
    public void onStart() {
        super.onStart();
        presenterContract.onViewAttached();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenterContract.onViewDetached();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = super.onCreateView(inflater, container, savedInstanceState);
        setLabelsForViews();
        autoCompleteTextView.addTextChangedListener(new BaseAutoCompleteTextChangeNotifier(this));
        autoCompleteTextView.setAdapter(placePredictionsListAdapter);
        autoCompleteTextView.setOnItemClickListener(this);
        return  rootView;
    }

    @Override
    public void setLabelsForViews() {
        autoCompleteTextView.setHint(R.string.place_prediction_text_entry_hint);
        predictionQuestionTextView.setText(R.string.place_prediction_question);
    }

    @Override
    public void onNextButtonClick() {
        presenterContract.onNextButtonClicked(autoCompleteTextView.getText().toString(), hasUserSelected);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        hasUserSelected = true;
        PlaceViewModel place = (PlaceViewModel) parent.getItemAtPosition(position);
        autoCompleteTextView.setText(place.getAddressLine1() + ", " + place.getAddressLine2());
    }

    @Override
    public void updateUI(final List<PlaceViewModel> placeViewModelList) {
        placePredictionsListAdapter.setPlaceViewModelList(placeViewModelList);
        placePredictionsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onTextChanged(final String text) {
        debouncer.debounce("fetchPredictionKey", new Runnable() {
            @Override public void run() {
                presenterContract.fetchPredictions(text);
            }
        }, 300, TimeUnit.MILLISECONDS);
    }

    @Override
    public void replaceFragment() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.replaceFragment(InsuranceCarrierPredictionFragment.newInstance());
    }
}
