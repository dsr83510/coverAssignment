package com.sdurga.assignments.autocompleteDemo.contracts;

import android.content.Context;

import java.util.List;

public interface InsuranceCarriersPredictionsMVPContracts {

    interface ViewContract {
        void updateUI(List<String> insuranceCarriersNames);
        void showNeutralAlertDialog(int titleResourceID, int messageID);
    }

    interface PresenterContract {
        void getInsuranceCarriersNames(final Context context);
        void onViewAttached();
        void onViewDetached();
        void onNextButtonClicked(String text);
    }

}
