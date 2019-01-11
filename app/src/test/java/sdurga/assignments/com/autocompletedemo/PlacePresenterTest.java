package sdurga.assignments.com.autocompletedemo;

import com.sdurga.assignments.autocompleteDemo.contracts.PlacePredictionsMVPContracts;
import com.sdurga.assignments.autocompleteDemo.models.gson.PlacePredictionsApiResponseModel;
import com.sdurga.assignments.autocompleteDemo.models.gson.Prediction;
import com.sdurga.assignments.autocompleteDemo.models.gson.StructuredFormatting;
import com.sdurga.assignments.autocompleteDemo.models.viewModels.PlaceViewModel;
import com.sdurga.assignments.autocompleteDemo.presenter.PlacePresenter;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PlacePresenterTest implements PlacePredictionsMVPContracts.ViewContract {
    List<PlaceViewModel> updateUIParameter;

    @Test
    public void test_fetchPredictions() {
        PlacePresenter placePresenter = new PlacePresenter(this);
        MockRestClient mockClient = new MockRestClient();
        mockClient.mockResponse = getMockResponse();
        placePresenter.restClient = mockClient;

        updateUIParameter = null;
        placePresenter.fetchPredictions("13422 6TH Ave");
        assertTrue(updateUIParameter.size() == 3);
        assertEquals("137 Noe Street", updateUIParameter.get(0).getAddressLine1());
        assertEquals("San Francisco, CA, USA", updateUIParameter.get(0).getAddressLine2());
        assertEquals("137 Northeast Noe Street", updateUIParameter.get(1).getAddressLine1());
        assertEquals("Bend, OR, USA", updateUIParameter.get(1).getAddressLine2());
        assertEquals("137 Noe Street", updateUIParameter.get(2).getAddressLine1());
        assertEquals("Pierre Part, LA, USA", updateUIParameter.get(2).getAddressLine2());
    }

    public void updateUI(List<PlaceViewModel> placeViewModelList) {
        updateUIParameter = placeViewModelList;
    }

    public PlacePredictionsApiResponseModel getMockResponse() {
        Prediction prediction1 = new Prediction(null,
                null,
                null,
                null,
                null,
                new StructuredFormatting("137 Noe Street", null, "San Francisco, CA, USA"),
                null,
                null);

        Prediction prediction2 = new Prediction(null,
                null,
                null,
                null,
                null,
                new StructuredFormatting("137 Northeast Noe Street", null, "Bend, OR, USA"),
                null,
                null);

        Prediction prediction3 = new Prediction(null,
                null,
                null,
                null,
                null,
                new StructuredFormatting("137 Noe Street", null, "Pierre Part, LA, USA"),
                null,
                null);

        Prediction[] predictions = {prediction1, prediction2, prediction3};
        PlacePredictionsApiResponseModel response = new PlacePredictionsApiResponseModel(predictions, "OK");
        return response;
    }

}