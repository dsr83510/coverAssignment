package com.sdurga.assignments.autocompleteDemo.network.apiServices;

import com.sdurga.assignments.autocompleteDemo.models.gson.PlacePredictionsApiResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlacePredictionsGeneratorService {

    @GET("maps/api/place/autocomplete/json")
    Call<PlacePredictionsApiResponseModel> fetchPredictions(@Query("input") String input, @Query("types") String types, @Query("key") String apiKey);
}
