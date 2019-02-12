package com.example.eda1707.movie2.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MovieList implements Parcelable{

    public ArrayList<MovieInfo> result = new ArrayList<MovieInfo>();

    public MovieList() {
    }


    protected MovieList(Parcel in) {
        result = in.createTypedArrayList(MovieInfo.CREATOR);
    }

    public static final Creator<MovieList> CREATOR = new Creator<MovieList>() {
        @Override
        public MovieList createFromParcel(Parcel in) {
            return new MovieList(in);
        }

        @Override
        public MovieList[] newArray(int size) {
            return new MovieList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(result);
    }
}
