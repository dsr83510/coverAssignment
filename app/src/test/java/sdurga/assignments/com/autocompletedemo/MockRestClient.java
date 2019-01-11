package sdurga.assignments.com.autocompletedemo;

import com.sdurga.assignments.autocompleteDemo.models.gson.PlacePredictionsApiResponseModel;
import com.sdurga.assignments.autocompleteDemo.network.IRestClient;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * TODO: Include file description and usage here
 */
public class MockRestClient implements IRestClient {
    public PlacePredictionsApiResponseModel mockResponse;

    @Override
    public void getPredictions(String input, Callback<PlacePredictionsApiResponseModel> resultsListener) {
        Response<PlacePredictionsApiResponseModel> response = Response.<PlacePredictionsApiResponseModel>success(mockResponse);
        resultsListener.onResponse(null, response);
    }
}
