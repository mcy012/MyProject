package com.example.eda1707.movie2.data;

import java.util.ArrayList;

public class CommentList {

    private ArrayList<CommentInfo> result = new ArrayList<CommentInfo>();

    private int totalCount;

    public ArrayList<CommentInfo> getResult() {
        return result;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
