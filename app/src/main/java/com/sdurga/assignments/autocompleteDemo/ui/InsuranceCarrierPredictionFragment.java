package com.sdurga.assignments.autocompleteDemo.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.sdurga.assignments.autocompleteDemo.adapters.InsuranceListAdapter;
import com.sdurga.assignments.autocompleteDemo.contracts.AutocompleteTextChangeObserver;
import com.sdurga.assignments.autocompleteDemo.contracts.InsuranceCarriersPredictionsMVPContracts;
import com.sdurga.assignments.autocompleteDemo.presenter.InsuranceCarriersPresenter;
import com.sdurga.assignments.autocompleteDemo.ui.base.BasePredictionFragment;

import java.util.HashSet;
import java.util.List;

import sdurga.assignments.com.autocompletedemo.R;

public class InsuranceCarrierPredictionFragment extends BasePredictionFragment implements InsuranceCarriersPredictionsMVPContracts.ViewContract, AutocompleteTextChangeObserver, AdapterView.OnItemClickListener {

    private InsuranceCarriersPredictionsMVPContracts.PresenterContract presenterContract;
    private InsuranceListAdapter insuranceListAdapter;


    public static InsuranceCarrierPredictionFragment newInstance() {
        return new InsuranceCarrierPredictionFragment();
    }

    public InsuranceCarrierPredictionFragment() {
        presenterContract = new InsuranceCarriersPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        insuranceListAdapter = new InsuranceListAdapter(getLayoutInflater());
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
        autoCompleteTextView.setAdapter(insuranceListAdapter);
        setLabelsForViews();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenterContract.getInsuranceCarriersNames(getActivity());
    }

    @Override
    public void setLabelsForViews() {
        autoCompleteTextView.setHint(R.string.insurance_carrier_selection_text_entry_hint);
        predictionQuestionTextView.setText(R.string.insurance_carrier_selection_question);
    }

    @Override
    public void onNextButtonClick() {
        this.presenterContract.onNextButtonClicked(autoCompleteTextView.getText().toString());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onTextChanged(String text) {

    }

    @Override
    public void updateUI(List<String> insuranceCarriersNames) {
        insuranceListAdapter.setInsuranceCarriersList(insuranceCarriersNames);
        insuranceListAdapter.notifyDataSetChanged();
    }


}
