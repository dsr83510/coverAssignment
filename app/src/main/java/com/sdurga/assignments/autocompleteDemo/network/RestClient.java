package com.sdurga.assignments.autocompleteDemo.network;

import android.util.Log;

import com.sdurga.assignments.autocompleteDemo.models.gson.PlacePredictionsApiResponseModel;
import com.sdurga.assignments.autocompleteDemo.network.apiServices.PlacePredictionsGeneratorService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton RestClient for the app.
 */

public final class RestClient implements IRestClient {


    private static final String BASE_URL = "https://maps.googleapis.com/";
    private static final String API_KEY = "AIzaSyBnMJjJXi3cyIVxzhdlYyaCG3PPQ4huF78";
    private static final String API_INPUT_TYPE_VALUE = "address";
    private static final String TAG = RestClient.class.getSimpleName();
    private PlacePredictionsGeneratorService placePredictionsGeneratorService;
    private Retrofit retrofit;

    private RestClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        placePredictionsGeneratorService = retrofit.create(PlacePredictionsGeneratorService.class);
    }

    //region Static Inner Helper Class

    private static class RestClientHelper {
        private static final RestClient INSTANCE = new RestClient();
    }


    public static RestClient getInstance() {
        return RestClientHelper.INSTANCE;
    }

    //endregion


    //region Public Methods

    public void getPredictions(final String input, final Callback<PlacePredictionsApiResponseModel> resultsListener) {
        Call<PlacePredictionsApiResponseModel> call =  placePredictionsGeneratorService.fetchPredictions(input,API_INPUT_TYPE_VALUE,API_KEY);
        call.enqueue(resultsListener);
    }

    //endregion



}
