package com.sdurga.assignments.autocompleteDemo.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.sdurga.assignments.autocompleteDemo.contracts.PlacePredictionsMVPContracts;
import com.sdurga.assignments.autocompleteDemo.models.gson.Prediction;
import com.sdurga.assignments.autocompleteDemo.models.gson.StructuredFormatting;
import com.sdurga.assignments.autocompleteDemo.models.viewModels.PlaceViewModel;
import com.sdurga.assignments.autocompleteDemo.network.IRestClient;
import com.sdurga.assignments.autocompleteDemo.models.gson.PlacePredictionsApiResponseModel;
import com.sdurga.assignments.autocompleteDemo.network.RestClient;
import com.sdurga.assignments.autocompleteDemo.ui.InsuranceCarrierPredictionFragment;
import com.sdurga.assignments.autocompleteDemo.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sdurga.assignments.com.autocompletedemo.R;

public class PlacePresenter implements PlacePredictionsMVPContracts.PresenterContract, Callback<PlacePredictionsApiResponseModel> {

    private static final String TAG = PlacePresenter.class.getSimpleName();
    private boolean isViewAttached;
    public IRestClient restClient;
    private PlacePredictionsMVPContracts.ViewContract viewContract;

    public PlacePresenter(PlacePredictionsMVPContracts.ViewContract viewContract) {
        this.viewContract = viewContract;
    }

    private IRestClient getRestClient() {
        if(restClient == null) {
            restClient = RestClient.getInstance();
        }
        return restClient;
    }

    @Override
    public void fetchPredictions(String userInput) {
        getRestClient().getPredictions(userInput, this);
    }

    @Override
    public void onResponse(Call<PlacePredictionsApiResponseModel> call, Response<PlacePredictionsApiResponseModel> response) {
        if(response != null) {
            if (response.code() == 200) {
                List<PlaceViewModel> placeViewModelList = new ArrayList<>();
                PlacePredictionsApiResponseModel placePredictionsApiResponseModel = response.body();
                for (Prediction prediction : placePredictionsApiResponseModel.getPredictions()) {
                    StructuredFormatting structuredFormatting = prediction.getStructuredFormatting();
                    placeViewModelList.add(new PlaceViewModel(structuredFormatting.getMainText(), structuredFormatting.getSecondaryText()));
                }
                if(isViewAttached){
                    viewContract.updateUI(placeViewModelList);
                }
            }
        }
    }

    @Override
    public void onFailure(Call<PlacePredictionsApiResponseModel> call, Throwable t) {
        Log.e(TAG, "onFailure() invoked with the msg" + t.getLocalizedMessage());
    }

    @Override
    public void onViewAttached() {
        isViewAttached = true;
    }

    @Override
    public void onViewDetached() {
        isViewAttached = false;
    }



    @Override
    public void onNextButtonClicked(String text, boolean hasUserSelected) {
        if (!TextUtils.isEmpty(text)) {
            if (hasUserSelected) {
               viewContract.replaceFragment();
            } else {
                viewContract.showNeutralAlertDialog(R.string.alert_invalid_address, R.string.alert_no_selection_message);
            }
        } else {
            viewContract.showNeutralAlertDialog(R.string.alert_no_selection_title, R.string.alert_no_selection_message);
        }
    }
}
