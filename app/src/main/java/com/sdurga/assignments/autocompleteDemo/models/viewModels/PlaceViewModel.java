package com.sdurga.assignments.autocompleteDemo.models.viewModels;

public final class PlaceViewModel {

    String addressLine1;
    String addressLine2;

    public PlaceViewModel(final String addressLine1, final String addressLine2) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
    }


    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }
}
