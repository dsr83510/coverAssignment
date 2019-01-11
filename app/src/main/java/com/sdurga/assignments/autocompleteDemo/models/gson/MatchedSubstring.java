package com.sdurga.assignments.autocompleteDemo.models.gson;

public final class MatchedSubstring {

    int lenth;
    int offset;

    public MatchedSubstring(final int lenth, final int offset) {
        this.lenth = lenth;
        this.offset = offset;
    }

    public int getLenth() {
        return lenth;
    }

    public int getOffset() {
        return offset;
    }
}
