package com.sdurga.assignments.autocompleteDemo.models.gson;

import com.google.gson.annotations.SerializedName;

public final class Prediction {

    public Prediction(String description, String id, MatchedSubstring[] matchedSubstrings, String placeId, String reference, StructuredFormatting structuredFormatting, Term[] terms, String[] types) {
        this.description = description;
        this.id = id;
        this.matchedSubstrings = matchedSubstrings;
        this.placeId = placeId;
        this.reference = reference;
        this.structuredFormatting = structuredFormatting;
        this.terms = terms;
        this.types = types;
    }

    String description;

    String id;

    @SerializedName("matched_substrings")
    MatchedSubstring[] matchedSubstrings;

    @SerializedName("place_id")
    String placeId;

    String reference;

    @SerializedName("structured_formatting")
    StructuredFormatting structuredFormatting;

    Term[] terms;

    String types[];


    public StructuredFormatting getStructuredFormatting() {
        return structuredFormatting;
    }
}
