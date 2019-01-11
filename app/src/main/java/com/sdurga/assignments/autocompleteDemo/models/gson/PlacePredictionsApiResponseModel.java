package com.sdurga.assignments.autocompleteDemo.models.gson;

public final class PlacePredictionsApiResponseModel {

    Prediction[] predictions;
    String status;

    public PlacePredictionsApiResponseModel(final Prediction[] predictions, final String status) {
        this.predictions = predictions;
        this.status = status;
    }

    public Prediction[] getPredictions() {
        return predictions;
    }
}
