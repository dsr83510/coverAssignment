package com.sdurga.assignments.autocompleteDemo.contracts;

import com.sdurga.assignments.autocompleteDemo.models.viewModels.PlaceViewModel;

import java.util.List;

public interface PlacePredictionsMVPContracts {

    interface ViewContract {
        void updateUI(List<PlaceViewModel> placeViewModelList);
        void replaceFragment();
        void showNeutralAlertDialog(int titleResourceID, int messageID);
    }

    interface PresenterContract {
        void onViewAttached();
        void fetchPredictions(String userInput);
        void onViewDetached();
        void onNextButtonClicked(String text, boolean hasUserClicked);
    }

}
