package com.example.eda1707.movie;

import java.util.ArrayList;
import java.util.List;

public class MovieList {

    private List<MovieInfo> items = new ArrayList<MovieInfo>();
    private int total;

    public List<MovieInfo> getItems() {
        return items;
    }

    public int getTotal() {
        return total;
    }

}


