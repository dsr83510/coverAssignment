package com.sdurga.assignments.autocompleteDemo.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.sdurga.assignments.autocompleteDemo.contracts.InsuranceCarriersPredictionsMVPContracts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import sdurga.assignments.com.autocompletedemo.R;

public class InsuranceCarriersPresenter implements InsuranceCarriersPredictionsMVPContracts.PresenterContract {


    private static final String JSON_ARRAY_KEY = "insurance_carriers";
    private HashSet<String> insuranceCarriersSet;
    private boolean isViewAttached;
    private InsuranceCarriersPredictionsMVPContracts.ViewContract viewContract;
    private List<String> insuranceCarriersNames;

    public InsuranceCarriersPresenter(InsuranceCarriersPredictionsMVPContracts.ViewContract viewContract) {
        this.viewContract = viewContract;
    }

    @Override
    public void getInsuranceCarriersNames(Context context) {
        String jsonString = loadJSONFromAssetFile(context);
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            List<String> insuranceCarriersNames = new ArrayList<String>();
            JSONArray jsonArray = jsonObject.getJSONArray(JSON_ARRAY_KEY);
            for (int i = 0; i < jsonArray.length(); i++) {
                insuranceCarriersNames.add(jsonArray.getString(i));
            }
            this.insuranceCarriersNames = insuranceCarriersNames;
            insuranceCarriersSet = new HashSet<>();
            for (String name : insuranceCarriersNames) {
                insuranceCarriersSet.add(name.toLowerCase());
            }
            if(isViewAttached){
                viewContract.updateUI(insuranceCarriersNames);
            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }

    private String loadJSONFromAssetFile(final Context context) {
        String jsonString = null;
        try {
            InputStream inputStream = context.getAssets().open("carriers.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return jsonString;
    }

    @Override
    public void onNextButtonClicked(String text) {
        if (!TextUtils.isEmpty(text)) {
            if (isValidInsurance(text)) {
                viewContract.showNeutralAlertDialog(R.string.alert_final_success, R.string.alert_final_success);
            } else {
                viewContract.showNeutralAlertDialog(R.string.alert_invalid_address, R.string.alert_no_selection_message);
            }
        } else {
            viewContract.showNeutralAlertDialog(R.string.alert_no_selection_title, R.string.alert_no_selection_message);
        }
    }

    private boolean isValidInsurance(String insurance) {
        return this.insuranceCarriersSet.contains(insurance.toLowerCase());
    }

    @Override
    public void onViewAttached() {
        isViewAttached = true;
        if (insuranceCarriersNames != null) {
            viewContract.updateUI(insuranceCarriersNames);
        }
    }

    @Override
    public void onViewDetached() {
        isViewAttached = false;
    }
}
