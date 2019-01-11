package com.sdurga.assignments.autocompleteDemo.network;



import com.sdurga.assignments.autocompleteDemo.models.gson.PlacePredictionsApiResponseModel;

import retrofit2.Callback;

/**
 * TODO: Include file description and usage here
 */
public interface IRestClient {
    public void getPredictions(final String input, final Callback<PlacePredictionsApiResponseModel> resultsListener);
}
