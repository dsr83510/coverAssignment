package com.sdurga.assignments.autocompleteDemo.models.gson;

import com.google.gson.annotations.SerializedName;

public final class StructuredFormatting {

    @SerializedName("main_text")
    String mainText;
    @SerializedName("main_text_matched_substrings")
    MatchedSubstring[] mainTextMatchedSubStrings;
    @SerializedName("secondary_text")
    String secondaryText;

    public StructuredFormatting(final String mainText, final MatchedSubstring[] mainTextMatchedSubStrings, final String secondaryText) {
        this.mainText = mainText;
        this.mainTextMatchedSubStrings = mainTextMatchedSubStrings;
        this.secondaryText = secondaryText;
    }

    public String getMainText() {
        return mainText;
    }

    public MatchedSubstring[] getMainTextMatchedSubStrings() {
        return mainTextMatchedSubStrings;
    }

    public String getSecondaryText() {
        return secondaryText;
    }
}
